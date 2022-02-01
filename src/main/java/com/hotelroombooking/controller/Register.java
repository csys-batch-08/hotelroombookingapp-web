package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelroombooking.daoimpl.GuestDaoImpl;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			String firstName=request.getParameter("firstName");	
			String 	lastName = request.getParameter("lastName");
			String email  = request.getParameter("email");
			String password  = request.getParameter("password");
			long mobileNumber = Long.parseLong(request.getParameter("phoneNumber"));
				
				GuestDaoImpl guestDaoObj = new GuestDaoImpl();
				boolean flag=guestDaoObj.registerGuest(firstName, lastName, email, password, password, mobileNumber);
				
				
				if(flag)
				{
				  
					response.sendRedirect("login.jsp");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	
		try {
		
		
		doGet(request, response);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}

}
