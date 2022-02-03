<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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
		<a href="adminDashboard.jsp"><h4
				style="position: relative; top: 23px; left: 23px; color: white; text-decoration: none;">HOME</h4></a>
		<a href="Logout"><h4
				style="float: right; position: relative; bottom: 36px; right: 28px; color: white;">LOGOUT</h4></a>
	</div>

	<br>
	<br>
	<input type"text" placeholder="Search">
	<button>Search</button>

	<table cellpadding=2px cellspacing=20px width="100%">
		<caption></caption>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Email</th>
			<th>Password</th>
			<th>Mobile Number</th>
		</tr>

		<c:forEach items="${userList }" var="guests">
			<tr>
				<td><c:out value="${guests.firstName }"></c:out></td>
				<td><c:out value="${guests.lastName }"></c:out></td>
				<td><c:out value="${guests.email }"></c:out></td>
				<td><c:out value="${guests.password }"></c:out></td>
				<td><c:out value="${guests.mobileNumber }"></c:out></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>