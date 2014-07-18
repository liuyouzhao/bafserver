package com.hibernate.usage;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.hibernate.model.User;

import antlr.collections.List;

public class Main {
	
	private static SessionFactory sessionFactory;
	
	public static void add(Session session) {
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setId(1);
		u.setUsername("hujia");
		u.setPassword("8ash18dakshj12dsa");
	
		session.save(u);
		tx.commit();
	}
	
	public static void delete(Session session) {
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setId(1);
		session.delete(u);
		tx.commit();
	}
	
	public static void update(Session session) {
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setId(1);
		u.setUsername("hujia");
		u.setPassword("8ash18dakshj12dsa");
	
		session.save(u);
		tx.commit();
	}
	
	public static void search(Session session, boolean isGet) {
		if(isGet) {
			User user = (User) session.get(User.class, 2);
			System.out.println(user.toString());
			return;
		}
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", "hujia"));
		ArrayList list = (ArrayList) criteria.list();
		for(int i = 0; i < list.size(); i ++) {
			User u = (User) list.get(i);
			System.out.println(u.toString());
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configure = new Configuration();
		//ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configure.getProperties()).build();
		sessionFactory = configure.configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		search(session, true);
		
		sessionFactory.close();
	}

}
