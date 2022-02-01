package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl;
import com.hotelroombooking.model.WeddingHallDetails;

/**
 * Servlet implementation class editWeddingHall
 */
@WebServlet("/EditWeddingHall")
public class EditWeddingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditWeddingHall() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int weddingHallNumber = Integer.parseInt(request.getParameter("weddingHallNumber"));
			String category = request.getParameter("category");
			String location = request.getParameter("location");
			int price = Integer.parseInt(request.getParameter("price"));
			
			WeddingHallDetails weddingHallDetailsObj = new WeddingHallDetails(weddingHallNumber,null,category,location,price);
			WeddingHallTransactionDaoImpl weddingHallTransDaoObj = new WeddingHallTransactionDaoImpl();
			HttpSession session = request.getSession();
			session.setAttribute("editWeddingHallDetails", weddingHallDetailsObj);
			boolean flag = weddingHallTransDaoObj.updateWeddingHallAdmin(session);
			
			
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
		
		try {
		doGet(request, response);
	}
	catch(Exception e) {
		e.printStackTrace();
	}

	}

}
