package com.myapp.helper;

import java.util.logging.Level;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myapp.model.Message;
import com.myapp.model.User;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
        	java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF );
        	
            sessionFactory = new  Configuration()
            		.configure("hibernate.cfg.xml")
            		.addPackage("com.myapp.model")
	            	.addAnnotatedClass(Message.class)
	            	.addAnnotatedClass(User.class)
            		.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    private HibernateUtil() {};
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}