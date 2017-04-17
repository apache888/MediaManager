package com.manager.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created on 16.04.2017.
 *
 * @author Roman Hayda
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /**
     * Method return static field sessionFactory referenced on SessionFactory object created by configuration
     * @return SessionFactory object
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Method shutdown SessionFactory object
     */
    public static void shutdown() {
        getSessionFactory().close();
    }

}
