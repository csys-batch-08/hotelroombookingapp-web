package com.hotelroombooking.model;

import java.io.Serializable;
import java.util.Objects;

public class MeetingHallDetails implements Serializable {

	private int meetingHallNumber;
	private String status;
	private String category;
	private String location;
	private int price;

	public MeetingHallDetails(int meetingHallNumber, String status, String category, String location, int price) {
		super();
		this.meetingHallNumber = meetingHallNumber;
		this.status = status;
		this.category = category;
		this.location = location;
		this.price = price;
	}

	public MeetingHallDetails() {
		super();
	}

	@Override
	public String toString() {
		return "MeetingHallDetails [meetingHallNumber=" + meetingHallNumber + ", status=" + status + ", category="
				+ category + ", location=" + location + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, location, price, meetingHallNumber, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingHallDetails other = (MeetingHallDetails) obj;
		return Objects.equals(category, other.category) && Objects.equals(location, other.location)
				&& price == other.price && meetingHallNumber == other.meetingHallNumber
				&& Objects.equals(status, other.status);
	}

	public int getmeetingHallNumber() {
		return meetingHallNumber;
	}

	public void setmeetingHallNumber(int meetingHallNumber) {
		this.meetingHallNumber = meetingHallNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
