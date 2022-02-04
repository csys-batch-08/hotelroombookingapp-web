<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css">
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

	<table cellpadding=2px cellspacing=20px width="100%" id="usersTable">
		<caption></caption>
		<thead>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Email</th>
			<th>Password</th>
			<th>Mobile Number</th>
		</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${userList }" var="guests">
			<tr>
				<td><c:out value="${guests.firstName }"></c:out></td>
				<td><c:out value="${guests.lastName }"></c:out></td>
				<td><c:out value="${guests.email }"></c:out></td>
				<td><c:out value="${guests.password }"></c:out></td>
				<td><c:out value="${guests.mobileNumber }"></c:out></td>
			</tr>
		</c:forEach>
		</tbody>

	</table>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#usersTable').DataTable();
} );

</script>
</html>