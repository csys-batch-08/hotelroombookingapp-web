package com.hotelroombooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.hotelroombooking.dao.RoomTransactionDao;
import com.hotelroombooking.message.Mail;
import com.hotelroombooking.message.Mailer;
import com.hotelroombooking.model.Guest;
import com.hotelroombooking.model.RoomDetails;
import com.hotelroombooking.model.RoomTransaction;
import com.hotelroombooking.util.ConnectionUtil;

public class RoomTransactionDaoImpl implements RoomTransactionDao {
	public static final String from = "hemnaathrsurya@gmail.com";
	public static final String password = "hangover@18!!";
	public static final String subject = "Hotel Room Booking Application";

	@Override
	public boolean bookRoom(HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Guest guestObj = (Guest) session.getAttribute("currentUser");
		RoomTransaction roomTransObj = (RoomTransaction) session.getAttribute("bookRoomDetails");

		int vacantRoomNumber = 0;
		int guestId = 0;
		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		boolean flag = false;

		try {

			String fetchVacantRoom = "select room_number from room_details where status='vacant' and category=? and location=?";

			pstmt1 = conn.prepareStatement(fetchVacantRoom);

			pstmt1.setString(1, roomTransObj.getCategory());
			pstmt1.setString(2, roomTransObj.getLocation());

			ResultSet rs = pstmt1.executeQuery();

			if (rs.next()) {
				flag = true;
				vacantRoomNumber = rs.getInt(1);

			}

			if (vacantRoomNumber != 0) {
				try {
					String bookRoomQuery = "insert into room_transaction(room_number,check_in,check_out,category,location,guest_id) values(?,?,?,?,?,?)";
					pstmt2 = conn.prepareStatement(bookRoomQuery);

					GuestDaoImpl guestDaoObj = new GuestDaoImpl();

					guestId = guestDaoObj.findGuestId(guestObj);

					roomTransObj.setroomNumber(vacantRoomNumber);
					pstmt2.setInt(1, vacantRoomNumber);
					pstmt2.setDate(2, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
					pstmt2.setDate(3, new java.sql.Date(sdf.parse(roomTransObj.getCheckOut()).getTime()));
					pstmt2.setString(4, roomTransObj.getCategory());
					pstmt2.setString(5, roomTransObj.getLocation());
					pstmt2.setInt(6, guestId);

					flag = pstmt2.executeUpdate() > 0;

					if (flag) {
						try {
							String updateBookRoomQuery = "update room_details set status='occupied' where room_number=?";
							pstmt3 = conn.prepareStatement(updateBookRoomQuery);
							pstmt3.setInt(1, vacantRoomNumber);

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

						Mailer.send(from, password, guestObj.getEmail(), subject, Mail.bookRoomMail(roomTransObj));
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
				session.setAttribute("NoRoomsToBook", "noRooms");
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

	@Override
	public boolean cancelRoom(HttpSession session) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		Connection conn = ConnectionUtil.getDbConnection();

		try {
			Guest guestObj = (Guest) session.getAttribute("currentUser");
			RoomTransaction roomTransObj = (RoomTransaction) session.getAttribute("cancelRoomDetails");

			String updateCancelRoomQuery = "update room_details set status='vacant' where room_number=?";
			pstmt = conn.prepareStatement(updateCancelRoomQuery);

			pstmt.setInt(1, roomTransObj.getroomNumber());

			flag = pstmt.executeUpdate() > 0;
			if (flag) {
				Mailer.send(from, password, guestObj.getEmail(), subject, Mail.cancelRoomMail(roomTransObj));

			}

		}

		catch (Exception e) {
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

	@Override
	public boolean updateRoom(HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int vacantRoomNumber = 0;
		int guestId = 0;
		boolean flag = false;

		Connection conn = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;

		try {

			Guest guestObj = (Guest) session.getAttribute("currentUser");
			RoomTransaction roomTransObj = (RoomTransaction) session.getAttribute("updateRoomDetails");

			String fetchVacantRoom = "select room_number from room_details where status='vacant' and category=? and location=?";

			pstmt2 = conn.prepareStatement(fetchVacantRoom);

			pstmt2.setString(1, roomTransObj.getCategory());
			pstmt2.setString(2, roomTransObj.getLocation());

			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				vacantRoomNumber = rs.getInt(1);
			}

			if (vacantRoomNumber != 0) {
				try {
					conn = ConnectionUtil.getDbConnection();
					String updateRoomQuery = "update room_transaction set check_in=?,check_out=?,category=?,location=? where room_number=?";
					pstmt1 = conn.prepareStatement(updateRoomQuery);

					pstmt1.setDate(1, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
					pstmt1.setDate(2, new java.sql.Date(sdf.parse(roomTransObj.getCheckOut()).getTime()));
					pstmt1.setString(3, roomTransObj.getCategory());
					pstmt1.setString(4, roomTransObj.getLocation());
					pstmt1.setInt(5, roomTransObj.getroomNumber());

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
					String updateRoomQuery2 = "update room_transaction set room_number=? where check_in=? and check_out=? and category=? and location=? and guest_id=?";
					pstmt3 = conn.prepareStatement(updateRoomQuery2);

					GuestDaoImpl guestDaoObj = new GuestDaoImpl();
					guestId = guestDaoObj.findGuestId(guestObj);

					pstmt3.setInt(1, vacantRoomNumber);
					pstmt3.setDate(2, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
					pstmt3.setDate(3, new java.sql.Date(sdf.parse(roomTransObj.getCheckIn()).getTime()));
					pstmt3.setString(4, roomTransObj.getCategory());
					pstmt3.setString(5, roomTransObj.getLocation());
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

				try {
					conn = ConnectionUtil.getDbConnection();
					String updateRoomQuery3 = "update room_details set status='vacant' where room_number=?";
					pstmt4 = conn.prepareStatement(updateRoomQuery3);

					pstmt4.setInt(1, roomTransObj.getroomNumber());
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

				roomTransObj.setroomNumber(vacantRoomNumber);

				try {
					String updateRoomQuery4 = "update room_details set status='occupied' where room_number=?";
					pstmt5 = conn.prepareStatement(updateRoomQuery4);
					pstmt5.setInt(1, vacantRoomNumber);
					flag = pstmt5.executeUpdate() > 0;

					if (flag) {
						Mailer.send(from, password, guestObj.getEmail(), subject, Mail.updateRoomMail(roomTransObj));

					}
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
				session.setAttribute("noRoomsToUpdate", "noUpdate");
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

	@Override
	public List<RoomTransaction> showRoomBooking(Guest guestObj) {
		int guestId = 0;
		List<RoomTransaction> roomBooking = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = ConnectionUtil.getDbConnection();

		try {
			String showRoomBookingQuery = "select room_number,check_in,check_out,category,location from room_transaction where guest_id=?";

			pstmt = conn.prepareStatement(showRoomBookingQuery);

			GuestDaoImpl guestDaoObj = new GuestDaoImpl();
			guestId = guestDaoObj.findGuestId(guestObj);

			pstmt.setInt(1, guestId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomTransaction roomTrans = new RoomTransaction(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				roomBooking.add(roomTrans);
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

		return roomBooking;
	}

	@Override
	public boolean addRoomAdmin(HttpSession session) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		Connection conn = ConnectionUtil.getDbConnection();
		try {

			RoomDetails roomDetailsObj = (RoomDetails) session.getAttribute("addRoomDetails");

			String addRoomQuery = "insert into room_details(room_number,category,location,price) values(?,?,?,?)";

			pstmt = conn.prepareStatement(addRoomQuery);

			pstmt.setInt(1, roomDetailsObj.getRoomNumber());
			pstmt.setString(2, roomDetailsObj.getCategory());
			pstmt.setString(3, roomDetailsObj.getLocation());
			pstmt.setInt(4, roomDetailsObj.getPrice());

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

	@Override
	public boolean deleteRoomAdmin(HttpSession session) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		Connection conn = ConnectionUtil.getDbConnection();

		try {

			RoomDetails roomDetailsObj = (RoomDetails) session.getAttribute("deleteRoomDetails");

			String deleteRoomQuery = "delete from room_details where room_number=?";

			pstmt = conn.prepareStatement(deleteRoomQuery);

			pstmt.setInt(1, roomDetailsObj.getRoomNumber());

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

	@Override
	public boolean updateRoomAdmin(HttpSession session) {

		boolean flag = false;
		PreparedStatement pstmt = null;
		Connection conn = ConnectionUtil.getDbConnection();

		try {

			RoomDetails roomDetailsObj = (RoomDetails) session.getAttribute("editRoomDetails");

			String updateRoomQuery = "update room_details set category=?,location=?,price=? where room_number=?";

			pstmt = conn.prepareStatement(updateRoomQuery);

			pstmt.setString(1, roomDetailsObj.getCategory());
			pstmt.setString(2, roomDetailsObj.getLocation());
			pstmt.setInt(3, roomDetailsObj.getPrice());
			pstmt.setInt(4, roomDetailsObj.getRoomNumber());

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

	@Override
	public int findBookRoomPrice(HttpSession session) {
		PreparedStatement pstmt = null;
		Connection conn = ConnectionUtil.getDbConnection();

		try {
			RoomTransaction roomTransObj = (RoomTransaction) session.getAttribute("bookRoomDetails");
			String findPriceQuery = "select price from room_details where category=?";

			pstmt = conn.prepareStatement(findPriceQuery);
			pstmt.setString(1, roomTransObj.getCategory());
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

	@Override
	public int findUpdateRoomPrice(HttpSession session) {
		PreparedStatement pstmt = null;
		Connection conn = ConnectionUtil.getDbConnection();

		try {
			RoomTransaction roomTransObj = (RoomTransaction) session.getAttribute("updateRoomDetails");
			String findPriceQuery = "select price from room_details where category=?";

			pstmt = conn.prepareStatement(findPriceQuery);
			pstmt.setString(1, roomTransObj.getCategory());
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