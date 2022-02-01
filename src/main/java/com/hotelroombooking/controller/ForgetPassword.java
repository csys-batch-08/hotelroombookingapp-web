package com.hotelroombooking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.GuestDaoImpl;
import com.hotelroombooking.model.Guest;

/**
 * Servlet implementation class forgetPassword
 */
@WebServlet("/Forgetpassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Guest guestObj = new Guest(null,null,email,password,0);
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("forgetPassword", guestObj);
		boolean flag = guestDaoObj.forgetPassword(session);
		
		
		if(flag)
		{
			response.sendRedirect("login.jsp");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
//		doGet(request, response);
	}

}
