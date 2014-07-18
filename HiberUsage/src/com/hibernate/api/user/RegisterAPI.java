package com.hibernate.api.user;

import org.hibernate.Session;

import com.hibernate.api.API;
import com.hibernate.api.BaseAPI;
import com.hibernate.controller.UserController;

public class RegisterAPI extends BaseAPI  {
	public boolean nameExist(String username) {
		return UserController.getUser(username, session) != null;
	}

	public boolean registNewUser(String username, String passwordMd5) {
		UserController.saveUser(username, passwordMd5, session);
		return false;
	}
}