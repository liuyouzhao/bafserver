package com.baf.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BafServerMain
 */
@WebServlet("/BafServerMain")
public class BafServerMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static int counter = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BafServerMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		counter = (int) (Math.random() * 100);
		System.out.println("----------------------- " + counter);
		if(counter % 2 == 0) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter pw = response.getWriter();
		pw.println("ok " + counter);
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
		boolean ok = com.hibernate.api.API.init();
		System.out.println("com.hibernate.api.API.init[" + ok + "]");
	}

}
