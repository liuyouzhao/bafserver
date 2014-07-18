package com.baf.server.process;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baf.server.data.BaseObject;
import com.baf.server.data.CheckUserName_Object;
import com.baf.server.data.Register_Object;
import com.baf.server.util.ArgumentUtil;
import com.baf.server.util.ServerUtil;
import com.hibernate.api.BaseAPI;
import com.hibernate.api.user.RegisterAPI;

public class CheckUserName_Process extends BaseProcess {
	/// code
	private static String STATE_CHECK_SUCCEED = "0";
	private static String STATE_CHECK_FAILED = "1";
	
	/// msg
	private static String MSG_CHECK_OK = "STATE_CHECK_SUCCEED";
	private static String MSG_CHECK_ALREADY_EXISTS = "STATE_CHECK_ALREADY_EXISTS";
	private static String MSG_CHECK_INVALID_STRING = "STATE_CHECK_INVALID_STRING";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String content = ArgumentUtil.getContent(request);
		System.out.println("getParameter: " + content);
		
		CheckUserName_Object nameChecker = (CheckUserName_Object) BaseObject.create(CheckUserName_Object.class, content);
		RegisterAPI api = (RegisterAPI) BaseAPI.create(RegisterAPI.class);

		if(checkUserName(nameChecker.username)) {
			if(api.nameExist(nameChecker.username)) {
				return ServerUtil.responseStateStringToClient(response, "null", 
						STATE_CHECK_FAILED, MSG_CHECK_ALREADY_EXISTS, "null");
			}
			
			return ServerUtil.responseStateStringToClient(response, "null", 
					STATE_CHECK_SUCCEED, MSG_CHECK_OK, "null");
		}
		return ServerUtil.responseStateStringToClient(response, "null", 
				STATE_CHECK_FAILED, MSG_CHECK_INVALID_STRING, "null");
	}
	
	public static boolean checkUserName(String userName) {
		String regex = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5])+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(userName);
		return m.matches();
	}
}
