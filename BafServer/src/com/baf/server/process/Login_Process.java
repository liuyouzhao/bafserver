package com.baf.server.process;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baf.server.data.BaseObject;
import com.baf.server.data.Login_Object;
import com.baf.server.secure.BafLoginChecker;
import com.baf.server.util.ArgumentUtil;
import com.baf.server.util.ErrorUtil;
import com.baf.server.util.ServerUtil;
import com.hibernate.api.user.LoginAPI;
import com.hibernate.security.CryptUtil;

public class Login_Process extends BaseProcess {
	
	/// code
	private static String STATE_LOGIN_SUCCEED = "0";
	private static String STATE_LOGIN_FAILED = "1";
	
	/// msg
	private static String MSG_LOGIN_ALREADY_LOGINED = "MSG_LOGIN_ALREADY_LOGINED";
	private static String MSG_LOGIN_SUCCEED_MSG = "MSG_LOGIN_SUCCEED_MSG";
	private static String MSG_LOGIN_ACCESS_INVALIDATE = "MSG_LOGIN_ACCESS_INVALIDATE";
	private static String MSG_LOGIN_VERYCODE_INVALIDATE = "MSG_LOGIN_VERYCODE_INVALIDATE";
	private static String MSG_LOGIN_VERYCODE_NOT_CREATED = "MSG_LOGIN_VERYCODE_NOT_CREATED";
	private static String MSG_LOGIN_USERNAME_NOT_EXISTS = "MSG_LOGIN_USERNAME_NOT_EXISTS";
	private static String MSG_LOGIN_PASSWORD_WRONG = "MSG_LOGIN_PASSWORD_WRONG";
	
	/// data
	private static String DATA_ACCESS_NOT_REQUESTED = "You need to request access before you really login";
	private static String DATA_VERYCODE_NOT_CREATED = "The server has not created the very-code for you to login";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String content = ArgumentUtil.getContent(request);
		System.out.println("getParameter: " + content);
		
		
		/// 1. Check if it has already logined, though what will never be.
		Login_Object login = (Login_Object) BaseObject.getFromCache(request.getSession(), Login_Object.class);
		if(login != null) {
			return ServerUtil.responseStateStringToClient(response, login.username, 
					STATE_LOGIN_FAILED, MSG_LOGIN_ALREADY_LOGINED, DATA_NULL);
		}
		// ------------------------------------------------------------------------------
		
		
		
		/// 2. Get the very-code from session that be created earlier
		String verycode = (String) request.getSession().getAttribute("verycode");
		if(verycode == null || verycode.length() == 0) {
			return ServerUtil.responseStateStringToClient(response, "null", 
					STATE_LOGIN_FAILED, MSG_LOGIN_VERYCODE_NOT_CREATED, DATA_VERYCODE_NOT_CREATED);
		}
		// ------------------------------------------------------------------------------
		
		
		
		/// 3. Wrap Login Object, the login is null before this line.
		login = (Login_Object) BaseObject.create(Login_Object.class, content);
		// ------------------------------------------------------------------------------
		
		
		
		/// 4. Check very-code [XXXX], if passed, invalid it!
		if(!verycode.equals(login.verycode)) {
			return ServerUtil.responseStateStringToClient(response, login.username, 
					STATE_LOGIN_FAILED, MSG_LOGIN_VERYCODE_INVALIDATE, DATA_NULL);
		}
		request.getSession().removeAttribute("verycode");
		// ------------------------------------------------------------------------------
		
		
		
		/// 5. The user is exist ?
		LoginAPI api = new LoginAPI(); 		
		if(!api.nameExistAndValid(login.username)) {
			return ServerUtil.responseStateStringToClient(response, login.username, 
					STATE_LOGIN_FAILED, MSG_LOGIN_USERNAME_NOT_EXISTS, DATA_NULL);
		}
		// ------------------------------------------------------------------------------
		
		
		/// 5. Let's check the access
		String access = BafLoginChecker.getCurrentAccess(request.getSession());
		if(access == null) {
			return ServerUtil.responseStateStringToClient(response, login.username, 
					STATE_LOGIN_FAILED, MSG_LOGIN_ACCESS_INVALIDATE, DATA_ACCESS_NOT_REQUESTED);
		}
		// ------------------------------------------------------------------------------
		
		
		/// 6. Get password here
		String password = api.getPasswordMd5(login.username);
		// ------------------------------------------------------------------------------
		
		
		
		/// 7. Check the access
		boolean success = false;
		try {
			success = BafLoginChecker.checkPassword(password.getBytes(), login.passkey.getBytes(), access.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorUtil.error(response, "null", ErrorUtil.ERROR_CODE_LOGIN_EXCEPTION, "null", content);
		}
		// ------------------------------------------------------------------------------
		
		
		
		/// 8. Neither checking successfully or not, delete the access
		BafLoginChecker.invalidAccess(request.getSession());
		// ------------------------------------------------------------------------------
		
		
		
		/// 9. If succeed!?
		if(!success) {
			return ServerUtil.responseStateStringToClient(response, login.username, 
					STATE_LOGIN_FAILED, MSG_LOGIN_PASSWORD_WRONG, DATA_ACCESS_NOT_REQUESTED);
		}
		// ------------------------------------------------------------------------------
		
		
		
		/// 10. Congratulations! You are PASSED! Let session knows.
		if( !login.doCache(login.getName(), request.getSession()) ) {
			return ErrorUtil.error(response, login.username,
					ErrorUtil.ERROR_CODE_LOGIN_EXCEPTION, "Session Cached Failed..", content);
		}
		try {
			login._activity = CryptUtil.initKey();
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorUtil.error(response, login.username,
					ErrorUtil.ERROR_CODE_LOGIN_EXCEPTION, "Generate key Failed...", content);
		}
		// ------------------------------------------------------------------------------
		
		
		/// 11. Save the state to database
		api.requestLogin(login.username);
		// ------------------------------------------------------------------------------

		/// 12. OY! Let's send back to client!
		return ServerUtil.responseStateStringToClient(response, login.username, 
				STATE_LOGIN_SUCCEED, MSG_LOGIN_SUCCEED_MSG, DATA_NULL);
	}

}
