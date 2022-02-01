package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.dao.GuestDao;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.util.ConnectionUtil;

public class GuestDaoImpl  implements GuestDao{
	
	
	
	public boolean registerGuest(String rgFirstname,String rgLastname,String rgMail,String rgPassword,String rgConfirmPassword,
			long rgMobileNumber)
	{
		String registerquery = "insert into guest_details (firstname,lastname,email,password,mobile) values (?,?,?,?,?)";
		Connection conn = ConnectionUtil.getDbConnection();
		 
		boolean flag=false;
		
		
		try
		{
			PreparedStatement p = conn.prepareStatement(registerquery);
			p.setString(1, rgFirstname);
			p.setString(2, rgLastname);
			p.setString(3, rgMail);
			p.setString(4, rgPassword);
			p.setLong(5, rgMobileNumber);
			
			flag=p.executeUpdate()>0;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	
	public Guest loginGuest(String gUserName,String gPassword)
	{
		String loginquery = "select * from guest_details where email=? and password=?";
		Connection conn = ConnectionUtil.getDbConnection();
		
		Guest guestObj=null;
		try
		{
			PreparedStatement p2 = conn.prepareStatement(loginquery);
			p2.setString(1, gUserName);
			p2.setString(2, gPassword);
			ResultSet rs1 = p2.executeQuery();
//			System.out.println(rs1.getString(2));
			while(rs1.next())
			{
				guestObj = new Guest(rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getLong(6));
			}
		}
		catch(Exception e)
		{ 	
			e.printStackTrace();
		}
		return guestObj;
	}
	
	
	public int findGuestId(Guest guestObj) 
	{
		int guestId=0;
		try
		{
			String findIdQuery="select id from guest_details where email=?";
			Connection conn = ConnectionUtil.getDbConnection();
			PreparedStatement pstmt = conn.prepareStatement(findIdQuery);
			pstmt.setString(1, guestObj.getEmail());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				guestId = rs.getInt(1);
//				System.out.println(guestId);
			}
			else
			{
				System.out.println("guest not found");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return guestId;
	}
	
	
	
	public List<Guest> showAllUser()
	{
		List<Guest> guestList = new ArrayList<Guest>();
		String allUserQuery = "select firstname,lastname,email,password,mobile from guest_details";
		
		Connection conn = ConnectionUtil.getDbConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(allUserQuery);
			while(rs.next())
			{
				Guest guest = new Guest(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getLong(6));
				guestList.add(guest);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guestList;
	}
	
	
	
	
	
	
	public boolean forgetPassword(HttpSession session) 
	{
//		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		try {
//		System.out.println("Enter email");
//		String mail=sc.nextLine();
//		System.out.println("Enter new password");
//		String passwd=sc.nextLine();
			
			Guest guestObj = (Guest)session.getAttribute("forgetPassword");
		
		String forgetPasswordQuery = "update guest_details set password=? where email=?";
		
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(forgetPasswordQuery);
		
		pstmt.setString(1, guestObj.getPassword());
//		System.out.println(guestObj.getEmail());
		pstmt.setString(2, guestObj.getEmail());
//		System.out.println(guestObj.getPassword());
		
		flag = pstmt.executeUpdate()>0;
		pstmt.executeUpdate("commit");
		if(flag)
		{
			System.out.println("Password changed successfully");
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
	
	
	

}
