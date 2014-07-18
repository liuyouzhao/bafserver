package com.baf.server.util;

import javax.servlet.http.HttpServletRequest;

public class ArgumentUtil {
	
	public static final String PARAMETER_NAME_CONTENT = "content";
	
	public static String getContent(HttpServletRequest req) {
		return req.getParameter(PARAMETER_NAME_CONTENT);
	}
}
