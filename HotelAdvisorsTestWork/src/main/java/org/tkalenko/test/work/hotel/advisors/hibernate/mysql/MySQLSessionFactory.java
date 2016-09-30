package org.tkalenko.test.work.hotel.advisors.hibernate.mysql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MySQLSessionFactory {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private MySQLSessionFactory() {
    }

    protected static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
            throw new ExceptionInInitializerError("Initial SQLiteSessionFactory failed" + e);
        }
        return sessionFactory;
    }

    public static SessionFactory get() {
        return sessionFactory;
    }

    public static void shutdown() {
        get().close();
    }
}
