package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl;
import com.hotelroombooking.model.WeddingHallTransaction;

/**
 * Servlet implementation class cancelWeddingHall
 */
@WebServlet("/CancelWeddingHall")
public class CancelWeddingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelWeddingHall() {
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
			int weddingHallNumber = Integer.parseInt(request.getParameter("weddingHallNumber"));
			WeddingHallTransaction weddingHallTransObj = new WeddingHallTransaction(weddingHallNumber, null, null, null,
					null);
			WeddingHallTransactionDaoImpl weddingHallTransDaoObj = new WeddingHallTransactionDaoImpl();
			HttpSession session = request.getSession();
			session.setAttribute("cancelWeddingHallDetails", weddingHallTransObj);
			boolean flag = weddingHallTransDaoObj.cancelWeddingHall(session);
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
