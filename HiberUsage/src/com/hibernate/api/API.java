package com.hibernate.api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class API {
	private static Configuration configure;
	private static SessionFactory sessionFactory;
	private static boolean hasInited = false;
	
	public static boolean init() {
		try {
			configure = new Configuration();
			sessionFactory = configure.configure().buildSessionFactory();
			hasInited = true;
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
		return true;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session openSession() {
		return sessionFactory.openSession();
	}
}
