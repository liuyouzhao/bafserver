package com.baf.server.process;

import javax.servlet.http.HttpServletRequest;

public class BafFormProcess {

	public String process(HttpServletRequest request) {
		return request.getParameter("content");
	}
}
