package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.AdminDaoImpl;
import com.hotelroombooking.daoimpl.GuestDaoImpl;
import com.hotelroombooking.model.Admin;
import com.hotelroombooking.model.Guest;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			GuestDaoImpl guestDaoObj = new GuestDaoImpl();
			AdminDaoImpl adminDaoObj = new AdminDaoImpl();
			Guest guestObj = guestDaoObj.loginGuest(email, password);
			Admin adminObj = adminDaoObj.loginAdmin(email, password);
			if (guestObj != null) {
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", guestObj);
				response.sendRedirect("guestDashboard.jsp");
			} else if (adminObj != null) {
				response.sendRedirect("adminDashboard.jsp");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("invalidLogin", "invalid");
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
