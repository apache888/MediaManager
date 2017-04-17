package com.manager.view;

import com.manager.controller.Controller;

/**
 * Created on 16.04.2017.
 * @author Roman Hayda
 *
 * View implementation in MVC pattern
 * interface contains methods to fire CRUD events
 */
public interface View {

    /**
     * set Controller for View (MVC)
     * @param controller - specific controller for particular object
     */
    void setController(Controller controller);

    /**
     * fire event to create entity and to add it in database
     */
    void fireEventAdd();

    /**
     * fire event to delete entity from database
     */
    void fireEventDelete();

    /**
     * fire event to receive list of all entities of specific table from database
     */
    void fireEventGetAll();

    /**
     * fire event to receive list of all entities of specific table
     * from database filtered by status
     */
    void fireEventGetAllByStatus();

    /**
     * fire event to receive entity by id from database
     */
    void fireEventShowStatus();

    /**
     * fire event to update entity status in database
     */
    void fireEventUpdateStatus();

}
