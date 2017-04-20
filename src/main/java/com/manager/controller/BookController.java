package com.manager.controller;

import com.manager.dao.BookDaoImpl;
import com.manager.dao.MediaDao;
import com.manager.model.Book;

import java.util.List;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * Class implements interface Controller for Book entity
 * contains methods to handle CRUD events
 */
public class BookController implements Controller<Book> {
    private MediaDao<Book> bookDao = new BookDaoImpl();

    @Override
    public void onAdd(Book book) {
        bookDao.add(book);
    }

    @Override
    public void onDelete(int id) {
        bookDao.delete(id);
    }

    @Override
    public List<Book> onGetAll() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> onGetAllByStatus(int status) {
        return bookDao.getAllByStatus(status);
    }

    @Override
    public Book onShowStatus(int id) {
        return bookDao.showStatus(id);
    }

    @Override
    public void onUpdateStatus(int id, int status) {
        bookDao.updateStatus(id, status);
    }
}
