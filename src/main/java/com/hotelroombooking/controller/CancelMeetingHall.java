package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl;
import com.hotelroombooking.model.MeetingHallTransaction;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int meetingHallNumber = Integer.parseInt(request.getParameter("meetingHallNumber"));
			MeetingHallTransaction meetingHallTransObj = new MeetingHallTransaction(meetingHallNumber, null, null, null,
					null);
			MeetingHallTransactionDaoImpl meetingHallTransDaoObj = new MeetingHallTransactionDaoImpl();
			HttpSession session = request.getSession();
			session.setAttribute("cancelMeetingHallDetails", meetingHallTransObj);
			boolean flag = meetingHallTransDaoObj.cancelMeetingHall(session);
			if (flag) {
				response.sendRedirect("guestDashboard.jsp");
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
