package com.baf.server.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ServerUtil {
	
	public static String RETURN_JSON_STRING = "{\"username\":\"%s\",\"state\":\"%s\",\"msg\":\"%s\",\"data\":\"%s\"}";
	
	public static String responseStringToClient(HttpServletResponse response, String content) throws IOException {
		PrintWriter pw = response.getWriter();
		pw.println(content);
		pw.close();
		
		return content;
	}
	
	public static String responseStateStringToClient(HttpServletResponse response, String username, String state, String msg, String data) throws IOException {
		PrintWriter pw = response.getWriter();
		String content = String.format(RETURN_JSON_STRING, username, state, msg, data);
		pw.println(content);
		pw.close();
		return content;
	}
}