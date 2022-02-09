package com.hotelroombooking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl;
import com.hotelroombooking.daoimpl.RoomTransactionDaoImpl;
import com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.MeetingHallTransaction;
import com.hotelroombooking.model.RoomTransaction;
import com.hotelroombooking.model.WeddingHallTransaction;

/**
 * Servlet implementation class Bookings
 */
@WebServlet("/Bookings")
public class Bookings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bookings() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Guest guestObj = (Guest) session.getAttribute("currentUser");
		RoomTransactionDaoImpl roomTransDaoImpl = new RoomTransactionDaoImpl();
		WeddingHallTransactionDaoImpl weddingHallDaoImpl = new WeddingHallTransactionDaoImpl();
		MeetingHallTransactionDaoImpl meetingHallDaoImpl = new MeetingHallTransactionDaoImpl();
		try {
			List<RoomTransaction> roomList = roomTransDaoImpl.showRoomBooking(guestObj);
			List<WeddingHallTransaction> weddingHallList = weddingHallDaoImpl.showWeddingHallBooking(guestObj);
			List<MeetingHallTransaction> meetingHallList = meetingHallDaoImpl.showMeetingHallBooking(guestObj);
			request.setAttribute("myRoomBookings", roomList);
			request.setAttribute("myWeddingHallBookings", weddingHallList);
			request.setAttribute("myMeetingHallBookings", meetingHallList);
			RequestDispatcher rd = request.getRequestDispatcher("myBookings.jsp");
			rd.forward(request, response);
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
