package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;

		boolean isValidUser = validateUsernameAndPassword(username, password);

		if (isValidUser) {
			rd = request.getRequestDispatcher("/success"); //Valid credentials, calls success servlet to display dashboard
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("login.html"); // Invalid credentials display error message on login page
			PrintWriter out = response.getWriter();
			rd.include(request, response);
			out.println(
					("<center> <span style='color:red'>Invalid credentials. Please login with correct username and password.</span></center>"));
		}

	}

	private boolean validateUsernameAndPassword(String username, String password) {
		if (username.equalsIgnoreCase("admin") && password.equals("admin@123")) {
			return true;
		}
		return false;
	}

}
