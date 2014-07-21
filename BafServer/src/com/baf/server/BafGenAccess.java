package com.baf.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baf.server.secure.BafLoginChecker;
import com.baf.server.util.ServerUtil;

/**
 * Servlet implementation class BafGenAccess
 */
@WebServlet("/BafGenAccess")
public class BafGenAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BafGenAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String access = BafLoginChecker.generateAccessAndSave(request.getSession());
		ServerUtil.responseStateStringToClient(response, "null", "ok", "give the access", access);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String access = BafLoginChecker.generateAccessAndSave(request.getSession());
		ServerUtil.responseStateStringToClient(response, "null", "ok", "give the access", access);
	}

}
