package com.hotelroombooking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl;
import com.hotelroombooking.daoimpl.RoomTransactionDaoImpl;
import com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl;

/**
 * Servlet implementation class PriceDetails
 */
@WebServlet("/PriceDetails")
public class PriceDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriceDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
	
		RoomTransactionDaoImpl roomTransDaoObj = new RoomTransactionDaoImpl();
		Integer bookRoomPrice = roomTransDaoObj.findBookRoomPrice(session);
		session.setAttribute("bookRoomPrice", bookRoomPrice);
		
		Integer updateRoomPrice = roomTransDaoObj.findUpdateRoomPrice(session);
		
		
		WeddingHallTransactionDaoImpl weddingHallTransObj = new WeddingHallTransactionDaoImpl();
		Integer bookWeddingPrice = weddingHallTransObj.findBookWeddingPrice(session);
		Integer updateWeddingPrice = weddingHallTransObj.findUpdateWeddingPrice(session);
		
		
		
		MeetingHallTransactionDaoImpl meetingHallTransObj = new MeetingHallTransactionDaoImpl();
		Integer bookMeetingPrice = meetingHallTransObj.findBookMeetingPrice(session);
		Integer updateMeetingPrice = meetingHallTransObj.findUpdateMeetingPrice(session);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
