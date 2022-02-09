package com.hotelroombooking.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.model.Guest;

public interface GuestDao {
	public boolean registerGuest(String rgFirstname, String rgLastname, String rgMail, String rgPassword,
			String rgConfirmPassword, long rgMobileNumber);

	public Guest loginGuest(String gUserName, String gPassword);

	public int findGuestId(Guest guestObj);

	public List<Guest> showAllUser();

	public boolean forgetPassword(HttpSession session);
}
