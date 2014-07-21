package com.baf.server.util;

import javax.servlet.http.HttpServletRequest;

public class ArgumentUtil {
	
	public static final String PARAMETER_NAME_CONTENT = "content";
	
	public static String getContent(HttpServletRequest req) {
		return req.getParameter(PARAMETER_NAME_CONTENT);
	}
	
	public static String bytesToHexString(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for(byte b : data) {
			String str = String.format("%02x", b);
			sb.append(str);
		}
		return sb.toString();
	}
}
