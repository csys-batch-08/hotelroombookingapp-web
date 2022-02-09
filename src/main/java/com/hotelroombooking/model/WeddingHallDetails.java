package com.hotelroombooking.model;

import java.io.Serializable;
import java.util.Objects;

public class WeddingHallDetails implements Serializable {

	private int weddingHallNumber;
	private String status;
	private String category;
	private String location;
	private int price;

	public WeddingHallDetails(int weddingHallNumber, String status, String category, String location, int price) {
		super();
		this.weddingHallNumber = weddingHallNumber;
		this.status = status;
		this.category = category;
		this.location = location;
		this.price = price;
	}

	public WeddingHallDetails() {
		super();
	}

	@Override
	public String toString() {
		return "WeddingHallDetails [weddingHallNumber=" + weddingHallNumber + ", status=" + status + ", category="
				+ category + ", location=" + location + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, location, price, weddingHallNumber, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeddingHallDetails other = (WeddingHallDetails) obj;
		return Objects.equals(category, other.category) && Objects.equals(location, other.location)
				&& price == other.price && weddingHallNumber == other.weddingHallNumber
				&& Objects.equals(status, other.status);
	}

	public int getweddingHallNumber() {
		return weddingHallNumber;
	}

	public void setweddingHallNumber(int weddingHallNumber) {
		this.weddingHallNumber = weddingHallNumber;
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
