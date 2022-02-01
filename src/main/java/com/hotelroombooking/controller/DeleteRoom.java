package com.hotelroombooking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.RoomTransactionDaoImpl;
import com.hotelroombooking.model.RoomDetails;

/**
 * Servlet implementation class deleteRoom
 */
@WebServlet("/DeleteRoom")
public class DeleteRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRoom() {
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
		
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		
		RoomDetails roomDetailsObj = new RoomDetails(roomNumber,null,null,null,0);
		RoomTransactionDaoImpl roomTransDaoObj = new RoomTransactionDaoImpl();
		HttpSession session = request.getSession();
		session.setAttribute("deleteRoomDetails", roomDetailsObj);
		boolean flag = roomTransDaoObj.deleteRoomAdmin(session);


		
		if(flag)
		{
		  
			response.sendRedirect("adminDashboard.jsp");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
