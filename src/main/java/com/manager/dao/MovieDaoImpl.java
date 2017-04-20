package com.manager.dao;

import com.manager.model.Movie;
import com.manager.model.MovieStatus;
import org.hibernate.Session;

import java.util.List;

/**
 * Created on 17.04.2017.
 *
 * @author Roman Hayda
 *
 * Class implements DAO layer for entity Movie
 * contains CRUD methods
 */
public class MovieDaoImpl implements MediaDao<Movie> {

    @Override
    public void add(Movie movie) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(movie);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new RuntimeException("\nDuplicate entry \'" + movie.getName() + "\' for key 'name_UNIQUE'\nInput another name");
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Movie movie = session.get(Movie.class, id);
            session.delete(movie);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID (" + e.getMessage() + ")");
        }
    }

    @Override
    public List<Movie> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Movie> movies = session.createQuery("from Movie", Movie.class).getResultList();
        session.getTransaction().commit();
        return movies;
    }

    @Override
    public List<Movie> getAllByStatus(int status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Movie> movies = session.createQuery("from Movie where status= :status", Movie.class).setParameter("status", MovieStatus.values()[status]).getResultList();
        session.getTransaction().commit();
        return movies;
    }

    @Override
    public Movie showStatus(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Movie movie = (Movie) session.createQuery("from Movie m where id= :id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            return movie;
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
            Movie movie = session.get(Movie.class, id);
            movie.setStatus(MovieStatus.values()[status]);
            session.update(movie);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }
}
