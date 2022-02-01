package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import com.hotelroombooking.dao.RoomTransactionDao;
import com.hotelroombooking.util.ConnectionUtil;
import com.hotelroombooking.message.Mail;
import com.hotelroombooking.message.Mailer;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.RoomDetails;
import com.hotelroombooking.model.RoomTransaction;

public class RoomTransactionDaoImpl implements RoomTransactionDao{
	
	public boolean bookRoom(HttpSession session)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int vacantRoomNumber=0;
		int guestId=0;
		
		
		boolean flag=false;
		
		try {
		
		
		String fetchVacantRoom="select room_number from room_details where status='vacant' and category=? and location=?";
		
		String bookRoomQuery="insert into room_transaction(room_number,check_in,check_out,category,location,guest_id) values(?,?,?,?,?,?)";
		String updateBookRoomQuery="update room_details set status='occupied' where room_number=?";
//		System.out.println(bookRoomQuery);
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt1 = conn.prepareStatement(fetchVacantRoom);
		
	Guest guestObj=(Guest)session.getAttribute("currentUser");
	RoomTransaction roomTransObj=(RoomTransaction)session.getAttribute("bookRoomDetails");
	
		pstmt1.setString(1, roomTransObj.getCategory());
		pstmt1.setString(2, roomTransObj.getLocation());
		
		ResultSet rs = pstmt1.executeQuery();
		
//		RoomTransaction roomTransObj = null;
		if(rs.next())
		{
			flag=true;
			vacantRoomNumber=rs.getInt(1);
//			System.out.println(vacantRoomNumber);
			
		}
//		System.out.println(vacantRoomNumber);
		
		if(vacantRoomNumber!=0)
		{
			PreparedStatement pstmt2 = conn.prepareStatement(bookRoomQuery);
			PreparedStatement pstmt3 = conn.prepareStatement(updateBookRoomQuery);
		
			GuestDaoImpl guestDaoObj = new GuestDaoImpl();

			guestId=guestDaoObj.findGuestId(guestObj);
//			System.out.println(guestId);
	
			roomTransObj.setroomNumber(vacantRoomNumber);
			pstmt2.setInt(1, vacantRoomNumber);
			pstmt2.setDate(2, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
//			System.out.println(sdf.parse(roomTransObj.getCheckIn()));
			pstmt2.setDate(3, new java.sql.Date(sdf.parse(roomTransObj.getCheckOut()).getTime()));
			pstmt2.setString(4, roomTransObj.getCategory());
			pstmt2.setString(5,roomTransObj.getLocation());
			pstmt2.setInt(6, guestId);
		
			pstmt3.setInt(1, vacantRoomNumber);
		
//			System.out.println(bookRoomQuery);
//			roomTransObj= new RoomTransaction(vacantRoomNumber,String.valueOf(checkIn),String.valueOf(checkOut),roomTransObj.getCategory(),roomTransObj.getLocation());
		
			flag = pstmt2.executeUpdate()>0;
//			System.out.println("hloo");
			if(flag)
			{
//				System.out.println("Room booked");
				pstmt3.executeUpdate();
//				System.out.println("hlooo");
				
				Mailer.send("hemnaathrsurya@gmail.com", "hangover@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.bookRoomMail(roomTransObj));
			}
		}
		else
		{
			System.out.println("Error in room booking");
			session.setAttribute("NoRoomsToBook", "noRooms");
		}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
		 
	}
	
	
	
	
	
	public boolean cancelRoom(HttpSession session)
	{
		boolean flag=false;
	
//		System.out.println("Enter room number");
//		int roomNumber = Integer.parseInt(sc.nextLine());
//		RoomTransaction roomTransObj = null;
		
		try {
			Guest guestObj=(Guest)session.getAttribute("currentUser");
			RoomTransaction roomTransObj=(RoomTransaction)session.getAttribute("cancelRoomDetails");
			
		String updateCancelRoomQuery = "update room_details set status='vacant' where room_number=?";
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(updateCancelRoomQuery);
				
		pstmt.setInt(1, roomTransObj.getroomNumber());
		
//		roomTransObj = new RoomTransaction(roomNumber,null,null,null,null);
				
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
//			System.out.println("Booking Cancelled");
			Mailer.send("hemnaathrsurya@gmail.com", "hangover@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.cancelRoomMail(roomTransObj));

		}
		
		else
		{
			System.out.println("Invalid Room");
		}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
		
	}
	
	
	
	
	
	
	public boolean updateRoom(HttpSession session)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int vacantRoomNumber=0;
		int guestId=0;
		boolean flag=false;
		
//		RoomTransaction roomTransObj=null;
		
		try {
			
			Guest guestObj=(Guest)session.getAttribute("currentUser");
			RoomTransaction roomTransObj=(RoomTransaction)session.getAttribute("updateRoomDetails");
		
		
		
		Connection conn = ConnectionUtil.getDbConnection();
		
		
		

		
		String fetchVacantRoom="select room_number from room_details where status='vacant' and category=? and location=?";
		String updateRoomQuery="update room_transaction set check_in=?,check_out=?,category=?,location=? where room_number=?";
		String updateRoomQuery2="update room_transaction set room_number=? where check_in=? and check_out=? and category=? and location=? and guest_id=?";
		String updateRoomQuery3 = "update room_details set status='vacant' where room_number=?";
		String updateRoomQuery4 = "update room_details set status='occupied' where room_number=?";
		
		PreparedStatement pstmt2 = conn.prepareStatement(fetchVacantRoom);
		PreparedStatement pstmt1 = conn.prepareStatement(updateRoomQuery);
		PreparedStatement pstmt3 = conn.prepareStatement(updateRoomQuery2);
		PreparedStatement pstmt4 = conn.prepareStatement(updateRoomQuery3);
		PreparedStatement pstmt5 = conn.prepareStatement(updateRoomQuery4);
		
		pstmt2.setString(1, roomTransObj.getCategory());
//		System.out.println(category);
		pstmt2.setString(2, roomTransObj.getLocation());
//		System.out.println(location);

		
		ResultSet rs = pstmt2.executeQuery();
		if(rs.next())
		{
			vacantRoomNumber=rs.getInt(1);
			System.out.println(rs.getInt(1));
		}
//		System.out.println(vacantRoomNumber);
		
		if(vacantRoomNumber!=0) {
		
		pstmt1.setDate(1, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
		pstmt1.setDate(2, new java.sql.Date(sdf.parse(roomTransObj.getCheckOut()).getTime()));
		pstmt1.setString(3, roomTransObj.getCategory());
		pstmt1.setString(4, roomTransObj.getLocation());
		pstmt1.setInt(5, roomTransObj.getroomNumber());
		
		pstmt1.executeUpdate();
		
		
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		guestId=guestDaoObj.findGuestId(guestObj);
		

		
		pstmt3.setInt(1, vacantRoomNumber);
		pstmt3.setDate(2, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
		pstmt3.setDate(3, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
		pstmt3.setString(4, roomTransObj.getCategory());
		pstmt3.setString(5, roomTransObj.getLocation());
		pstmt3.setInt(6, guestId);
		System.out.println(guestId);
		
		pstmt3.executeUpdate();
//		roomTransObj = new RoomTransaction(vacantRoomNumber,String.valueOf(checkIn),String.valueOf(checkOut),category,location);


		
		pstmt4.setInt(1, roomTransObj.getroomNumber());
		System.out.println(roomTransObj.getroomNumber());
		pstmt4.executeUpdate();
		
		roomTransObj.setroomNumber(vacantRoomNumber);
		
		pstmt5.setInt(1, vacantRoomNumber);
		System.out.println(vacantRoomNumber);
		System.out.println(roomTransObj.getroomNumber());
		flag=pstmt5.executeUpdate()>0;
		
		if(flag)
		{
			System.out.println("Updated Room details");
			Mailer.send("hemnaathrsurya@gmail.com", "hangover@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.updateRoomMail(roomTransObj));

		}
		}
		else {
			session.setAttribute("noRoomsToUpdate", "noUpdate");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
		
	}
	
	
	
	
	
	public List<RoomTransaction> showRoomBooking(Guest guestObj)	
	{
		int guestId=0;		
		List<RoomTransaction> roomBooking = new ArrayList<RoomTransaction>();
		
		try {
		String showRoomBookingQuery = "select room_number,check_in,check_out,category,location from room_transaction where guest_id=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(showRoomBookingQuery);
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		guestId=guestDaoObj.findGuestId(guestObj);
		
		pstmt.setInt(1, guestId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			RoomTransaction roomTrans = new RoomTransaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			roomBooking.add(roomTrans);
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return roomBooking;
	}
	
	
	
	
	public boolean addRoomAdmin(HttpSession session)
	{
		boolean flag=false;
		try {
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("enter room number");
//		int roomNumber = Integer.parseInt(sc.nextLine());
//		System.out.println("enter room category");
//		String roomCategory = sc.nextLine();
//		System.out.println("enter room location");
//		String roomLocation = sc.nextLine();
//		System.out.println("enter room price");
//		int roomPrice = Integer.parseInt(sc.nextLine());
			
			RoomDetails roomDetailsObj=(RoomDetails)session.getAttribute("addRoomDetails");
		
		
		String addRoomQuery="insert into room_details(room_number,category,location,price) values(?,?,?,?)";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(addRoomQuery);
		
		pstmt.setInt(1,roomDetailsObj.getRoomNumber());
		pstmt.setString(2,roomDetailsObj.getCategory());
		pstmt.setString(3,roomDetailsObj.getLocation());
		pstmt.setInt(4,roomDetailsObj.getPrice());
		
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("Room added");
		}
		else
		{
			System.out.println("Error");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
	}
	
	
	
	
	public boolean deleteRoomAdmin(HttpSession session)
	{
		boolean flag=false;
		try {
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("enter room number");
//		int roomNumber = Integer.parseInt(sc.nextLine());
			RoomDetails roomDetailsObj=(RoomDetails)session.getAttribute("deleteRoomDetails");

		
		String deleteRoomQuery = "delete from room_details where room_number=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(deleteRoomQuery);
		
		pstmt.setInt(1, roomDetailsObj.getRoomNumber());
		
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("room deleted");
		}
		else
		{
			System.err.println("error");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return flag;
	}
	
	
	
	
	
	
	
	public boolean updateRoomAdmin(HttpSession session)
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		
		try {
//		System.out.println("enter room number");
//		int roomNumber = Integer.parseInt(sc.nextLine());
//		System.out.println("enter room category");
//		String roomCategory = sc.nextLine();
//		System.out.println("enter room location");
//		String roomLocation = sc.nextLine();
//		System.out.println("enter room price");
//		int roomPrice = Integer.parseInt(sc.nextLine());
			RoomDetails roomDetailsObj=(RoomDetails)session.getAttribute("editRoomDetails");

		
		String updateRoomQuery="update room_details set category=?,location=?,price=? where room_number=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(updateRoomQuery);
		
		pstmt.setString(1, roomDetailsObj.getCategory());
		pstmt.setString(2, roomDetailsObj.getLocation());
		pstmt.setInt(3, roomDetailsObj.getPrice());
		pstmt.setInt(4, roomDetailsObj.getRoomNumber());
		
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("room updated");
		}
		else
		{
			System.err.println("error");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
		
	}



	
	
	public int findBookRoomPrice(HttpSession session)
	{
		
		try {
		RoomTransaction roomTransObj=(RoomTransaction)session.getAttribute("bookRoomDetails");
		String findPriceQuery = "select price from room_details where category='"+roomTransObj.getCategory()+"'";
		System.out.println(roomTransObj.getCategory());
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt=conn.prepareStatement(findPriceQuery);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
		return rs.getInt(1);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	
	
	
	public int findUpdateRoomPrice(HttpSession session)
	{
		
		try {
		RoomTransaction roomTransObj=(RoomTransaction)session.getAttribute("updateRoomDetails");
		String findPriceQuery = "select price from room_details where category='"+roomTransObj.getCategory()+"'";
		System.out.println(roomTransObj.getCategory());
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt=conn.prepareStatement(findPriceQuery);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
		return rs.getInt(1);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}


	





	
	
	

}
