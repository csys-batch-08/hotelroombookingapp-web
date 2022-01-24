package com.hotelroombooking.dao;

import javax.servlet.http.HttpSession;

public interface PaymentDao 
{
	public boolean payment(HttpSession session);
}
