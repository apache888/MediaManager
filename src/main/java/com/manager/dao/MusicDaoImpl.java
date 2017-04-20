package com.manager.dao;

import com.manager.model.Music;
import com.manager.model.MusicStatus;
import org.hibernate.Session;

import java.util.List;

/**
 * Created on 17.04.2017.
 *
 * @author Roman Hayda
 *
 * Class implements DAO layer for entity Music
 * contains CRUD methods
 */
public class MusicDaoImpl implements MediaDao<Music> {

    @Override
    public void add(Music music) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(music);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new RuntimeException("\nDuplicate entry \'" + music.getName() + "\' for key 'name_UNIQUE'\nInput another name");
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Music music = session.get(Music.class, id);
            session.delete(music);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID (" + e.getMessage() + ")");
        }
    }

    @Override
    public List<Music> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Music> musicList = session.createQuery("from Music", Music.class).getResultList();
        session.getTransaction().commit();
        return musicList;
    }

    @Override
    public List<Music> getAllByStatus(int status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Music> musicList = session.createQuery("from Music where status= :status", Music.class).setParameter("status", MusicStatus.values()[status]).getResultList();
        session.getTransaction().commit();
        return musicList;
    }

    @Override
    public Music showStatus(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Music music = (Music) session.createQuery("from Music m where id= :id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            return music;
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
            Music music = session.get(Music.class, id);
            music.setStatus(MusicStatus.values()[status]);
            session.update(music);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }
}
