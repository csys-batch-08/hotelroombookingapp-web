<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="assets/css/myBookings.css">
</head>
<body>
	<div class="header">
		<a href="guestDashboard.jsp"><h4
				style="position: relative; top: 23px; left: 23px; color: white; text-decoration: none;">HOME</h4></a>
		<a href="Logout"><h4
				style="float: right; position: relative; bottom: 36px; right: 28px; color: white;">LOGOUT</h4></a>
	</div>
	<table cellpadding=2px cellspacing=20px width="100%"
		id="myBookingsTable">
		<caption></caption>
		<thead>
			<tr>
				<th>Room Number</th>
				<th>CheckIn Date</th>
				<th>CheckOut Date</th>
				<th>Category</th>
				<th>Location</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${myRoomBookings }" var="rooms">
				<tr>
					<td><c:out value="${rooms.roomNumber }"></c:out></td>

					<fmt:parseDate value="${rooms.checkIn}" pattern="yyyy-MM-dd "
						type="date" var="roomCheckIn" />
					<fmt:formatDate value="${roomCheckIn}" pattern="dd-MM-yyyy"
						var="roomCheckInDate" />
					<td><c:out value="${roomCheckInDate }"></c:out></td>

					<fmt:parseDate value="${rooms.checkOut}" pattern="yyyy-MM-dd "
						type="date" var="roomCheckOut" />
					<fmt:formatDate value="${roomCheckOut}" pattern="dd-MM-yyyy"
						var="roomCheckOutDate" />
					<td><c:out value="${roomCheckOutDate }"></c:out></td>
					<td><c:out value="${rooms.category }"></c:out></td>
					<td><c:out value="${rooms.location }"></c:out></td>
				</tr>
			</c:forEach>
			<c:forEach items="${myWeddingHallBookings }" var="weddingHalls">
				<tr>
					<td><c:out value="${weddingHalls.roomNumber }"></c:out></td>
					<fmt:parseDate value="${weddingHalls.checkIn}"
						pattern="yyyy-MM-dd " type="date" var="weddingCheckIn" />
					<fmt:formatDate value="${weddingCheckIn}" pattern="dd-MM-yyyy"
						var="weddingCheckInDate" />
					<td><c:out value="${weddingCheckInDate }"></c:out></td>
					<fmt:parseDate value="${weddingHalls.checkOut}"
						pattern="yyyy-MM-dd " type="date" var="weddingCheckOut" />
					<fmt:formatDate value="${weddingCheckOut}" pattern="dd-MM-yyyy"
						var="weddingCheckOutDate" />
					<td><c:out value="${weddingCheckOutDate }"></c:out></td>
					<td><c:out value="${weddingHalls.category }"></c:out></td>
					<td><c:out value="${weddingHalls.location }"></c:out></td>
				</tr>
			</c:forEach>
			<c:forEach items="${myMeetingHallBookings }" var="meetingHalls">
				<tr>
					<td><c:out value="${meetingHalls.roomNumber }"></c:out></td>
					<fmt:parseDate value="${meetingHalls.checkIn}"
						pattern="yyyy-MM-dd " type="date" var="meetingCheckIn" />
					<fmt:formatDate value="${meetingCheckIn}" pattern="dd-MM-yyyy"
						var="meetingCheckInDate" />
					<td><c:out value="${meetingCheckInDate }"></c:out></td>
					<fmt:parseDate value="${meetingHalls.checkOut}"
						pattern="yyyy-MM-dd " type="date" var="meetingCheckOut" />
					<fmt:formatDate value="${meetingCheckOut}" pattern="dd-MM-yyyy"
						var="meetingCheckOutDate" />
					<td><c:out value="${meetingCheckOutDate }"></c:out></td>
					<td><c:out value="${meetingHalls.category }"></c:out></td>
					<td><c:out value="${meetingHalls.location }"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
<script src="assets/js/adminDashboard.js"></script>
</html>