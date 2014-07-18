package com.baf.server.data;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class CheckUserName_Object  extends BaseObject {

	private String[] ATTRIBUTE_NAMES = new String[] {
			"username"
	};
	public String username;
	private JSONObject jobj;
	@Override
	public BaseObject wrap(String content) {
		try {
			this.jobj = JSONObject.fromObject(content);
			this.username = (String) jobj.get(ATTRIBUTE_NAMES[0]);
			System.out.println(username);
			return this;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return super.wrap(content);
	}

	@Override
	public String unwrap() {
		return super.unwrap();
	}

	@Override
	public boolean isEmpty() {
		return username == null || jobj == null;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public boolean doCache(String name, HttpSession session) {
		return super.doCache(name, session);
	}

	

}
