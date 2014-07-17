package com.baf.server.api.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baf.server.process.BafFormProcess;

/**
 * Servlet implementation class FinanceAPI_Test
 */
@WebServlet("/FinanceAPI_Test")
public class API_Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public API_Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method= request.getParameter("method");
		if("com.baf.api.finance.get".equals(method)) {
			int last = Integer.parseInt(request.getParameter("last"));
			String result = com.hibernate.api.FinanceAPI.get(last);
			
			PrintWriter pw = response.getWriter();
			pw.println(result);
			pw.close();
		}
		else if("com.baf.api.user.register".equals(method)) {
			String result = new BafFormProcess().process(request);
			PrintWriter pw = response.getWriter();
			pw.println(result);
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
