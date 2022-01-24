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
		
		RoomTransaction roomTransObj = new RoomTransaction(roomNumber,null,null,null,null);
		RoomTransactionDaoImpl roomTransDaoObj = new RoomTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("cancelRoomDetails", roomTransObj);
		boolean flag=roomTransDaoObj.cancelRoom(session);
//		PrintWriter pw = response.getWriter();
//		pw.write(flag+"");
		
		if(flag)
		{
			response.sendRedirect("GuestDashboard.jsp");
		}

		
		
//		doGet(request, response);
	}

}
