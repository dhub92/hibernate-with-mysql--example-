package com.david.hibernateUtils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Utils of HIbernate that allow to access the DB
 * @author David Moreno
 * @version 1.0
 * @since 1.0
 */
public class HibernateUtils {
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
	/**
	 * Create a sessionFactory object with the parameters specified on hibernate.cfg.xml
	 * @return SessionFactory object
	 */
	private static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable e){
			System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
		}
		return sessionFactory;
	}
	/**
	 * Retrieve a SessionFactory object
	 * @return SessionFactory object
	 */
	public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
 
	/**
	 * Close the connection to the DB
	 */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
