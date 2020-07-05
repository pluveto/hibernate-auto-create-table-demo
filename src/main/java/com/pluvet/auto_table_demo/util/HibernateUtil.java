package com.pluvet.auto_table_demo.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class HibernateUtil {
    private static SessionFactory factory = null;

    static {
        var serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }


    public static Session getSession() {
        Session ses = null;
        if (factory != null)
            ses = factory.openSession();
        return ses;
    }


    public static void closeSession(Session ses) {
        if (ses != null)
            ses.close();
    }


    public static void closeFactory() {
        if (factory != null)
            factory.close();
    }
}