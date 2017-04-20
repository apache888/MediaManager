package com.manager.controller;

import java.util.List;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * Controller implementation in MVC pattern
 * interface contains methods to handle CRUD events
 * generic by entity type
 */
public interface Controller<T> {

    /**
     * handles event to create entity in database
     * @param obj - Entity object
     */
    void onAdd(T obj);

    /**
     * handles event to delete entity from database
     * @param id - Entity id
     */
    void onDelete(int id);

    /**
     * handles event to receive list of all Entity objects from database
     * @return List<T>
     */
    List<T> onGetAll();

    /**
     * handles event to receive list of all Entity objects
     * from database
     * filtered by status
     * @return List<T>
     */
    List<T> onGetAllByStatus(int status);

    /**
     * handles event to receive entity by id from database
     * @param id - Entity id
     * @return Entity object
     */
    T onShowStatus(int id);

    /**
     * handles event to update entity in database
     * @param id - Entity id
     * @param status - new status
     */
    void onUpdateStatus(int id, int status);

}
