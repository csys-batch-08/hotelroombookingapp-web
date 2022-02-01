package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.dao.MeetingHallTransactionDao;
import com.hotelroombooking.message.Mail;
import com.hotelroombooking.message.Mailer;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.MeetingHallDetails;
import com.hotelroombooking.model.MeetingHallTransaction;
import com.hotelroombooking.util.ConnectionUtil;

public class MeetingHallTransactionDaoImpl implements MeetingHallTransactionDao
{
	public boolean bookMeetingHall(HttpSession session) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Guest guestObj=(Guest)session.getAttribute("currentUser");
		MeetingHallTransaction meetingHallTransObj=(MeetingHallTransaction)session.getAttribute("bookMeetingHallDetails");
		
		
		int vacantMeetingRoomNumber=0;
		int guestId=0;
		
		boolean flag=false;
		Connection conn=  ConnectionUtil.getDbConnection();
		PreparedStatement pstmt1=null,pstmt2=null,pstmt3=null;
		
		
		try 
		{	
			String fetchVacantMeetingRoom="select meeting_hall_number from meeting_hall_details where status='vacant' and category=? and location=?";	
			pstmt1 = conn.prepareStatement(fetchVacantMeetingRoom);
			pstmt1.setString(1, meetingHallTransObj.getCategory());
			pstmt1.setString(2, meetingHallTransObj.getLocation());		
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next())
			{
				vacantMeetingRoomNumber=rs.getInt(1);
			}
			if(vacantMeetingRoomNumber!=0) 
			{
				try {
				String bookMeetingRoomQuery="insert into meeting_hall_transaction(meeting_hall_number,check_in,check_out,category,location,guest_id) values(?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(bookMeetingRoomQuery);
				
				GuestDaoImpl guestDaoObj = new GuestDaoImpl();
				guestId=guestDaoObj.findGuestId(guestObj);
				
				meetingHallTransObj.setroomNumber(vacantMeetingRoomNumber);
				
				pstmt2.setInt(1, vacantMeetingRoomNumber);
				pstmt2.setDate(2, new java.sql.Date(sdf.parse(meetingHallTransObj.getCheckIn()).getTime()));
				pstmt2.setDate(3, new java.sql.Date(sdf.parse(meetingHallTransObj.getCheckOut()).getTime()));
				pstmt2.setString(4, meetingHallTransObj.getCategory());
				pstmt2.setString(5,meetingHallTransObj.getLocation());
				pstmt2.setInt(6, guestId);
				
		
				
				
				flag = pstmt2.executeUpdate()>0;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					if(pstmt2!=null) {
						pstmt2.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}
				if(flag)
				{
					System.out.println("Meeting Hall booked");
					
					try {
					String updateBookMeetingRoomQuery="update meeting_hall_details set status='occupied' where meeting_hall_number=?";
					pstmt3 = conn.prepareStatement(updateBookMeetingRoomQuery);
					pstmt3.setInt(1, vacantMeetingRoomNumber);
					pstmt3.executeUpdate();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					finally {
						if(pstmt3!=null) {
							pstmt3.close();
						}
						if(conn!=null) {
							conn.close();
						}
					}
					
					Mailer.send("hemnaathrsurya@gmail.com", "hangover@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.bookMeetingHallMail(meetingHallTransObj));
				}
				else
				{
					System.out.println("Error in booking");
				}
			}
			else 
			{
				session.setAttribute("noMeetingHallToBook","noMeetingHall");
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			if(pstmt1!=null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;

	}
	
	
	
	public boolean cancelMeetingHall(HttpSession session) 
	{
//		MeetingHallTransaction meetingHallTransObj=null;
		boolean flag=false;
		
		try {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter meeting hall number");
//		int meetingRoomNumber = Integer.parseInt(sc.nextLine());
			
			
		
		String updateCancelRoomQuery = "update meeting_hall_details set status='vacant' where meeting_hall_number=?";
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(updateCancelRoomQuery);
		Guest guestObj=(Guest)session.getAttribute("currentUser");
		MeetingHallTransaction meetingHallTransObj=(MeetingHallTransaction)session.getAttribute("cancelMeetingHallDetails");
				
		pstmt.setInt(1, meetingHallTransObj.getroomNumber());
		
//		meetingHallTransObj= new MeetingHallTransaction(meetingRoomNumber,null,null,null,null);

				
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("Booking Cancelled");
			Mailer.send("hemnaathrsurya@gmail.com", "hangover@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.cancelMeetingHallMail(meetingHallTransObj));

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
	
	
	
	public boolean updateMeetingHall(HttpSession session) 
	{
		
		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		int vacantMeetingHallNumber=0;
	
		int guestId=0;
		boolean flag=false;
		
		
		try {

			
			Guest guestObj=(Guest)session.getAttribute("currentUser");
			MeetingHallTransaction meetingHallTransObj=(MeetingHallTransaction)session.getAttribute("updateMeetingHallDetails");

		
		
		
		Connection conn = ConnectionUtil.getDbConnection();
		
		
		

		
		String fetchVacantRoom="select meeting_hall_number from meeting_hall_details where status='vacant' and category=? and location=?";
		String updateRoomQuery="update meeting_hall_transaction set check_in=?,check_out=?,category=?,location=? where meeting_hall_number=?";
		String updateRoomQuery2="update meeting_hall_transaction set meeting_hall_number=? where check_in=? and check_out=? and category=? and location=? and guest_id=?";
		String updateRoomQuery3 = "update meeting_hall_details set status='vacant' where meeting_hall_number=?";
		String updateRoomQuery4 = "update meeting_hall_details set status='occupied' where meeting_hall_number=?";
		
		PreparedStatement pstmt2 = conn.prepareStatement(fetchVacantRoom);
		PreparedStatement pstmt1 = conn.prepareStatement(updateRoomQuery);
		PreparedStatement pstmt3 = conn.prepareStatement(updateRoomQuery2);
		PreparedStatement pstmt4 = conn.prepareStatement(updateRoomQuery3);
		PreparedStatement pstmt5 = conn.prepareStatement(updateRoomQuery4);
		
		pstmt2.setString(1, meetingHallTransObj.getCategory());
		System.out.println(meetingHallTransObj.getCategory());
		pstmt2.setString(2, meetingHallTransObj.getLocation());
		System.out.println(meetingHallTransObj.getLocation());

		
		ResultSet rs = pstmt2.executeQuery();
		if(rs.next())
		{
			vacantMeetingHallNumber=rs.getInt(1);
			System.out.println(rs.getInt(1));
		}
//		System.out.println(vacantRoomNumber);
		
		if(vacantMeetingHallNumber!=0) {
		
		
		
		pstmt1.setDate(1, new java.sql.Date(sdf.parse(meetingHallTransObj.getCheckIn()).getTime()));
		pstmt1.setDate(2, new java.sql.Date(sdf.parse(meetingHallTransObj.getCheckOut()).getTime()));
		pstmt1.setString(3, meetingHallTransObj.getCategory());
		pstmt1.setString(4, meetingHallTransObj.getLocation());
		pstmt1.setInt(5, meetingHallTransObj.getroomNumber());
		
		pstmt1.executeUpdate();
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		guestId=guestDaoObj.findGuestId(guestObj);
		
		pstmt3.setInt(1, vacantMeetingHallNumber);
		pstmt3.setDate(2, new java.sql.Date(sdf.parse(meetingHallTransObj.getCheckIn()).getTime()));
		pstmt3.setDate(3, new java.sql.Date(sdf.parse(meetingHallTransObj.getCheckOut()).getTime()));
		pstmt3.setString(4, meetingHallTransObj.getCategory());
		pstmt3.setString(5, meetingHallTransObj.getLocation());
		pstmt3.setInt(6, guestId);
		
		pstmt3.executeUpdate();
		
//		MeetingHallTransaction meetingHallTransObj = new MeetingHallTransaction(vacantMeetingHallNumber,String.valueOf(checkIn),String.valueOf(checkOut),category,location);
		Mailer.send("hemnaathrsurya@gmail.com", "hangover@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.updateMeetingHallMail(meetingHallTransObj));


		
		pstmt4.setInt(1, meetingHallTransObj.getroomNumber());
		pstmt4.executeUpdate();
		
		meetingHallTransObj.setroomNumber(vacantMeetingHallNumber);
		
		pstmt5.setInt(1, vacantMeetingHallNumber);
		flag=pstmt5.executeUpdate()>0;
		
		if(flag)
		{
			System.out.println("Updated Room details");
		}
		}
		else {
			session.setAttribute("noMeetingHallToUpdate", "noMeetingHallUpdate");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
		
	}
	
		
	
	
	
	public List<MeetingHallTransaction> showMeetingHallBooking(Guest guestObj) 
	{
		int guestId=0;
		List<MeetingHallTransaction> meetingHallBooking = new ArrayList<MeetingHallTransaction>();
		
		try {
		String showMeetingHallBookingQuery = "select meeting_hall_number,check_in,check_out,category,location from meeting_hall_transaction where guest_id=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(showMeetingHallBookingQuery);
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		guestId=guestDaoObj.findGuestId(guestObj);
		
		pstmt.setInt(1, guestId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			MeetingHallTransaction meetingHallTrans = new MeetingHallTransaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				meetingHallBooking.add(meetingHallTrans);
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return meetingHallBooking;
	}
	
	
	
	
	
	public boolean addMeetingHallAdmin(HttpSession session) 
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		
		try {
//		System.out.println("enter meeting hall number");
//		int meetingHallNumber = Integer.parseInt(sc.nextLine());
//		System.out.println("enter meeting hall category");
//		String meetingHallCategory = sc.nextLine();
//		System.out.println("enter meeting hall location");
//		String meetingHallLocation = sc.nextLine();
//		System.out.println("enter meeting hall price");
//		int meetingHallPrice = Integer.parseInt(sc.nextLine());
			MeetingHallDetails meetingHallDetailsObj=(MeetingHallDetails)session.getAttribute("addMeetingHallDetails");
		System.out.println("hlo");
		
		String addMeetingHallQuery="insert into meeting_hall_details(meeting_hall_number,category,location,price) values(?,?,?,?)";
		System.out.println("hloo");
		
		Connection conn = ConnectionUtil.getDbConnection();
		System.out.println("hlooo");
		PreparedStatement pstmt = conn.prepareStatement(addMeetingHallQuery);
		System.out.println("hloooo");
		
		pstmt.setInt(1,meetingHallDetailsObj.getmeetingHallNumber());
		System.out.println(meetingHallDetailsObj.getmeetingHallNumber());
		pstmt.setString(2,meetingHallDetailsObj.getCategory());
		System.out.println(meetingHallDetailsObj.getCategory());
		pstmt.setString(3,meetingHallDetailsObj.getLocation());
		System.out.println(meetingHallDetailsObj.getLocation());
		pstmt.setInt(4,meetingHallDetailsObj.getPrice());
		System.out.println(meetingHallDetailsObj.getPrice());
		
		flag=pstmt.executeUpdate()>0;
		System.out.println("hlooooo");
		
		if(flag)
		{
			System.out.println("hloooooo");
			System.out.println("meeting hall added");
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
	
	
	
	
	
	
	public boolean deleteMeetingHallAdmin(HttpSession session) 
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		
		try {
//		System.out.println("enter wedding hall number");
//		int meetingHallNumber = Integer.parseInt(sc.nextLine());
			MeetingHallDetails meetingHallDetailsObj=(MeetingHallDetails)session.getAttribute("deleteMeetingHallDetails");

		
		String deleteMeetingHallQuery = "delete from meeting_hall_details where meeting_hall_number=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(deleteMeetingHallQuery);
		
		pstmt.setInt(1, meetingHallDetailsObj.getmeetingHallNumber());
		System.out.println(meetingHallDetailsObj.getmeetingHallNumber());
		
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("meeting hall deleted");
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
	
	
	
	
	
	public boolean updateMeetingHallAdmin(HttpSession session) 
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		
		try {
//		System.out.println("enter meeting room number");
//		int meetingHallNumber = Integer.parseInt(sc.nextLine());
//		System.out.println("enter meeting room category");
//		String meetingHallCategory = sc.nextLine();
//		System.out.println("enter meeting room location");
//		String meetingHallLocation = sc.nextLine();
//		System.out.println("enter meeting room price");
//		int meetingHallPrice = Integer.parseInt(sc.nextLine());
			MeetingHallDetails meetingHallDetailsObj=(MeetingHallDetails)session.getAttribute("editMeetingHallDetails");
			System.out.println("hlo");

		
		String updateRoomQuery="update meeting_hall_details set category=?,location=?,price=? where meeting_hall_number=?";
		System.out.println("hloo");
		
		Connection conn = ConnectionUtil.getDbConnection();
		System.out.println("hlooo");
		PreparedStatement pstmt = conn.prepareStatement(updateRoomQuery);
		System.out.println("hloooo");
		
		pstmt.setString(1, meetingHallDetailsObj.getCategory());
		System.out.println(meetingHallDetailsObj.getCategory());
		pstmt.setString(2, meetingHallDetailsObj.getLocation());
		System.out.println(meetingHallDetailsObj.getLocation());
		pstmt.setInt(3, meetingHallDetailsObj.getPrice());
		System.out.println(meetingHallDetailsObj.getPrice());
		pstmt.setInt(4, meetingHallDetailsObj.getmeetingHallNumber());
		System.out.println(meetingHallDetailsObj.getmeetingHallNumber());
		
		flag=pstmt.executeUpdate()>0;
		System.out.println(flag);
		System.out.println("hlooooo");
		if(flag)
		{
			System.out.println("meeting hall updated");
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



	
	public int findBookMeetingPrice(HttpSession session)
	{
		
		try {
			MeetingHallTransaction meetingHallTransObj=(MeetingHallTransaction)session.getAttribute("bookMeetingHallDetails");
//			System.out.println(weddingHallTransObj.getCategory());
		String findPriceQuery = "select price from meeting_hall_details where category='"+meetingHallTransObj.getCategory()+"'";
//		System.out.println(weddingHallTransObj.getCategory());
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
	
	
	
	public int findUpdateMeetingPrice(HttpSession session)
	{
		
		
		try {
			MeetingHallTransaction meetingHallTransObj=(MeetingHallTransaction)session.getAttribute("updateMeetingHallDetails");
//			System.out.println(weddingHallTransObj.getCategory());
		String findPriceQuery = "select price from meeting_hall_details where category='"+meetingHallTransObj.getCategory()+"'";
//		System.out.println(weddingHallTransObj.getCategory());
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