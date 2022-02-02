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
 * Servlet implementation class cancelRoom
 */
@WebServlet("/CancelRoom")
public class CancelRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelRoom() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));

			RoomTransaction roomTransObj = new RoomTransaction(roomNumber, null, null, null, null);
			RoomTransactionDaoImpl roomTransDaoObj = new RoomTransactionDaoImpl();
			HttpSession session = request.getSession();
			session.setAttribute("cancelRoomDetails", roomTransObj);
			boolean flag = roomTransDaoObj.cancelRoom(session);

			if (flag) {
				response.sendRedirect("guestDashboard.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			doGet(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
