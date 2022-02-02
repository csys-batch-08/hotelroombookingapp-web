package com.hotelroombooking.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.RoomTransaction;

public interface RoomTransactionDao {
	public boolean bookRoom(HttpSession session);

	public boolean updateRoom(HttpSession session);

	public boolean cancelRoom(HttpSession session);

	public List<RoomTransaction> showRoomBooking(Guest guestObj);

	public boolean addRoomAdmin(HttpSession session);

	public boolean deleteRoomAdmin(HttpSession session);

	public boolean updateRoomAdmin(HttpSession session);

	public int findBookRoomPrice(HttpSession session);

	public int findUpdateRoomPrice(HttpSession session);
}
