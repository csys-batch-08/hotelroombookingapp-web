<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table,td,th,tr{
border:1px solid black;
border-collapse: collapse;
}
table th,td {
    text-align: center;
}
</style>
</head>
<body>
<div class="col-sm-8 align-items-center mt-3">
<div style="justify-content: space-between;width:100%;height: 80vh;overflow: auto;">
<table cellpadding=2px cellspacing=20px width="100%" class="myBooking">
<caption></caption>
<tr>
<th>Room Number</th>
<th>CheckIn Date</th>
<th>CheckOut Date</th>
<th>Category</th>
<th>Location</th>
</tr>

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

</table>
</div>
</div>
</body>
</html>