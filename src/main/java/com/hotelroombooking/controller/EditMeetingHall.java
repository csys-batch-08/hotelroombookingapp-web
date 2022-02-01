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
 * Servlet implementation class editMeetingHall
 */
@WebServlet("/EditMeetingHall")
public class EditMeetingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMeetingHall() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int meetingHallNumber = Integer.parseInt(request.getParameter("meetingHallNumber"));
			String category = request.getParameter("category");
			String location = request.getParameter("location");
			int price = Integer.parseInt(request.getParameter("price"));
			
			MeetingHallDetails meetingHallDetailsObj = new MeetingHallDetails(meetingHallNumber,null,category,location,price);
			MeetingHallTransactionDaoImpl meetingHallTransDaoObj = new MeetingHallTransactionDaoImpl();
			HttpSession session = request.getSession();
			session.setAttribute("editMeetingHallDetails", meetingHallDetailsObj);
			boolean flag = meetingHallTransDaoObj.updateMeetingHallAdmin(session);

			
			
			if(flag)
			{
			  
				response.sendRedirect("adminDashboard.jsp");
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
		
		
		doGet(request, response);
		
	}

}
