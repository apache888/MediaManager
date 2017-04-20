package com.manager.controller;

import com.manager.dao.MediaDao;
import com.manager.dao.MusicDaoImpl;
import com.manager.model.Music;

import java.util.List;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * Class implements interface Controller for Music entity
 * contains methods to handle CRUD events
 */
public class MusicController implements Controller<Music> {
    private MediaDao<Music> musicDao = new MusicDaoImpl();

    @Override
    public void onAdd(Music music) {
        musicDao.add(music);
    }

    @Override
    public void onDelete(int id) {
        musicDao.delete(id);
    }

    @Override
    public List<Music> onGetAll() {
        return musicDao.getAll();
    }

    @Override
    public List<Music> onGetAllByStatus(int status) {
        return musicDao.getAllByStatus(status);
    }

    @Override
    public Music onShowStatus(int id) {
        return musicDao.showStatus(id);
    }

    @Override
    public void onUpdateStatus(int id, int status) {
        musicDao.updateStatus(id, status);
    }
}
