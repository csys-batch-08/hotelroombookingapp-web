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
 * Servlet implementation class addWeddingHall
 */
@WebServlet("/AddWeddingHall")
public class AddWeddingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddWeddingHall() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		int weddingHallNumber = Integer.parseInt(request.getParameter("weddingHallNumber"));
		System.out.println(weddingHallNumber);
		String category = request.getParameter("category");
		System.out.println(category);
		String location = request.getParameter("location");
		System.out.println(location);
		int price = Integer.parseInt(request.getParameter("price"));
		System.out.println(price);
		
		WeddingHallDetails weddingHallDetailsObj = new WeddingHallDetails(weddingHallNumber,null,category,location,price);
		WeddingHallTransactionDaoImpl weddingHallTransDao = new WeddingHallTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("addWeddingHallDetails", weddingHallDetailsObj);
		boolean flag = weddingHallTransDao.addWeddingHallAdmin(session);
//		PrintWriter pw = response.getWriter();
//		pw.write(flag+"");
		
		
		if(flag)
		{
		  
			response.sendRedirect("adminDashboard.jsp");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
//		doGet(request, response);
	}

}
