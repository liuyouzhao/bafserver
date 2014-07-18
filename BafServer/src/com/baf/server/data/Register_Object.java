package com.baf.server.data;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class Register_Object extends BaseObject {

	private String[] ATTRIBUTE_NAMES = new String[] {
			"username",
			"password"
	};
	public String username;
	public String password;
	private JSONObject jobj;
	
	@Override
	public BaseObject wrap(String content) {
		try {
			this.jobj = JSONObject.fromObject(content);
			String username = (String) jobj.get(ATTRIBUTE_NAMES[0]);
			String password = (String) jobj.get(ATTRIBUTE_NAMES[1]);
			this.username = username;
			this.password = password;
			return this;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public String unwrap() {
		return super.unwrap();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return jobj == null || username == null || password == null;
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
