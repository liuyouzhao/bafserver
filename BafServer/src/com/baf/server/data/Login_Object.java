package com.baf.server.data;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class Login_Object extends BaseObject {
	
	private String[] ATTRIBUTE_NAMES = new String[] {
			"username",
			"passkey",
			"verycode",
			/// TODO: added much more upon ^^^:
	};
	public String username;
	public String passkey;
	public String verycode;
	
	/// TODO: added much more upon ^^^:
	
	/// Self attribute:
	public String _activity;
	
	@Override
	public BaseObject wrap(String content) {
		try {
			this.jobj = JSONObject.fromObject(content);
			this.username = (String) jobj.get(ATTRIBUTE_NAMES[0]);
			this.passkey = (String) jobj.get(ATTRIBUTE_NAMES[1]);
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
		// TODO Auto-generated method stub
		return super.unwrap();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return super.isEmpty();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public boolean doCache(String name, HttpSession session) {
		// TODO Auto-generated method stub
		return super.doCache(name, session);
	}

}
