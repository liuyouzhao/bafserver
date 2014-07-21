package com.baf.server.data;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class Register_Object extends BaseObject {

	private String[] ATTRIBUTE_NAMES = new String[] {
			"username",
			"password",
			"verycode"
	};
	public String username;
	public String password;
	public String verycode;
	
	private JSONObject jobj;
	
	@Override
	public BaseObject wrap(String content) {
		try {
			this.jobj = JSONObject.fromObject(content);
			this.username = (String) jobj.get(ATTRIBUTE_NAMES[0]);
			this.password = (String) jobj.get(ATTRIBUTE_NAMES[1]);
			this.verycode = (String) jobj.get(ATTRIBUTE_NAMES[2]);
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
