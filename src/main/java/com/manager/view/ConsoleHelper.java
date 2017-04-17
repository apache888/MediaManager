package com.manager.view;

import com.manager.controller.BookController;
import com.manager.controller.Controller;
import com.manager.controller.MovieController;
import com.manager.controller.MusicController;
import com.manager.dao.HibernateUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 */
public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private View view;
    private Controller controller;

    public void startApp() {
        showCommandsMenu();
    }

    public static void writeToConsole(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    private void showCommandsMenu() {
        while (true) {
            try {
                writeToConsole("\nSelect command:\n\n" +
                        "0 - Завершить программу\n" +
                        "1 - Внести в список книгу/музыку/фильм\n" +
                        "2 - Показать статус для книги/музыки/фильма\n" +
                        "3 - Изменить статус для книги/музыки/фильма\n" +
                        "4 - Вывести список книг/музыки/фильма\n" +
                        "5 - Вывести список книг/музыки/фильма фильтрованный по статусу\n" +
                        "6 - Удалить книгу/музыку/фильм\n");
                switch (Integer.parseInt(readString())) {
                    case 0:
                        HibernateUtil.shutdown();
                        return;
                    case 1:
                        selectEntity();
                        view.fireEventAdd();
                        break;
                    case 2:
                        selectEntity();
                        view.fireEventShowStatus();
                        break;
                    case 3:
                        selectEntity();
                        view.fireEventUpdateStatus();
                        break;
                    case 4:
                        selectEntity();
                        view.fireEventGetAll();
                        break;
                    case 5:
                        selectEntity();
                        view.fireEventGetAllByStatus();
                        break;
                    case 6:
                        selectEntity();
                        view.fireEventDelete();
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong command. Try again.");
                }
            } catch (IOException | IllegalArgumentException e) {
                writeToConsole(e.getMessage());
            } catch (RuntimeException re) {
                writeToConsole(re.getMessage());
            }
        }
    }

    private void selectEntity() throws IOException {
        while (true) {
            ConsoleHelper.writeToConsole("Select type of media\n" +
                    "\t\t1 - Book\n" +
                    "\t\t2 - Movie\n" +
                    "\t\t3 - Music\n");
            try {
                switch (Integer.parseInt(ConsoleHelper.readString())) {
                    case 1:
                        view = new BookView();
                        controller = new BookController();
                        view.setController(controller);
                        return;
                    case 2:
                        view = new MovieView();
                        controller = new MovieController();
                        view.setController(controller);
                        return;
                    case 3:
                        view = new MusicView();
                        controller = new MusicController();
                        view.setController(controller);
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                writeToConsole("Wrong select. Try again.");
            }
        }
    }
}
