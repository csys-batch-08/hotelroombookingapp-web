package com.hotelroombooking.model;

import java.util.Objects;

public class Guest 
{
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long mobileNumber;
	public Guest(String firstName, String lastName, String email, String password,long mobileNumber) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
	public Guest() {
		super();
	}
	@Override
	public String toString() {
		return "\nfirstName=" + firstName + "\nlastName=" + lastName + "\nemail=" + email + "\npassword="
				+ password + "\nmobileNumber=" + mobileNumber;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, mobileNumber, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		return Objects.equals(email, other.email)&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& mobileNumber == other.mobileNumber && Objects.equals(password, other.password);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}