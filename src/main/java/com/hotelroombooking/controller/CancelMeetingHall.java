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
import com.hotelroombooking.model.MeetingHallTransaction;
import com.hotelroombooking.model.WeddingHallTransaction;

/**
 * Servlet implementation class cancelMeetingHall
 */
@WebServlet("/CancelMeetingHall")
public class CancelMeetingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelMeetingHall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		int meetingHallNumber = Integer.parseInt(request.getParameter("meetingHallNumber"));
		
		MeetingHallTransaction meetingHallTransObj = new MeetingHallTransaction(meetingHallNumber,null,null,null,null);
		MeetingHallTransactionDaoImpl meetingHallTransDaoObj = new MeetingHallTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("cancelMeetingHallDetails", meetingHallTransObj);
		boolean flag=meetingHallTransDaoObj.cancelMeetingHall(session);
//		PrintWriter pw = response.getWriter();
//		pw.write(flag+"");
		
		
		if(flag)
		{
			response.sendRedirect("guestDashboard.jsp");
		}
		
		
//		doGet(request, response);
	}

}
