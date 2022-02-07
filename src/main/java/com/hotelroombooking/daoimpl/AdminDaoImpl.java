package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotelroombooking.dao.AdminDao;
import com.hotelroombooking.model.Admin;
import com.hotelroombooking.util.ConnectionUtil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin loginAdmin(String adminMail, String adminPassword) {
		String loginquery = "select * from admin where email=? and password=?";

		Admin adminObj = null;
		PreparedStatement p2 = null;
		Connection conn = ConnectionUtil.getDbConnection();
		try {

			p2 = conn.prepareStatement(loginquery);
			p2.setString(1, adminMail);
			p2.setString(2, adminPassword);
			ResultSet rs1 = p2.executeQuery();
			while (rs1.next()) {
				adminObj = new Admin(rs1.getString(2), rs1.getString(3));
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (p2 != null) {
				try {
					p2.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.getMessage();
				}
			}
		}
		return adminObj;
	}

}
