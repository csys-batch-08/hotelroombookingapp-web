package com.hotelroombooking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.RoomTransactionDaoImpl;
import com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl;
import com.hotelroombooking.model.RoomDetails;
import com.hotelroombooking.model.WeddingHallDetails;

/**
 * Servlet implementation class deleteWeddingHall
 */
@WebServlet("/DeleteWeddingHall")
public class DeleteWeddingHall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteWeddingHall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		int weddingHallNumber = Integer.parseInt(request.getParameter("weddingHallNumber"));
		
		WeddingHallDetails weddingHallDetailsObj = new WeddingHallDetails(weddingHallNumber,null,null,null,0);
		WeddingHallTransactionDaoImpl weddingHallTransDaoObj = new WeddingHallTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("deleteWeddingHallDetails", weddingHallDetailsObj);
		boolean flag = weddingHallTransDaoObj.deleteWeddingHallAdmin(session);
//		PrintWriter pw = response.getWriter();
//		pw.write(flag+"");

		
		if(flag)
		{
		  
			response.sendRedirect("AdminDashboard.jsp");
		}
		
		
//		doGet(request, response);
	}

}
