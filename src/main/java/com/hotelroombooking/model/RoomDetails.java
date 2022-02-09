package com.hotelroombooking.model;

import java.io.Serializable;
import java.util.Objects;

public class RoomDetails implements Serializable {

	private int roomNumber;
	private String status;
	private String category;
	private String location;
	private int price;

	public RoomDetails(int roomNumber, String status, String category, String location, int price) {
		super();
		this.roomNumber = roomNumber;
		this.status = status;
		this.category = category;
		this.location = location;
		this.price = price;
	}

	public RoomDetails() {
		super();
	}

	@Override
	public String toString() {
		return "RoomDetails [roomNumber=" + roomNumber + ", status=" + status + ", category=" + category + ", location="
				+ location + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, location, price, roomNumber, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomDetails other = (RoomDetails) obj;
		return Objects.equals(category, other.category) && Objects.equals(location, other.location)
				&& price == other.price && roomNumber == other.roomNumber && Objects.equals(status, other.status);
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
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
