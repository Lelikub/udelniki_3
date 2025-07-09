package com.udel.dataMiner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SQLiteMiner {
    private static SessionFactory sessionFactory = null;

    static{
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } 
        catch (Exception ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
