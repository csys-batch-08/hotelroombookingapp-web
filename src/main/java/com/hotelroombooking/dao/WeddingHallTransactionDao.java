package com.hotelroombooking.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.WeddingHallTransaction;

public interface WeddingHallTransactionDao 
{
	public boolean bookWeddingHall(HttpSession session);
	public boolean cancelWeddingHall(HttpSession session);
	public boolean updateWeddingHall(HttpSession session);
	public List<WeddingHallTransaction> showWeddingHallBooking(Guest guestObj);
	public boolean addWeddingHallAdmin(HttpSession session);
	public boolean deleteWeddingHallAdmin(HttpSession session);
	public boolean updateWeddingHallAdmin(HttpSession session);
	public int findUpdateWeddingPrice(HttpSession session);
	public int findBookWeddingPrice(HttpSession session);
}
