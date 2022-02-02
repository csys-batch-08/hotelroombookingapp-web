package com.hotelroombooking.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.MeetingHallTransaction;

public interface MeetingHallTransactionDao {
	public boolean bookMeetingHall(HttpSession session);

	public boolean cancelMeetingHall(HttpSession session);

	public boolean updateMeetingHall(HttpSession session);

	public List<MeetingHallTransaction> showMeetingHallBooking(Guest guestObj);

	public boolean addMeetingHallAdmin(HttpSession session);

	public boolean deleteMeetingHallAdmin(HttpSession session);

	public boolean updateMeetingHallAdmin(HttpSession session);

	public int findBookMeetingPrice(HttpSession session);

	public int findUpdateMeetingPrice(HttpSession session);
}
