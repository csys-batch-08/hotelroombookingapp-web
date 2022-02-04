<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css">

<style type="text/css">
table, td, th, tr {
	border: 1px solid black;
	border-collapse: collapse;
}

table th, td {
	text-align: center;
}

.header {
	padding-top: 0px;
	background: #075fbc;
	color: white;
	padding-bottom: 24px;
}
</style>

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
					<td><c:out value="${rooms.checkIn }"></c:out></td>
					<td><c:out value="${rooms.checkOut }"></c:out></td>
					<td><c:out value="${rooms.category }"></c:out></td>
					<td><c:out value="${rooms.location }"></c:out></td>
				</tr>
			</c:forEach>

			<c:forEach items="${myWeddingHallBookings }" var="weddingHalls">
				<tr>
					<td><c:out value="${weddingHalls.roomNumber }"></c:out></td>
					<td><c:out value="${weddingHalls.checkIn }"></c:out></td>
					<td><c:out value="${weddingHalls.checkOut }"></c:out></td>
					<td><c:out value="${weddingHalls.category }"></c:out></td>
					<td><c:out value="${weddingHalls.location }"></c:out></td>
				</tr>
			</c:forEach>

			<c:forEach items="${myMeetingHallBookings }" var="meetingHalls">
				<tr>
					<td><c:out value="${meetingHalls.roomNumber }"></c:out></td>
					<td><c:out value="${meetingHalls.checkIn }"></c:out></td>
					<td><c:out value="${meetingHalls.checkOut }"></c:out></td>
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
<script type="text/javascript">
$(document).ready( function () {
    $('#myBookingsTable').DataTable();
} );

</script>
</html>