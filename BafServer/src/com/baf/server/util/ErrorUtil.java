package com.baf.server.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ErrorUtil {
	
	public static String ERROR_CODE_REGISTER_EXCEPTION = "error.code.register.exception";
	public static String ERROR_CODE_LOGIN_EXCEPTION = "error.code.login.exception";
	
	public static String ERROR_JSON_STRING = "{'username':'%s', 'error':'%s','errormsg':'%s','yours':'%s'}";
	
	public static String error(HttpServletResponse response, String username, String code, String msg, String yours) {
		String content = String.format(ERROR_JSON_STRING, username, code, msg, yours);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(pw != null) {
				pw.close();
			}
		}
		return content;
	}
}
