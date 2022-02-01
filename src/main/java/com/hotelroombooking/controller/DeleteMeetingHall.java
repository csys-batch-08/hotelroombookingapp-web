package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl;
import com.hotelroombooking.model.MeetingHallDetails;

/**
 * Servlet implementation class deleteMeetingHall
 */
@WebServlet("/DeleteMeetingHall")
public class DeleteMeetingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMeetingHall() {
        super();
    }

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
		
		MeetingHallDetails meetingHallDetailsObj = new MeetingHallDetails(meetingHallNumber,null,null,null,0);
		MeetingHallTransactionDaoImpl meetingHallTransDaoObj = new MeetingHallTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("deleteMeetingHallDetails", meetingHallDetailsObj);
		boolean flag = meetingHallTransDaoObj.deleteMeetingHallAdmin(session);

		
		
		if(flag)
		{
		  
			response.sendRedirect("adminDashboard.jsp");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
