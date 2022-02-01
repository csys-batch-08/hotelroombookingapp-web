package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;


import javax.servlet.http.HttpSession;

import com.hotelroombooking.util.ConnectionUtil;
import com.hotelroombooking.dao.PaymentDao;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.Payment;

public class PaymentDaoImpl implements PaymentDao
{
	public boolean payment(HttpSession session)  
	{
		boolean flag=false;
		int guestId=0;
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Card Details");
//		long cardNumber = sc.nextLong();
		try {
			Guest guestObj=(Guest)session.getAttribute("currentUser");
			Payment paymentObj=(Payment)session.getAttribute("payment");
			
			GuestDaoImpl guestDaoObj = new GuestDaoImpl();

			guestId=guestDaoObj.findGuestId(guestObj);
		
		String cardDetailsQuery = "insert into payment_details(card_number,expiry_date,cvv,guest_id) values (?,?,?,?)";
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = conn.prepareStatement(cardDetailsQuery);
		pstmt.setLong(1, paymentObj.getCardNumber());
		pstmt.setString(2, paymentObj.getExpiryDate());	
		pstmt.setString(3, paymentObj.getCvv());
		pstmt.setInt(4, guestId);
		flag=pstmt.executeUpdate()>0;
		if(flag)
		{
			System.out.println("Payment Successfull");
		}
		else
		{
			System.err.println("Payment Failed");
		}
		}
		catch(Exception e)
		{
//			System.out.println(e);
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	
	
	
	
//	public Payment guestInvoice(int vacantMeetingRoomNumber) throws SQLException
//	{
//		Payment paymentObj = null;
//		String invoiceQuery="select meeting_hall_details.meeting_hall_number,meeting_hall_details.category,meeting_hall_details.location,"
//				+ "meeting_hall_details.price,meeting_hall_transaction.check_in,meeting_hall_transaction.check_outfrom meeting_hall_details "
//				+ "inner join meeting_hall_transaction ON meeting_hall_details.meeting_hall_number=meeting_hall_transaction.meeting_hall_number "
//				+ "where meeting_hall_details.status='occupied' and meeting_hall_details.meeting_hall_number='"+vacantMeetingRoomNumber+"'";
//		
//		Connection conn = ConnectionUtil.getDbConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery(invoiceQuery);
//		while(rs.next())
//		{
//			paymentObj = new Payment(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getDate(6));
//		}
//		return paymentObj;
//	}
	
	
	
	
}
