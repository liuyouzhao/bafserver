package com.hibernate.api;

import org.hibernate.Session;


public class BaseAPI {
	protected Session session;
	
	public BaseAPI() {
		session = API.openSession();
	}
	
	public void finalize() throws Throwable{            
		session.close();
        System.out.println(this + "finalize method was called!"); 
    }
	
	public static BaseAPI create(Class cls)  {
		try {
			return ((BaseAPI) cls.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
