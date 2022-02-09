package com.hotelroombooking.model;

import java.io.Serializable;
import java.util.Objects;

public class Payment implements Serializable {
	private int id;
	private long cardNumber;
	private String expiryDate;
	private String cvv;
	private Guest guestId;

	public Payment(int id, long cardNumber, String expiryDate, String cvv, Guest guestId) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.guestId = guestId;
	}

	public Payment() {
		super();
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + ", cvv=" + cvv
				+ ", guestId=" + guestId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, cvv, expiryDate, guestId, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return cardNumber == other.cardNumber && Objects.equals(cvv, other.cvv)
				&& Objects.equals(expiryDate, other.expiryDate) && Objects.equals(guestId, other.guestId)
				&& id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Guest getGuestId() {
		return guestId;
	}

	public void setGuestId(Guest guestId) {
		this.guestId = guestId;
	}
}