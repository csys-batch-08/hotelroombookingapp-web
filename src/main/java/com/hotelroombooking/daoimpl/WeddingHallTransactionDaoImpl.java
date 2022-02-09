package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.dao.WeddingHallTransactionDao;
import com.hotelroombooking.message.Mail;
import com.hotelroombooking.message.Mailer;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.WeddingHallDetails;
import com.hotelroombooking.model.WeddingHallTransaction;
import com.hotelroombooking.util.ConnectionUtil;

public class WeddingHallTransactionDaoImpl implements WeddingHallTransactionDao {

	public static final String from = "hemnaathrsurya@gmail.com";
	public static final String password = "hangover@18!!";
	public static final String subject = "Hotel Room Booking Application";

	/**
	 * method to book wedding hall
	 */
	@Override
	public boolean bookWeddingHall(HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int vacantWeddingRoomNumber = 0;
		int guestId = 0;
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		boolean flag = false;
		try {
			String fetchVacantWeddingRoom = "select wedding_hall_number from wedding_hall_details where status='vacant' and "
					+ "category=? and location=?";
			pstmt1 = conn.prepareStatement(fetchVacantWeddingRoom);
			Guest guestObj = (Guest) session.getAttribute("currentUser");
			WeddingHallTransaction weddingHallTransObj = (WeddingHallTransaction) session
					.getAttribute("bookWeddingHallDetails");
			pstmt1.setString(1, weddingHallTransObj.getCategory());
			pstmt1.setString(2, weddingHallTransObj.getLocation());
			ResultSet rs = pstmt1.executeQuery();
			if (rs.next()) {
				vacantWeddingRoomNumber = rs.getInt(1);
			}
			if (vacantWeddingRoomNumber != 0) {
				try {
					String bookWeddingRoomQuery = "insert into wedding_hall_transaction(wedding_hall_number,check_in,check_out,"
							+ "category,location,guest_id) values(?,?,?,?,?,?)";
					pstmt2 = conn.prepareStatement(bookWeddingRoomQuery);
					GuestDaoImpl guestDaoObj = new GuestDaoImpl();
					guestId = guestDaoObj.findGuestId(guestObj);
					weddingHallTransObj.setroomNumber(vacantWeddingRoomNumber);
					pstmt2.setInt(1, vacantWeddingRoomNumber);
					pstmt2.setDate(2, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
					pstmt2.setDate(3, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckOut()).getTime()));
					pstmt2.setString(4, weddingHallTransObj.getCategory());
					pstmt2.setString(5, weddingHallTransObj.getLocation());
					pstmt2.setInt(6, guestId);
					flag = pstmt2.executeUpdate() > 0;
					if (flag) {
						try {
							String updateWeddingRoomQuery = "update wedding_hall_details set status='occupied' where "
									+ "wedding_hall_number=?";
							pstmt3 = conn.prepareStatement(updateWeddingRoomQuery);
							pstmt3.setInt(1, vacantWeddingRoomNumber);
							pstmt3.executeUpdate();
						} catch (Exception e) {
							e.getMessage();
						} finally {
							if (pstmt3 != null) {
								pstmt3.close();
							}
							if (conn != null) {
								conn.close();
							}
						}
						Mailer.send(from, password, guestObj.getEmail(), subject,
								Mail.bookWeddingHallMail(weddingHallTransObj));
					}
				} catch (Exception e) {
					e.getMessage();
				} finally {
					if (pstmt2 != null) {
						pstmt2.close();
					}
					if (conn != null) {
						conn.close();
					}
				}
			} else {
				session.setAttribute("NoWeddingHallToBook", "noWeddingHall");
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (pstmt1 != null) {
				try {
					pstmt1.close();
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
	 * method to cancel meeting hall
	 */
	@Override
	public boolean cancelWeddingHall(HttpSession session) {
		boolean flag = false;
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = null;
		try {
			String updateCancelWeddingRoomQuery = "update wedding_hall_details set status='vacant' where wedding_hall_number=?";
			pstmt = conn.prepareStatement(updateCancelWeddingRoomQuery);
			Guest guestObj = (Guest) session.getAttribute("currentUser");
			WeddingHallTransaction weddingHallTransObj = (WeddingHallTransaction) session
					.getAttribute("cancelWeddingHallDetails");
			pstmt.setInt(1, weddingHallTransObj.getroomNumber());
			flag = pstmt.executeUpdate() > 0;
			if (flag) {
				Mailer.send(from, password, guestObj.getEmail(), subject,
						Mail.cancelWeddingHallMail(weddingHallTransObj));
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
		return flag;
	}

	/**
	 * method to update meeting hall
	 */
	@Override
	public boolean updateWeddingHall(HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Guest guestObj = (Guest) session.getAttribute("currentUser");
		WeddingHallTransaction weddingHallTransObj = (WeddingHallTransaction) session
				.getAttribute("updateWeddingHallDetails");
		int vacantWeddingRoomNumber = 0;
		int guestId = 0;
		boolean flag = false;
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		try {
			String fetchVacantRoom = "select wedding_hall_number from wedding_hall_details where status='vacant' and category=? "
					+ "and location=?";
			pstmt2 = conn.prepareStatement(fetchVacantRoom);
			pstmt2.setString(1, weddingHallTransObj.getCategory());
			pstmt2.setString(2, weddingHallTransObj.getLocation());
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				vacantWeddingRoomNumber = rs.getInt(1);
			}
			if (vacantWeddingRoomNumber != 0) {
				try {
					String updateRoomQuery = "update wedding_hall_transaction set check_in=?,check_out=?,category=?,location=?"
							+ " where wedding_hall_number=?";
					pstmt1 = conn.prepareStatement(updateRoomQuery);
					pstmt1.setDate(1, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
					pstmt1.setDate(2, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckOut()).getTime()));
					pstmt1.setString(3, weddingHallTransObj.getCategory());
					pstmt1.setString(4, weddingHallTransObj.getLocation());
					pstmt1.setInt(5, weddingHallTransObj.getroomNumber());
					pstmt1.executeUpdate();
				} catch (Exception e) {
					e.getMessage();
				} finally {
					if (pstmt1 != null) {
						pstmt1.close();
					}
					if (conn != null) {
						conn.close();
					}
				}
				try {
					conn = ConnectionUtil.getDbConnection();
					String updateRoomQuery2 = "update wedding_hall_transaction set wedding_hall_number=? where check_in=? "
							+ "and check_out=? and category=? and location=? and guest_id=?";
					pstmt3 = conn.prepareStatement(updateRoomQuery2);
					GuestDaoImpl guestDaoObj = new GuestDaoImpl();
					guestId = guestDaoObj.findGuestId(guestObj);
					pstmt3.setInt(1, vacantWeddingRoomNumber);
					pstmt3.setDate(2, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
					pstmt3.setDate(3, new java.sql.Date(sdf.parse(weddingHallTransObj.getCheckIn()).getTime()));
					pstmt3.setString(4, weddingHallTransObj.getCategory());
					pstmt3.setString(5, weddingHallTransObj.getLocation());
					pstmt3.setInt(6, guestId);
					pstmt3.executeUpdate();
				} catch (Exception e) {
					e.getMessage();
				} finally {
					if (pstmt3 != null) {
						pstmt3.close();
					}
					if (conn != null) {
						conn.close();
					}
				}
				Mailer.send(from, password, guestObj.getEmail(), subject,
						Mail.updateWeddingHallMail(weddingHallTransObj));
				try {
					conn = ConnectionUtil.getDbConnection();
					String updateRoomQuery3 = "update wedding_hall_details set status='vacant' where wedding_hall_number=?";
					pstmt4 = conn.prepareStatement(updateRoomQuery3);
					pstmt4.setInt(1, weddingHallTransObj.getroomNumber());
					pstmt4.executeUpdate();
				} catch (Exception e) {
					e.getMessage();
				} finally {
					if (pstmt4 != null) {
						pstmt4.close();
					}
					if (conn != null) {
						conn.close();
					}
				}
				weddingHallTransObj.setroomNumber(vacantWeddingRoomNumber);
				try {
					conn = ConnectionUtil.getDbConnection();
					String updateRoomQuery4 = "update wedding_hall_details set status='occupied' where wedding_hall_number=?";
					pstmt5 = conn.prepareStatement(updateRoomQuery4);
					pstmt5.setInt(1, vacantWeddingRoomNumber);
					flag = pstmt5.executeUpdate() > 0;
				} catch (Exception e) {
					e.getMessage();
				} finally {
					if (pstmt5 != null) {
						pstmt5.close();
					}
					if (conn != null) {
						conn.close();
					}
				}

			} else {
				session.setAttribute("noWeddingHallsToUpdate", "noWeddingHallUpdate");
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (pstmt2 != null) {
				try {
					pstmt2.close();
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
	 * method to list booked wedding halls
	 */
	@Override
	public List<WeddingHallTransaction> showWeddingHallBooking(Guest guestObj) {
		int guestId = 0;
		WeddingHallTransaction weddingHallTrans = null;
		List<WeddingHallTransaction> weddingHallBooking = new ArrayList<>();
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = null;
		try {
			String showWeddingHallBookingQuery = "select wedding_hall_number,check_in,check_out,category,location from "
					+ "wedding_hall_transaction where guest_id=?";
			pstmt = conn.prepareStatement(showWeddingHallBookingQuery);
			GuestDaoImpl guestDaoObj = new GuestDaoImpl();
			guestId = guestDaoObj.findGuestId(guestObj);
			pstmt.setInt(1, guestId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				weddingHallTrans = new WeddingHallTransaction(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				weddingHallBooking.add(weddingHallTrans);
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
		return weddingHallBooking;
	}

	/**
	 * mehodd to add wedding hall
	 */
	@Override
	public boolean addWeddingHallAdmin(HttpSession session) {
		boolean flag = false;
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = null;
		try {
			WeddingHallDetails weddingHallDetailsObj = (WeddingHallDetails) session
					.getAttribute("addWeddingHallDetails");
			String addWeddingHallQuery = "insert into wedding_hall_details(wedding_hall_number,category,location,price) "
					+ "values(?,?,?,?)";
			pstmt = conn.prepareStatement(addWeddingHallQuery);
			pstmt.setInt(1, weddingHallDetailsObj.getweddingHallNumber());
			pstmt.setString(2, weddingHallDetailsObj.getCategory());
			pstmt.setString(3, weddingHallDetailsObj.getLocation());
			pstmt.setInt(4, weddingHallDetailsObj.getPrice());
			flag = pstmt.executeUpdate() > 0;
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

	/**
	 * method to delete wedding hall
	 */
	@Override
	public boolean deleteWeddingHallAdmin(HttpSession session) {
		boolean flag = false;
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = null;
		try {
			WeddingHallDetails weddingHallDetailsObj = (WeddingHallDetails) session
					.getAttribute("deleteWeddingHallDetails");
			String deleteWeddingHallQuery = "delete from wedding_hall_details where wedding_hall_number=?";
			pstmt = conn.prepareStatement(deleteWeddingHallQuery);
			pstmt.setInt(1, weddingHallDetailsObj.getweddingHallNumber());
			flag = pstmt.executeUpdate() > 0;
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

	/**
	 * method to edit wedding hall
	 */
	@Override
	public boolean updateWeddingHallAdmin(HttpSession session) {
		boolean flag = false;
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = null;
		try {
			WeddingHallDetails weddingHallDetailsObj = (WeddingHallDetails) session
					.getAttribute("editWeddingHallDetails");
			String updateRoomQuery = "update wedding_hall_details set category=?,location=?,price=? where wedding_hall_number=?";
			pstmt = conn.prepareStatement(updateRoomQuery);
			pstmt.setString(1, weddingHallDetailsObj.getCategory());
			pstmt.setString(2, weddingHallDetailsObj.getLocation());
			pstmt.setInt(3, weddingHallDetailsObj.getPrice());
			pstmt.setInt(4, weddingHallDetailsObj.getweddingHallNumber());
			flag = pstmt.executeUpdate() > 0;
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

	/**
	 * method to find the booking price of meeting hall
	 */
	@Override
	public int findBookWeddingPrice(HttpSession session) {
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = null;
		try {
			WeddingHallTransaction weddingHallTransObj = (WeddingHallTransaction) session
					.getAttribute("bookWeddingHallDetails");
			String findPriceQuery = "select price from wedding_hall_details where category=?";
			pstmt = conn.prepareStatement(findPriceQuery);
			pstmt.setString(1, weddingHallTransObj.getCategory());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
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
		return 0;
	}

	/**
	 * method to find updated meeting hall price
	 */
	@Override
	public int findUpdateWeddingPrice(HttpSession session) {
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = null;
		try {
			WeddingHallTransaction weddingHallTransObj = (WeddingHallTransaction) session
					.getAttribute("updateWeddingHallDetails");
			String findPriceQuery = "select price from wedding_hall_details where category=?";
			pstmt = conn.prepareStatement(findPriceQuery);
			pstmt.setString(1, weddingHallTransObj.getCategory());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
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
		return 0;
	}
}