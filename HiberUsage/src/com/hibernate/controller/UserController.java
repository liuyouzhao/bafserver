package com.hibernate.controller;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hibernate.api.API;
import com.hibernate.model.User;
import com.hibernate.model.UserAccess;
import com.hibernate.security.Checker;

public class UserController {
		
	public static User getUser(String username, Session session) {
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		ArrayList list = (ArrayList) criteria.list();
		if(list.size() > 1) {
			System.out.println("[ERROR: ] doLogin " + username + " contains more than one scores!");
			return null;
		}
		else if(list.size() == 0) {
			return null;
		}
		User user = (User) list.get(0);
		return user;
	}
	
	public static User getUser(String username) {
		Session session = API.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		ArrayList list = (ArrayList) criteria.list();
		if(list.size() > 1) {
			System.out.println("[ERROR: ] doLogin " + username + " contains more than one scores!");
			return null;
		}
		else if(list.size() == 0) {
			return null;
		}
		User user = (User) list.get(0);
		session.close();
		return user;
	}
	
	public static UserAccess getUserAccess(String username, Session session) {
		Criteria criteria = session.createCriteria(UserAccess.class);
		criteria.add(Restrictions.eq("username", username));
		ArrayList list = (ArrayList) criteria.list();
		if(list.size() == 0) {
			return null;
		}
		UserAccess ua = (UserAccess) list.get(0);
		return ua;
	}
	
	public static UserAccess getUserAccess(String username) {
		Session session = API.openSession();
		Criteria criteria = session.createCriteria(UserAccess.class);
		criteria.add(Restrictions.eq("username", username));
		ArrayList list = (ArrayList) criteria.list();
		session.close();
		if(list.size() == 0) {
			return null;
		}
		UserAccess ua = (UserAccess) list.get(0);
		return ua;
	}
	
	public static String genTempAccess() {
		return Checker.generateAccessKey();
	}
	
	public static boolean saveTempAccess(String username, String accessKey) {
		Session session = null;
		Transaction tx = null;
		try {
			session = API.openSession();
			tx = session.beginTransaction();
			UserAccess ua = getUserAccess(username, session);
			
			if(ua == null) {
				ua = new UserAccess();
				ua.setUsername(username);
			}
			ua.setUseraccess(accessKey);
			session.save(ua);
			tx.commit();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}
		finally {
			if(session != null)
				session.close();
		}
	}
	
	public static boolean saveTempAccess(String username, String accessKey, Session session) {
		Transaction tx = null;
		try {
			session = API.openSession();
			tx = session.beginTransaction();
			UserAccess ua = getUserAccess(username, session);
			
			if(ua == null) {
				ua = new UserAccess();
				ua.setUsername(username);
			}
			ua.setUseraccess(accessKey);
			session.save(ua);
			tx.commit();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}
	}
	
	public static boolean saveUser(String username, String passwordMd5) {
		Session session = null;
		Transaction tx = null;
		try {
			session = API.openSession();
			tx = session.beginTransaction();
			
			User user = getUser(username, session);
			
			if(user != null) {
				return false;
			}
			
			user = new User();
			user.setUsername(username);
			user.setPassword(passwordMd5);
			
			session.save(user);
			tx.commit();
			
			return true;
			
		} catch(Exception ex) {
			ex.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}
		finally {
			if(session != null)
				session.close();
		}
	}
	
	public static boolean saveUser(String username, String passwordMd5, Session session) {
		Transaction tx = null;
		try {
			session = API.openSession();
			tx = session.beginTransaction();
			
			User user = getUser(username, session);
			
			if(user != null) {
				return false;
			}
			
			user = new User();
			user.setUsername(username);
			user.setPassword(passwordMd5);
			
			session.save(user);
			tx.commit();
			
			return true;
			
		} catch(Exception ex) {
			ex.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}
	}
	
	public static boolean saveLoginState(String username, boolean logined) {
		Session session = null;
		Transaction tx = null;
		try {
			session = API.openSession();
			tx = session.beginTransaction();
			User user = getUser(username, session);
			
			if(user == null) {
				session.close();
				return false;
			}
			
			user.setLogined(logined ? 1 : 0);
			session.update(user);
			tx.commit();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}
		finally {
			if(session != null)
				session.close();
		}
	}
	
	public static boolean saveLoginState(String username, boolean logined, Session session) {
		Transaction tx = null;
		try {
			session = API.openSession();
			tx = session.beginTransaction();
			User user = getUser(username, session);
			
			if(user == null) {
				session.close();
				return false;
			}
			
			user.setLogined(logined ? 1 : 0);
			session.update(user);
			tx.commit();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			if(tx != null) {
				tx.rollback();
			}
			return false;
		}
	}
	
	public static boolean checkPassword(String username, byte[] somethingFromClient, byte[] accessKeyAfterEncrypt) {
		Session session = API.openSession();
		User user = getUser(username, session);
		String md5Pass = user.getPassword();
		boolean userLoginSucceed = false;
		try {
			userLoginSucceed = Checker.checkPassword(md5Pass.getBytes(), somethingFromClient, accessKeyAfterEncrypt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return userLoginSucceed;
	}
	
	public static boolean checkPassword(String username, byte[] somethingFromClient, byte[] accessKeyAfterEncrypt, Session session) {
		User user = getUser(username, session);
		String md5Pass = user.getPassword();
		boolean userLoginSucceed = false;
		try {
			userLoginSucceed = Checker.checkPassword(md5Pass.getBytes(), somethingFromClient, accessKeyAfterEncrypt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLoginSucceed;
	}
	
	public static boolean userValid(String username, Session session) {
		User user = getUser(username, session);
		return user != null && user.getDeleted() == 0;
	}
	
	public static String getPasswordFromDatabase(String username, Session session) {
		User user = getUser(username, session);
		if(user == null) {
			return null;
		}
		return user.getPassword();
	}
	
}
