package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.dao.WeddingHallTransactionDao;
import com.hotelroombooking.message.Mail;
import com.hotelroombooking.message.Mailer;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.RoomDetails;
import com.hotelroombooking.model.RoomTransaction;
import com.hotelroombooking.model.WeddingHallDetails;
import com.hotelroombooking.model.WeddingHallTransaction;
import com.hotelroombooking.util.ConnectionUtil;

public class WeddingHallTransactionDaoImpl implements WeddingHallTransactionDao
{
	public boolean bookWeddingHall(HttpSession session)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int vacantWeddingRoomNumber=0;
		int guestId=0;
		int i=0;
		boolean flag=false;
//		WeddingHallTransaction weddingHallTransObj=null;
		
		try {
//		do {
//		System.out.println("enter check-in date");
//		checkIn = sdf.parse(sc.nextLine());
//		System.out.println("enter check-out date");
//		checkOut = sdf.parse(sc.nextLine());
//		if(checkIn.after(checkOut))
//		{
//			System.out.println("Invalid Date Format");
//			dateFlag=false;
//		}
//		else
//		{
//			dateFlag=true;
//		}
//		}while(dateFlag!=true);
//		System.out.println("enter category");
//		System.out.println("1.Premium\n2.luxury\n3.standard\n4.budget");
//		int categoryChoice = Integer.parseInt(sc.nextLine());
//		String category = (categoryChoice==1)?"premium":(categoryChoice==2)?"luxury":(categoryChoice==3)?"standard":"budget";
//		System.out.println("enter location");
//		String location = sc.nextLine();
		
		String fetchVacantWeddingRoom="select wedding_hall_number from wedding_hall_details where status='vacant' and category=? and location=?";
		
		String bookWeddingRoomQuery="insert into wedding_hall_transaction(wedding_hall_number,check_in,check_out,category,location,guest_id) values(?,?,?,?,?,?)";
		String updateWeddingRoomQuery="update wedding_hall_details set status='occupied' where wedding_hall_number=?";
//		System.out.println(bookRoomQuery);
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt1 = conn.prepareStatement(fetchVacantWeddingRoom);
		Guest guestObj=(Guest)session.getAttribute("currentUser");
		WeddingHallTransaction weddingHallTransObj=(WeddingHallTransaction)session.getAttribute("bookWeddingHallDetails");
		
		pstmt1.setString(1, weddingHallTransObj.getCategory());
		pstmt1.setString(2, weddingHallTransObj.getLocation());
		
		ResultSet rs = pstmt1.executeQuery();
		if(rs.next())
		{
			vacantWeddingRoomNumber=rs.getInt(1);
		}
		
		
		if(vacantWeddingRoomNumber!=0)
		{
		
		PreparedStatement pstmt2 = conn.prepareStatement(bookWeddingRoomQuery);
		PreparedStatement pstmt3 = conn.prepareStatement(updateWeddingRoomQuery);
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		
		guestId=guestDaoObj.findGuestId(guestObj);
//		System.out.println(guestId);
		weddingHallTransObj.setroomNumber(vacantWeddingRoomNumber);
		
		pstmt2.setInt(1, vacantWeddingRoomNumber);
		pstmt2.setDate(2, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
		pstmt2.setDate(3, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckOut()).getTime()));
		pstmt2.setString(4, weddingHallTransObj.getCategory());
		pstmt2.setString(5,weddingHallTransObj.getLocation());
		pstmt2.setInt(6, guestId);
		
		pstmt3.setInt(1, vacantWeddingRoomNumber);
		
//		System.out.println(bookRoomQuery);
//		 weddingHallTransObj = new WeddingHallTransaction(vacantWeddingRoomNumber,String.valueOf(checkIn),
//				String.valueOf(checkOut),category,location);

		
		flag = pstmt2.executeUpdate()>0;
	

		
		if(flag)
		{
//			System.out.println("Weddings Hall booked");
			pstmt3.executeUpdate();
			Mailer.send("hemnaathrsurya@gmail.com", "hemnaath@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.bookWeddingHallMail(weddingHallTransObj));

		}
		else
		{
			System.out.println("Error in booking");
		}
		}
		else {
			session.setAttribute("NoWeddingHallToBook", "noWeddingHall");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;

	}
	
	
	
	public boolean cancelWeddingHall(HttpSession session)
	{
//		WeddingHallTransaction weddingHallTransObj=null;
		boolean flag=false;
		
		try {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter wedding hall number");
//		int weddingRoomNumber = Integer.parseInt(sc.nextLine());
		
		String updateCancelWeddingRoomQuery = "update wedding_hall_details set status='vacant' where wedding_hall_number=?";
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(updateCancelWeddingRoomQuery);
		Guest guestObj=(Guest)session.getAttribute("currentUser");
		WeddingHallTransaction weddingHallTransObj=(WeddingHallTransaction)session.getAttribute("cancelWeddingHallDetails");
		
				
		pstmt.setInt(1, weddingHallTransObj.getroomNumber());
		
//		 weddingHallTransObj = new WeddingHallTransaction(weddingRoomNumber,null,null,null,null);

				
		flag=pstmt.executeUpdate()>0;
		

		
		if(flag)
		{
			System.out.println("Booking Cancelled");
			Mailer.send("hemnaathrsurya@gmail.com", "hemnaath@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.cancelWeddingHallMail(weddingHallTransObj));

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
	
	
	
	
	public boolean updateWeddingHall(HttpSession session)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Scanner sc = new Scanner(System.in);
		int vacantWeddingRoomNumber=0;
		
		int guestId=0;
		boolean flag=false;
		
		
		try {

			
			Guest guestObj=(Guest)session.getAttribute("currentUser");
			WeddingHallTransaction weddingHallTransObj=(WeddingHallTransaction)session.getAttribute("updateWeddingHallDetails");
		
		
		
		Connection conn = ConnectionUtil.getDbConnection();
		
	

		
		String fetchVacantRoom="select wedding_hall_number from wedding_hall_details where status='vacant' and category=? and location=?";
		String updateRoomQuery="update wedding_hall_transaction set check_in=?,check_out=?,category=?,location=? where wedding_hall_number=?";
		String updateRoomQuery2="update wedding_hall_transaction set wedding_hall_number=? where check_in=? and check_out=? and category=? and location=? and guest_id=?";
		String updateRoomQuery3 = "update wedding_hall_details set status='vacant' where wedding_hall_number=?";
		String updateRoomQuery4 = "update wedding_hall_details set status='occupied' where wedding_hall_number=?";
		
		PreparedStatement pstmt2 = conn.prepareStatement(fetchVacantRoom);
		PreparedStatement pstmt1 = conn.prepareStatement(updateRoomQuery);
		PreparedStatement pstmt3 = conn.prepareStatement(updateRoomQuery2);
		PreparedStatement pstmt4 = conn.prepareStatement(updateRoomQuery3);
		PreparedStatement pstmt5 = conn.prepareStatement(updateRoomQuery4);
		
		pstmt2.setString(1, weddingHallTransObj.getCategory());
//		System.out.println(category);
		pstmt2.setString(2, weddingHallTransObj.getLocation());
//		System.out.println(location);

		
		ResultSet rs = pstmt2.executeQuery();
		if(rs.next())
		{
			vacantWeddingRoomNumber=rs.getInt(1);
			System.out.println(rs.getInt(1));
		}
//		System.out.println(vacantRoomNumber);
		
		if(vacantWeddingRoomNumber!=0) {
		
		
		
		pstmt1.setDate(1, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
		pstmt1.setDate(2, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckOut()).getTime()));
		pstmt1.setString(3, weddingHallTransObj.getCategory());
		pstmt1.setString(4, weddingHallTransObj.getLocation());
		pstmt1.setInt(5, weddingHallTransObj.getroomNumber());
		
		pstmt1.executeUpdate();
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		guestId=guestDaoObj.findGuestId(guestObj);
		
		
		pstmt3.setInt(1, vacantWeddingRoomNumber);
		pstmt3.setDate(2, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
		pstmt3.setDate(3, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
		pstmt3.setString(4, weddingHallTransObj.getCategory());
		pstmt3.setString(5, weddingHallTransObj.getLocation());
		pstmt3.setInt(6, guestId);
		
		pstmt3.executeUpdate();
		
//		 weddingHallTransObj = new WeddingHallTransaction(vacantWeddingRoomNumber,String.valueOf(checkIn),String.valueOf(checkOut)
//				,category,location);
		Mailer.send("hemnaathrsurya@gmail.com", "hemnaath@18!!", guestObj.getEmail(), "Hotel Room Booking Application", Mail.updateWeddingHallMail(weddingHallTransObj));


		
		pstmt4.setInt(1, weddingHallTransObj.getroomNumber());
		pstmt4.executeUpdate();
		
		weddingHallTransObj.setroomNumber(vacantWeddingRoomNumber);
		
		pstmt5.setInt(1, vacantWeddingRoomNumber);
		flag=pstmt5.executeUpdate()>0;
		
		if(flag)
		{
			System.out.println("Updated Room details");
		}
		}
		else {
			session.setAttribute("noWeddingHallsToUpdate", "noWeddingHallUpdate");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
		
	}
	
	
	
	
	
	
	
	public List<WeddingHallTransaction> showWeddingHallBooking(Guest guestObj)
	{
		int guestId=0;
		WeddingHallTransaction weddingHallTrans=null;
		List<WeddingHallTransaction> weddingHallBooking = new ArrayList<WeddingHallTransaction>();
		
		try {
		String showWeddingHallBookingQuery = "select check_in,check_out,category,location from wedding_hall_transaction where guest_id=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(showWeddingHallBookingQuery);
		
		GuestDaoImpl guestDaoObj = new GuestDaoImpl();
		guestId=guestDaoObj.findGuestId(guestObj);
		
		pstmt.setInt(1, guestId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			weddingHallTrans = new WeddingHallTransaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			weddingHallBooking.add(weddingHallTrans);
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return weddingHallBooking;
	}
	
	
	
	
	
	public boolean addWeddingHallAdmin(HttpSession session)
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		
		
		try {
//		System.out.println("enter wedding hall number");
//		int weedingHallNumber = Integer.parseInt(sc.nextLine());
//		System.out.println("enter wedding hall category");
//		String weddingHallCategory = sc.nextLine();
//		System.out.println("enter wedding hall location");
//		String weddingHallLocation = sc.nextLine();
//		System.out.println("enter wedding hall price");
//		int weddingHallPrice = Integer.parseInt(sc.nextLine());
			WeddingHallDetails weddingHallDetailsObj=(WeddingHallDetails)session.getAttribute("addWeddingHallDetails");
		
		
		String addWeddingHallQuery="insert into wedding_hall_details(wedding_hall_number,category,location,price) values(?,?,?,?)";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(addWeddingHallQuery);
		
		pstmt.setInt(1,weddingHallDetailsObj.getweddingHallNumber());
		pstmt.setString(2,weddingHallDetailsObj.getCategory());
		pstmt.setString(3,weddingHallDetailsObj.getLocation());
		pstmt.setInt(4,weddingHallDetailsObj.getPrice());
		
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("wedding hall added");
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
	
	
	
	
	
	
	public boolean deleteWeddingHallAdmin(HttpSession session) 
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		
		try {
//		System.out.println("enter wedding hall number");
//		int weddingHallNumber = Integer.parseInt(sc.nextLine());
			WeddingHallDetails weddingHallDetailsObj=(WeddingHallDetails)session.getAttribute("deleteWeddingHallDetails");

		
		String deleteWeddingHallQuery = "delete from wedding_hall_details where wedding_hall_number=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(deleteWeddingHallQuery);
		
		pstmt.setInt(1, weddingHallDetailsObj.getweddingHallNumber());
		
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("wedding hall deleted");
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
	
	
	
	
	
	public boolean updateWeddingHallAdmin(HttpSession session)
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		
		try {
//		System.out.println("enter wedding room number");
//		int weddingHallNumber = Integer.parseInt(sc.nextLine());
//		System.out.println("enter wedding room category");
//		String weddingHallCategory = sc.nextLine();
//		System.out.println("enter wedding room location");
//		String weddingHallLocation = sc.nextLine();
//		System.out.println("enter wedding room price");
//		int weddingHallPrice = Integer.parseInt(sc.nextLine());
			WeddingHallDetails weddingHallDetailsObj=(WeddingHallDetails)session.getAttribute("editWeddingHallDetails");
		
		String updateRoomQuery="update wedding_hall_details set category=?,location=?,price=? where wedding_hall_number=?";
		
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(updateRoomQuery);
		
		pstmt.setString(1, weddingHallDetailsObj.getCategory());
		pstmt.setString(2, weddingHallDetailsObj.getLocation());
		pstmt.setInt(3, weddingHallDetailsObj.getPrice());
		pstmt.setInt(4, weddingHallDetailsObj.getweddingHallNumber());
		
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("wedding hall updated");
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
	
	
	
	
	public int findBookWeddingPrice(HttpSession session)
	{
		int price=0;
		try {
			WeddingHallTransaction weddingHallTransObj=(WeddingHallTransaction)session.getAttribute("bookWeddingHallDetails");
			System.out.println(weddingHallTransObj.getCategory());
		String findPriceQuery = "select price from wedding_hall_details where category='"+weddingHallTransObj.getCategory()+"'";
		System.out.println(weddingHallTransObj.getCategory());
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
	
	
	
	public int findUpdateWeddingPrice(HttpSession session)
	{
		int price=0;
		try {
			WeddingHallTransaction weddingHallTransObj=(WeddingHallTransaction)session.getAttribute("updateWeddingHallDetails");
			System.out.println(weddingHallTransObj.getCategory());
		String findPriceQuery = "select price from wedding_hall_details where category='"+weddingHallTransObj.getCategory()+"'";
		System.out.println(weddingHallTransObj.getCategory());
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