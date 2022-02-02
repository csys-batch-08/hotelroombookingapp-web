package com.hotelroombooking.dao;

import com.hotelroombooking.model.Admin;

public interface AdminDao {

	public Admin loginAdmin(String adminMail, String adminPassword);

}
