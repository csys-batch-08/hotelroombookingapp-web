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
 * Servlet implementation class updateMeetingHall
 */
@WebServlet("/UpdateMeetingHall")
public class UpdateMeetingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMeetingHall() {
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
			String checkIn = request.getParameter("checkIn");
			String checkOut = request.getParameter("checkOut");
			String category = request.getParameter("category");
			String location = request.getParameter("location");

			MeetingHallTransaction meetingHallTransObj = new MeetingHallTransaction(meetingHallNumber, checkIn,
					checkOut, category, location);
			MeetingHallTransactionDaoImpl meetingHallTransDaoObj = new MeetingHallTransactionDaoImpl();
			HttpSession session = request.getSession();
			session.setAttribute("updateMeetingHallDetails", meetingHallTransObj);
			Integer updateMeetingPrice = meetingHallTransDaoObj.findUpdateMeetingPrice(session);
			session.setAttribute("updateMeetingPrice", updateMeetingPrice);

			meetingHallTransDaoObj.updateMeetingHall(session);

			if (session.getAttribute("noMeetingHallsToUpdate") != null) {
				response.sendRedirect("guestDashboard.jsp");

			} else {
				response.sendRedirect("updateMeetingHallPayment.jsp");
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
