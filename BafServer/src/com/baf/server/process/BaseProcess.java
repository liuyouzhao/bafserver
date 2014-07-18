package com.baf.server.process;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseProcess {
	protected static String CODE_UNKNOW = "unknow";
	protected static String MSG_NULL = "null";
	protected static String DATA_NULL = "null";
	
	public static BaseProcess create(Class cls) throws InstantiationException, IllegalAccessException { return (BaseProcess) cls.newInstance(); }
	public String process(HttpServletRequest request, HttpServletResponse response)  throws IOException  {	return null; }
}
