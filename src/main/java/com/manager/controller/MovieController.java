package com.manager.controller;

import com.manager.dao.MediaDao;
import com.manager.dao.MovieDaoImpl;
import com.manager.model.Movie;

import java.util.List;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * Class implements interface Controller for Movie entity
 * contains methods to handle CRUD events
 */
public class MovieController implements Controller<Movie> {
    private MediaDao<Movie> movieDao = new MovieDaoImpl();

    @Override
    public void onAdd(Movie movie) {
        movieDao.add(movie);
    }

    @Override
    public void onDelete(int id) {
        movieDao.delete(id);
    }

    @Override
    public List<Movie> onGetAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> onGetAllByStatus(String status) {
        return movieDao.getAllByStatus(status);
    }

    @Override
    public Movie onShowStatus(int id) {
        return movieDao.showStatus(id);
    }

    @Override
    public void onUpdateStatus(int id, String status) {
        movieDao.updateStatus(id, status);
    }
}
