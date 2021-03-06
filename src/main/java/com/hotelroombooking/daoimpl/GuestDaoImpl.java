package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.dao.GuestDao;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.util.ConnectionUtil;

public class GuestDaoImpl implements GuestDao {
	/**
	 * method for registration
	 */
	@Override
	public boolean registerGuest(String rgFirstname, String rgLastname, String rgMail, String rgPassword,
			String rgConfirmPassword, long rgMobileNumber) {
		String registerquery = "insert into guest_details (firstname,lastname,email,password,mobile) values (?,?,?,?,?)";
		Connection conn = ConnectionUtil.getDbConnection();
		boolean flag = false;
		PreparedStatement p = null;
		try {
			p = conn.prepareStatement(registerquery);
			p.setString(1, rgFirstname);
			p.setString(2, rgLastname);
			p.setString(3, rgMail);
			p.setString(4, rgPassword);
			p.setLong(5, rgMobileNumber);
			flag = p.executeUpdate() > 0;
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (p != null) {
				try {
					p.close();
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
		return flag;
	}

	/**
	 * method for guest login
	 */
	@Override
	public Guest loginGuest(String gUserName, String gPassword) {
		String loginquery = "select * from guest_details where email=? and password=?";
		Connection conn = ConnectionUtil.getDbConnection();
		Guest guestObj = null;
		PreparedStatement p2 = null;
		try {
			p2 = conn.prepareStatement(loginquery);
			p2.setString(1, gUserName);
			p2.setString(2, gPassword);
			ResultSet rs1 = p2.executeQuery();
			while (rs1.next()) {
				guestObj = new Guest(rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5),
						rs1.getLong(6));
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
		return guestObj;
	}

	/**
	 * method for finding guest Id
	 */
	@Override
	public int findGuestId(Guest guestObj) {
		int guestId = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			String findIdQuery = "select id from guest_details where email=?";
			conn = ConnectionUtil.getDbConnection();
			pstmt = conn.prepareStatement(findIdQuery);
			pstmt.setString(1, guestObj.getEmail());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				guestId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return guestId;
	}

	/**
	 * method for listing all bookings
	 */
	@Override
	public List<Guest> showAllUser() {
		List<Guest> guestList = new ArrayList<>();
		String allUserQuery = "select firstname,lastname,email,password,mobile from guest_details";
		Connection conn = ConnectionUtil.getDbConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(allUserQuery);
			while (rs.next()) {
				Guest guest = new Guest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5));
				guestList.add(guest);
			}

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
		return guestList;
	}

	/**
	 * method for forgot password
	 */
	@Override
	public boolean forgetPassword(HttpSession session) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			Guest guestObj = (Guest) session.getAttribute("forgetPassword");
			String forgetPasswordQuery = "update guest_details set password=? where email=?";
			conn = ConnectionUtil.getDbConnection();
			pstmt = conn.prepareStatement(forgetPasswordQuery);
			pstmt.setString(1, guestObj.getPassword());
			pstmt.setString(2, guestObj.getEmail());
			flag = pstmt.executeUpdate() > 0;
			pstmt.executeUpdate("commit");
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return flag;
	}

}
