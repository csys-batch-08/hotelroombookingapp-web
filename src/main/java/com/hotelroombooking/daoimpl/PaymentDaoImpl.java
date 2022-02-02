package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt=null;


		try {
			Guest guestObj=(Guest)session.getAttribute("currentUser");
			Payment paymentObj=(Payment)session.getAttribute("payment");
			
			GuestDaoImpl guestDaoObj = new GuestDaoImpl();

			guestId=guestDaoObj.findGuestId(guestObj);
		
		String cardDetailsQuery = "insert into payment_details(card_number,expiry_date,cvv,guest_id) values (?,?,?,?)";
		 pstmt = conn.prepareStatement(cardDetailsQuery);
		pstmt.setLong(1, paymentObj.getCardNumber());
		pstmt.setString(2, paymentObj.getExpiryDate());	
		pstmt.setString(3, paymentObj.getCvv());
		pstmt.setInt(4, guestId);
		flag=pstmt.executeUpdate()>0;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
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
	
	
	
	

	
	
	
}
