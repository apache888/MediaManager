package com.manager.view;

import com.manager.controller.Controller;
import com.manager.model.Book;
import com.manager.model.BookStatus;

import java.io.IOException;
import java.util.List;

/**
 * Created on 16.04.2017.
 *@author Roman Hayda
 *
 * Class implements interface View for Book entity
 * class contains methods to fire CRUD events
 */
public class BookView implements View {

    private Controller<Book> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventAdd() {
        Book book = new Book();
        String name;
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input name:");
                name = ConsoleHelper.readString();
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
            }
        }
        book.setName(name);
        book.setStatus(BookStatus.WANT_TO_READ);
        controller.onAdd(book);
    }

    @Override
    public void fireEventDelete() {
        int id = inputId();
        controller.onDelete(id);
    }

    @Override
    public void fireEventGetAll() {
        writeAll(controller.onGetAll());
    }

    @Override
    public void fireEventGetAllByStatus() {
        int status = selectStatus();
        writeAll(controller.onGetAllByStatus(status));
    }

    @Override
    public void fireEventShowStatus() {
        int id = inputId();
        ConsoleHelper.writeToConsole("\n");
        ConsoleHelper.writeToConsole(controller.onShowStatus(id).toString());
    }

    @Override
    public void fireEventUpdateStatus() {
        int id = inputId();
        int status = selectStatus();
        controller.onUpdateStatus(id, status);
    }

    /**
     * select status by console dialog
     * @return String status
     */
    private int selectStatus() {
        int status;
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Select status:\n");
                ConsoleHelper.writeToConsole(String.format("\t %d - WANT_TO_READ", BookStatus.WANT_TO_READ.ordinal()));
                ConsoleHelper.writeToConsole(String.format("\t %d - AM_READING", BookStatus.AM_READING.ordinal()));
                ConsoleHelper.writeToConsole(String.format("\t %d - READ", BookStatus.READ.ordinal()));
                status = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
            } catch (NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        return status;
    }

    /**
     * select ID by console dialog
     * @return int id
     */
    private int inputId() {
        int id;
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input ID:");
                id = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        return id;
    }

    /**
     * write to console information about received list of objects
     * @param list - list of Book objects
     */
    private void writeAll(List<Book> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll books\n");
            for (Book book : list) {
                ConsoleHelper.writeToConsole(book.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }
}
