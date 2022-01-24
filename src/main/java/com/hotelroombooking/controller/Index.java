package com.hotelroombooking.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String email  = request.getParameter("email");
		String password  = request.getParameter("password");
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		AdminDaoImpl adminDaoObj = new AdminDaoImpl();
		Guest guestObj=guestDaoObj.loginGuest(email, password);
		Admin adminObj=adminDaoObj.loginAdmin(email, password);
//		System.out.println(email+password);
		PrintWriter pw = response.getWriter();
//		pw.write("welcome"+guestObj.getFirstName());
		
		if(guestObj!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("currentUser",guestObj);
			
			response.sendRedirect("GuestDashboard.jsp");
		}
		else if(adminObj!=null)
		{
//			HttpSession session=request.getSession();
//			session.setAttribute("currentUser",adminObj);
			
			response.sendRedirect("AdminDashboard.jsp");
		}
		else
		{
			
			HttpSession session=request.getSession();
			session.setAttribute("invalidLogin", "invalid");
			response.sendRedirect("Index.jsp");
		}
		
		
	}

}