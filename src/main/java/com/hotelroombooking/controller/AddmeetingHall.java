package com.hotelroombooking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl;
import com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl;
import com.hotelroombooking.model.MeetingHallDetails;
import com.hotelroombooking.model.WeddingHallDetails;

/**
 * Servlet implementation class addmeetingHall
 */
@WebServlet("/AddMeetingHall")
public class AddmeetingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		try {
		int meetingHallNumber = Integer.parseInt(request.getParameter("meetingHallNumber"));
		String category = request.getParameter("category");
		String location = request.getParameter("location");
		int price = Integer.parseInt(request.getParameter("price"));
		
		MeetingHallDetails meetingHallDetailsObj = new MeetingHallDetails(meetingHallNumber,null,category,location,price);
//		System.out.println("hi");
		MeetingHallTransactionDaoImpl meetingHallTransDao = new MeetingHallTransactionDaoImpl();
//		System.out.println("hii");
		HttpSession session = request.getSession();
		session.setAttribute("addMeetingHallDetails", meetingHallDetailsObj);
//		System.out.println("hy");
		boolean flag = meetingHallTransDao.addMeetingHallAdmin(session);
//		PrintWriter pw = response.getWriter();
//		pw.write(flag+"");
		
		
		if(flag)
		{
		  
			response.sendRedirect("adminDashboard.jsp");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
//		doGet(request, response);
	}

}
