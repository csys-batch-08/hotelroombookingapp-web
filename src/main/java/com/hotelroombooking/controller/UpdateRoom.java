package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.RoomTransactionDaoImpl;
import com.hotelroombooking.model.RoomTransaction;

/**
 * Servlet implementation class updateRoom
 */
@WebServlet("/UpdateRoom")
public class UpdateRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateRoom() {
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
			int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
			String checkIn = request.getParameter("checkIn");
			String checkOut = request.getParameter("checkOut");
			String category = request.getParameter("category");
			String location = request.getParameter("location");
			RoomTransaction roomTransObj = new RoomTransaction(roomNumber, checkIn, checkOut, category, location);
			RoomTransactionDaoImpl roomTransDaoObj = new RoomTransactionDaoImpl();
			HttpSession session = request.getSession();
			session.setAttribute("updateRoomDetails", roomTransObj);
			Integer updateRoomPrice = roomTransDaoObj.findUpdateRoomPrice(session);
			session.setAttribute("updateRoomPrice", updateRoomPrice);
			roomTransDaoObj.updateRoom(session);
			if (session.getAttribute("noRoomsToUpdate") != null) {
				response.sendRedirect("guestDashboard.jsp");
			} else {
				response.sendRedirect("updateRoomPayment.jsp");
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
