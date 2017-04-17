package com.manager.view;

import com.manager.controller.Controller;
import com.manager.model.Music;
import com.manager.model.MusicStatus;

import java.io.IOException;
import java.util.List;

/**
 * Created on 16.04.2017.
 * @author Roman Hayda
 *
 * Class implements interface View for Music entity
 * class contains methods to fire CRUD events
 */
public class MusicView implements View {

    private Controller<Music> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventAdd() {
        Music music = new Music();
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
        music.setName(name);
        music.setStatus(MusicStatus.WANT_TO_LISTEN);
        controller.onAdd(music);
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
        String status = selectStatus();
        writeAll(controller.onGetAllByStatus(status.toUpperCase()));
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
        String status = selectStatus();
        controller.onUpdateStatus(id, status.toUpperCase());
    }

    /**
     * select status by console dialog
     * @return String status
     */
    private String selectStatus() {
        String status;
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Select status:\n");
                for (MusicStatus movieStatus : MusicStatus.values()) {
                    ConsoleHelper.writeToConsole(movieStatus.toString());
                }
                status = ConsoleHelper.readString();
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
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
     * @param list - list of Music objects
     */
    private void writeAll(List<Music> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll musics\n");
            for (Music music : list) {
                ConsoleHelper.writeToConsole(music.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }
}
