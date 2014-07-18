package com.baf.server.process;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baf.server.data.BaseObject;
import com.baf.server.data.Register_Object;
import com.baf.server.util.ArgumentUtil;
import com.baf.server.util.ServerUtil;
import com.hibernate.api.user.RegisterAPI;

public class Register_Process extends BaseProcess {
	
	/// code
	private static String STATE_REGISTER_SUCCEED = "0";
	private static String STATE_REGISTER_FAILED = "1";
	
	/// msg
	private static String MSG_REGISTER_ALREADY_EXISTS = "STATE_CHECK_ALREADY_EXISTS";
	private static String MSG_REGISTER_SUCCEED_MSG = "STATE_REGISTER_SUCCEED_MSG";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String content = ArgumentUtil.getContent(request);
		System.out.println("getParameter: " + content);
		
		Register_Object register = (Register_Object) BaseObject.create(Register_Object.class, content);
		
		RegisterAPI api = new RegisterAPI();
		
		if(api.nameExist(register.username)) {
			return ServerUtil.responseStateStringToClient(response, register.username, 
					STATE_REGISTER_FAILED, MSG_REGISTER_ALREADY_EXISTS, "null");
		}
		
		api.registNewUser(register.username, register.password);
		register.doCache(null, request.getSession());
		
		return ServerUtil.responseStateStringToClient(response, register.username, 
				STATE_REGISTER_SUCCEED, MSG_REGISTER_SUCCEED_MSG, "null");
	}
}
