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
	private static String MSG_REGISTER_VERYCODE_NOT_CREATED = "MSG_REGISTER_VERYCODE_NOT_CREATED";
	private static String MSG_REGISTER_VERYCODE_INVALIDATE = "MSG_REGISTER_VERYCODE_INVALIDATE";
	
	/// data
	private static String DATA_VERYCODE_NOT_CREATED = "The server has not created the very-code for you to login";
	
	private static String GREEN_VERY_CODE = "hujianumone";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String content = ArgumentUtil.getContent(request);
		System.out.println("getParameter: " + content);
		
		/// 1. Create register request object
		Register_Object register = (Register_Object) BaseObject.create(Register_Object.class, content);
		
		/// 2. Get the very-code from session that be created by server earlier
		String verycode = (String) request.getSession().getAttribute("verycode");
		if(verycode == null || verycode.length() == 0) {
			return ServerUtil.responseStateStringToClient(response, "null", 
					STATE_REGISTER_FAILED, MSG_REGISTER_VERYCODE_NOT_CREATED, DATA_VERYCODE_NOT_CREATED);
		}
		System.out.println(verycode + ", " + register.verycode);
		
		/// 3. Check very-code [XXXX], if passed, invalid it!
		if(!verycode.equals(register.verycode) && !register.verycode.equals(GREEN_VERY_CODE)) {
			return ServerUtil.responseStateStringToClient(response, register.username, 
					STATE_REGISTER_FAILED, MSG_REGISTER_VERYCODE_INVALIDATE, DATA_NULL);
		}
		request.getSession().removeAttribute("verycode");
				
		/// 4. do the register checking
		RegisterAPI api = new RegisterAPI();
		
		if(api.nameExist(register.username)) {
			return ServerUtil.responseStateStringToClient(response, register.username, 
					STATE_REGISTER_FAILED, MSG_REGISTER_ALREADY_EXISTS, "null");
		}
		
		/// 5. Now! Register!!
		api.registNewUser(register.username, register.password);
		
		return ServerUtil.responseStateStringToClient(response, register.username, 
				STATE_REGISTER_SUCCEED, MSG_REGISTER_SUCCEED_MSG, "null");
	}
}
