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
<div class="col-sm-8 align-items-center mt-3" >
<div style="justify-content: space-between;width:100%">
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
</div>
</div>
</body>
</html>