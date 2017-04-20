package com.manager.dao;

import java.util.List;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * interface describes DAO layer for type T entity
 * contains CRUD methods
 */
public interface MediaDao<T> {

    /**
     * create type T object in database
     * @param obj - given Entity object
     */
    void add(T obj);

    /**
     * delete type T object from database
     * @param id - Entity id
     */
    void delete(int id);

    /**
     * receive list of all type T objects from database
     * @return List<T>
     */
    List<T> getAll();

    /**
     * receive list of all type T objects from database
     * iltered by status
     * @return List<T>
     */
    List<T> getAllByStatus(int status);

    /**
     * receive type T object by id from database
     * @param id - Entity id
     * @return type T object
     */
    T showStatus(int id);

    /**
     * update type T object status in database
     * @param id - Entity id
     * @param status - new status
     */
    void updateStatus(int id, int status);

}
