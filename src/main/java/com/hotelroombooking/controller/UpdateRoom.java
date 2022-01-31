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
		
		
		
		
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		String checkIn = request.getParameter("checkIn");
		String checkOut = request.getParameter("checkOut");
		String category = request.getParameter("category");
		String location = request.getParameter("location");
		
		RoomTransaction roomTransObj = new RoomTransaction(roomNumber,checkIn,checkOut,category,location);
		RoomTransactionDaoImpl roomTransDaoObj = new RoomTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("updateRoomDetails", roomTransObj);
		Integer updateRoomPrice = roomTransDaoObj.findUpdateRoomPrice(session);
		session.setAttribute("updateRoomPrice", updateRoomPrice);
		
		boolean flag=roomTransDaoObj.updateRoom(session);
		
		
		if(session.getAttribute("noRoomsToUpdate")!=null) {
			response.sendRedirect("GuestDashboard.jsp");
			
		}
		else {
			response.sendRedirect("UpdateRoomPayment.jsp");
		}
		
		

	}

}
