package com.baf.server.data;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

/**
 * 
 * @author hujia.liuhj
 *
 * The purpose is to do the conversation between String&IBafObject,
 * the target is based on request process, not database structure.
 * 
 * For example:
 * If your request hands up a data like:
 * "{'username':'harvey','password':'xxxx'}"(content) in RegisterProcess
 * Then:
 * There have to be a Class named RegisterObject for  RegisterProcess, 
 * RegisterObject will be filled in RegisterProcess, and set to the Session object for cache.
 */
public class BaseObject {
	protected JSONObject jobj;
	
	public static BaseObject create(Class cls, String content)  {
		try {
			return ((BaseObject) cls.newInstance()).wrap(content);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BaseObject getFromCache(HttpSession session, Class cls) {
		try {
			return (BaseObject) session.getAttribute(cls.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public BaseObject wrap(String content) {
		return null;
	}
	
	public String unwrap() {	return jobj.toString(); }
	
	public boolean isEmpty() {	return jobj == null;	}
	public String getName() {	return this.getClass().getName();	}

	public boolean doCache(String name, HttpSession session) {
		try {
			String n = name == null ? getName() : name;
			session.setAttribute(n, this);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	
}
