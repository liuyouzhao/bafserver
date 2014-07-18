package com.hibernate.api.user;

import com.hibernate.api.BaseAPI;
import com.hibernate.controller.UserController;

public class LoginAPI extends BaseAPI  {
		
	public boolean requestLogin(String username) {
		return UserController.saveLoginState(username, true, session);
	}
	
	public boolean nameExistAndValid(String username) {
		return UserController.userValid(username, session);
	}
	
	public String getPasswordMd5(String username) {
		return UserController.getPasswordFromDatabase(username, session);
	}
}
