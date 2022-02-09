package com.hotelroombooking.model;

import java.io.Serializable;
import java.util.Objects;

public class MeetingHallTransaction implements Serializable {

	private int roomNumber;
	private String checkIn;
	private String checkOut;
	private String category;
	private String location;
	private Guest guestId;

	public MeetingHallTransaction(int roomNumber, String checkIn, String checkOut, String category, String location,
			Guest guestId) {
		super();
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.category = category;
		this.location = location;
		this.guestId = guestId;
	}

	public MeetingHallTransaction(int roomNumber, String checkIn, String checkOut, String category, String location) {
		super();
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.category = category;
		this.location = location;
	}

	public MeetingHallTransaction() {
		super();
	}

	@Override
	public String toString() {
		return "MeetingHallDetails-> roomNumber=" + roomNumber + ", checkIn=" + checkIn + ", checkOut=" + checkOut
				+ ", category=" + category + "location=" + location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, checkIn, checkOut, guestId, location, roomNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingHallTransaction other = (MeetingHallTransaction) obj;
		return Objects.equals(category, other.category) && Objects.equals(checkIn, other.checkIn)
				&& Objects.equals(checkOut, other.checkOut) && Objects.equals(guestId, other.guestId)
				&& Objects.equals(location, other.location) && roomNumber == other.roomNumber;
	}

	public int getroomNumber() {
		return roomNumber;
	}

	public void setroomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Guest getguestId() {
		return guestId;
	}

	public void setguestId(Guest guestId) {
		this.guestId = guestId;
	}
}
