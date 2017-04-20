package com.manager.dao;

import com.manager.model.Book;
import com.manager.model.BookStatus;
import org.hibernate.Session;

import java.util.List;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 *
 * Class implements DAO layer for entity Book
 * contains CRUD methods
 */
public class BookDaoImpl implements MediaDao<Book> {

    @Override
    public void add(Book book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(book);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new RuntimeException("\nDuplicate entry \'" + book.getName() + "\' for key 'name_UNIQUE'\nInput another name");
        }
    }

    @Override
    public List<Book> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Book> books = session.createQuery("from Book", Book.class).getResultList();
        session.getTransaction().commit();
        return books;
    }

    @Override
    public Book showStatus(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Book book = (Book) session.createQuery("from Book b where id= :id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            return book;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }

    @Override
    public void updateStatus(int id, int status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Book book = session.get(Book.class, id);
            book.setStatus(BookStatus.values()[status]);
            session.update(book);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }

    @Override
    public List<Book> getAllByStatus(int status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Book> books = session.createQuery("from Book where status= :status", Book.class).setParameter("status", BookStatus.values()[status]).getResultList();
        session.getTransaction().commit();
        return books;
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Book book = session.get(Book.class, id);
            session.delete(book);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID (" + e.getMessage() + ")");
        }
    }

}
