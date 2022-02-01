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
 * Servlet implementation class updateweddingHall
 */
@WebServlet("/UpdateWeddingHall")
public class UpdateWeddingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWeddingHall() {
        super();
        // TODO Auto-generated constructor stub
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
		
		int weddingHallNumber = Integer.parseInt(request.getParameter("weddingHallNumber"));
		String checkIn = request.getParameter("checkIn");
		String checkOut = request.getParameter("checkOut");
		String category = request.getParameter("category");
		String location = request.getParameter("location");
		
		WeddingHallTransaction weddingHallTransObj = new WeddingHallTransaction(weddingHallNumber,checkIn,checkOut,category,location);
		WeddingHallTransactionDaoImpl weddingHallTransDaoObj = new WeddingHallTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("updateWeddingHallDetails", weddingHallTransObj);
		Integer updateWeddingPrice = weddingHallTransDaoObj.findUpdateWeddingPrice(session);
		session.setAttribute("updateWeddingPrice", updateWeddingPrice);
		
		weddingHallTransDaoObj.updateWeddingHall(session);
		
		
		
		if(session.getAttribute("noWeddingHallsToUpdate")!=null) {
			response.sendRedirect("guestDashboard.jsp");
			
		}
		else {
		
		response.sendRedirect("updateWeddingHallPayment.jsp");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
