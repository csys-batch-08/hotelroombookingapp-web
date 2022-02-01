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
 * Servlet implementation class bookWeedingHall
 */
@WebServlet("/BookWeddingHall")
public class BookWeedingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookWeedingHall() {
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
		
		String checkIn = request.getParameter("checkIn");
		String checkOut = request.getParameter("checkOut");
		String category = request.getParameter("category");
		String location = request.getParameter("location");
		
		WeddingHallTransaction weddingHallTransObj = new WeddingHallTransaction(0,checkIn,checkOut,category,location);
		WeddingHallTransactionDaoImpl weddingHallTransDaoObj = new WeddingHallTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("bookWeddingHallDetails", weddingHallTransObj);
		Integer bookWeddingPrice = weddingHallTransDaoObj.findBookWeddingPrice(session);
		session.setAttribute("bookWeddingPrice", bookWeddingPrice);
		weddingHallTransDaoObj.bookWeddingHall(session);
		
		
		if(session.getAttribute("NoWeddingHallToBook")!=null) {
			response.sendRedirect("guestDashboard.jsp");
			
		}
		else {
		
		response.sendRedirect("bookWeddingHallPayment.jsp");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
