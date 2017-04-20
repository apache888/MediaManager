package com.manager.view;

import com.manager.controller.Controller;
import com.manager.model.Movie;
import com.manager.model.MovieStatus;

import java.io.IOException;
import java.util.List;

/**
 * Created on 16.04.2017.
 * @author Roman Hayda
 *
 * Class implements interface View for Movie entity
 * class contains methods to fire CRUD events
 */
public class MovieView implements View {

    private Controller<Movie> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventAdd() {
        Movie movie = new Movie();
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
        movie.setName(name);
        movie.setStatus(MovieStatus.WANT_TO_WATCH);
        controller.onAdd(movie);
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
                ConsoleHelper.writeToConsole(String.format("\t %d - WANT_TO_WATCH", MovieStatus.WANT_TO_WATCH.ordinal()));
                ConsoleHelper.writeToConsole(String.format("\t %d - AM_WATCHING", MovieStatus.AM_WATCHING.ordinal()));
                ConsoleHelper.writeToConsole(String.format("\t %d - WATCHED", MovieStatus.WATCHED.ordinal()));
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
     * @param list - list of Movie objects
     */
    private void writeAll(List<Movie> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll movies\n");
            for (Movie movie : list) {
                ConsoleHelper.writeToConsole(movie.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }
}
