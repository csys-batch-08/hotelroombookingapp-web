<%@page import="com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.model.WeddingHallTransaction"%>
<%@page import="com.hotelroombooking.model.MeetingHallTransaction"%>
<%@page import="com.hotelroombooking.model.Guest"%>
<%@page import="com.hotelroombooking.daoimpl.RoomTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.model.RoomTransaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Guest Dashboard</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style type="text/css">
table,td,th,tr{
border:1px solid black;
border-collapse: collapse;
}
.gestDashboard form {
    background: #ffffff;
    padding: 10px;
    width: 51%;
    margin-bottom: 18px;
    margin: 10px auto;
    border-radius: 10px;
    box-shadow: 0px 0px 14px 0px #dbdbdb;
}.gestDashboard form input {
    width: 100%;
    margin-bottom: 10px;
}
table th,td {
    text-align: center;
}
.sideMenuItems ul {
    padding: 0px;
}
.sideMenuItems ul li {
    list-style: none;
    line-hupdateMeetingHall: 1.5;
    font-size: 15px;
    cursor: pointer;
    font-wupdateMeetingHall: bold;
    /* border-bottom: 1px solid #565656; */
    margin-bottom: 6px;
    color: white;
}
.sideMenu {
    hupdateMeetingHall: 100%;
    /* box-shadow: 0px 0px 17px 0px #d5d5d5; */
    border-radius: 0px;
    padding: 20px;
    background: black;
   
}
ul.dropdown {
    padding-left: 15px;
    margin-bottom: 19px;
    box-shadow: 0px 0px 36px 0px #1c1c1c;
    border-radius: 2px;
    margin-top: 9px;
}
.sideMenuItems ul li a {
    border-bottom: 2px solid #007bff;
    border-radius: 4px;
    padding: 2px 4px;
    margin-bottom: 0px;
    display: block;
}
.header {
    padding-top: 24px;
    background: #075fbc;
    color: white;
    padding-bottom: 24px;
}
.gestDashboard form {
    color: black;
    font-wupdateMeetingHall: bold;
}
.loginForm input {
    margin-bottom: 11px;
}
.formBtn {
    text-align: center;
}
.formBtn input, .formBtn button {
    margin: 0px 0px;
    font-size: 13px;
    padding: 5px 15px;
    background: #34c2df;
    color: black;
    border: none;
    font-wupdateMeetingHall: bold;
    width:100%
}

</style>

</head>
<body>


<c:if test="${not empty NoRoomsToBook }">
<script type="text/javascript">
alert("No Rooms Availble");
<% session.removeAttribute("NoRoomsToBook"); %>
</script>
</c:if>


<c:if test="${not empty noRoomsToUpdate }">
<script type="text/javascript">
alert("No Rooms Availble");
<% session.removeAttribute("noRoomsToUpdate"); %>
</script>
</c:if>


<c:if test="${not empty NoWeddingHallToBook }">
<script type="text/javascript">
alert("No Wedding Hall Availble");
<% session.removeAttribute("NoWeddingHallToBook"); %>
</script>
</c:if>

<c:if test="${not empty noWeddingHallsToUpdate }">
<script type="text/javascript">
alert("No Wedding Hall Availble");
<% session.removeAttribute("noWeddingHallsToUpdate"); %>
</script>
</c:if>

<c:if test="${not empty noMeetingHallToBook }">
<script type="text/javascript">
alert("No Meeting Hall Availble");
<% session.removeAttribute("noMeetingHallToBook"); %>
</script>
</c:if>

<c:if test="${not empty noMeetingHallToUpdate }">
<script type="text/javascript">
alert("No Wedding Hall Availble");
<%session.removeAttribute("noMeetingHallToUpdate");%>
</script>
</c:if>



<div class="header container-fluid">

 <%Guest guestObj = (Guest)session.getAttribute("currentUser");%>
	<h3>Welcome <c:out value="${currentUser.firstName }"></c:out></h3>
	
	
</div>




<script type="text/javascript">
function bookRoom()
{
	var room=document.getElementById("bookRoom");
	room.style.display="flex";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
	
}
function updateRoom()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="flex";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function cancelRoom()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="flex";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function bookWeddingHall()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="flex";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function updateWeddingHall()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="flex";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function cancelWeddingHall()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="flex";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function bookMeetingHall()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="flex";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function updateMeetingHall()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="flex";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function cancelMeetingHall()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="flex";
	var room=document.getElementById("myBookings");
	room.style.display="none";
}function myBookings()
{
	var room=document.getElementById("bookRoom");
	room.style.display="none";
	var room=document.getElementById("updateRoom");
	room.style.display="none";
	var room=document.getElementById("cancelRoom");
	room.style.display="none";
	var room=document.getElementById("bookWeddingHall");
	room.style.display="none";
	var room=document.getElementById("updateWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("bookMeetingHall");
	room.style.display="none";
	var room=document.getElementById("updateMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("myBookings");
	room.style.display="block";
}
</script>

<div class="container-fluid">
<div class="row gestDashboard">
<div class="col-sm-4 p-0">
<div class="sideMenu">
		<div class="sideMenuItems">
			<ul>
				<li><a onclick="roomShow()">ROOMS</a>
					<ul class="dropdown">
						<li onclick="bookRoom()">Book Room</li>
						<li onclick="updateRoom()">Update Room</li>
						<li onclick="cancelRoom()">Cancel Room</li>
					</ul>
				</li>
				<li><a onclick="weddingShow()">WEDDING HALLS</a>
					<ul class="dropdown">
						<li onclick="bookWeddingHall()">Book Wedding Hall</li>
						<li onclick="updateWeddingHall()">Update Wedding Hall</li>
						<li onclick="cancelWeddingHall()">Cancel Wedding Hall</li>
					</ul>
				</li>
				<li><a onclick="meetingShow()">MEETING HALL BOOKING</a>
					<ul class="dropdown">
						<li onclick="bookMeetingHall()">Book Meeting Hall</li>
						<li onclick="updateMeetingHall()">Update Meeting Hall</li>
						<li onclick="cancelMeetingHall()">Cancel Meeting Hall</li>
					</ul>
				</li>
				<li><a onclick="myBookings()">MY BOOKINGS</a></li>
			</ul>
			<div class="logoutbtn">
				<a href="Logout"><h3>LOGOUT</h3></a>
			</div>
		</div>
		
	</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="bookRoom">
<div style="justify-content: space-between;width:100%">
	<form action="BookRoom" method="post">
		<label>Check-In   :</label>
		<input type="date" id="bookRoomCheckIn" name="checkIn" required class="form-control">
		<label>Check-Out:</label>
		<input type="date" id="bookRoomCheckOut" name="checkOut" required class="form-control">
		<label>Category :</label>
		<input type="text" id="cat" list="category" name="category" required class="form-control">
		<datalist id="category" name="category">
		<option value="premium">premium</option>
		<option value="luxury">luxury</option>
		<option value="standard">standard</option>
		<option value="budget">budget</option>
		</datalist>
		<label>Location :</label>
		<input type="text" id="loc" list="location" name="location" required class="form-control"><br>
		<datalist id="location" name="location" >
		<option value="chennai">Chennai</option>
		<option value="coimbatore">Coimbatore</option>
		<option value="madurai">Madurai</option>
		<option value="sivakasi">Sivakasi</option>
		</datalist>
		<div class="formBtn">
			<input type="submit" value="Book Room">
		</div>
	</form>
</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="updateRoom">
<div style="justify-content: space-between;width:100%">
	<form action="UpdateRoom" method="post">
	<label>Room Number :</label>
	<input type="text" id=roomNumber name="roomNumber" required class="form-control">
	<label>Check-In :</label>
	<input type="date" id="updateRoomCheckIn" name="checkIn" required class="form-control">
	<label>Check-Out :</label>
	<br><input type="date" id="updateRoomCheckOut" name="checkOut" required class="form-control">
	<label>Category :</label>
	<br><input type="text" id="cat" list="category" name="category" required class="form-control">
	<datalist id="category" name="category" >
	<option value="premium">premium</option>
	<option value="luxury">luxury</option>
	<option value="standard">standard</option>
	<option value="budget">budget</option>
	</datalist>
	<label>Location :</label>
	<br><input type="text" id="loc" list="location" name="location" required class="form-control">
	<datalist id="location" name="location">
	<option value="chennai">Chennai</option>
	<option value="coimbatore">Coimbatore</option>
	<option value="madurai">Madurai</option>
	<option value="sivakasi">Sivakasi</option>
	</datalist>
	<div class="formBtn">
		<button>Update room</button>
	</div>
	</form>
</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="cancelRoom">
	<form action="CancelRoom" method="post">
	<label>Room Number :</label>
	<input type="text" id=roomNumber name="roomNumber" required class="form-control"><br>
	<div class="formBtn">
		<button>Cancel room</button>
	</div>
	</form>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="bookWeddingHall">
<div style="justify-content: space-between;width:100%">
	<form action="BookWeddingHall" method="post">
	<label>Check-In :</label>
	<input type="date" id="bookWeddingHallCheckIn" name="checkIn" required class="form-control">
	<label>Check-Out :</label>
	<input type="date" id="bookWeddingHallCheckOut" name="checkOut" required class="form-control">
	<label>Category :</label>
	<input type="text" id="cat" list="category" name="category" required class="form-control">
	<datalist id="category" name="category">
	<option value="premium">premium</option>
	<option value="luxury">luxury</option>
	<option value="standard">standard</option>
	<option value="budget">budget</option>
	</datalist>
	<label>Location :</label>
	<input type="text" id="loc" list="location" name="location" required class="form-control">
	<datalist id="location" name="location">
	<option value="chennai">Chennai</option>
	<option value="coimbatore">Coimbatore</option>
	<option value="madurai">Madurai</option>
	<option value="sivakasi">Sivakasi</option>
	</datalist>
	<div class="formBtn">
	<input type="submit" value="Book Wedding hall">
	</div>
	</form>
</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="updateWeddingHall">
<div style="justify-content: space-between;width:100%">
	<form  action="UpdateWeddingHall" method="post">
		<label>Wedding Hall Number :</label>
		<input type="text" id=weddingHallNumber name="weddingHallNumber" required class="form-control">
		<label>Check-In :</label>
		<input type="date" id="updateWeddingHallCheckIn" name="checkIn" required class="form-control">
		<label>Check-Out :</label>
		<input type="date" id="updateWeddingHallCheckOut" name="checkOut" required class="form-control">
		<label>Category :</label>
		<br><input type="text" id="cat" list="category" name="category" required class="form-control">
		<datalist id="category" name="category">
		<option value="premium">premium</option>
		<option value="luxury">luxury</option>
		<option value="standard">standard</option>
		<option value="budget">budget</option>
		</datalist>
		<label>Location :</label>
		<input type="text" id="loc" list="location" name="location" required class="form-control">
		<datalist id="location" name="location">
		<option value="chennai">Chennai</option>
		<option value="coimbatore">Coimbatore</option>
		<option value="madurai">Madurai</option>
		<option value="sivakasi">Sivakasi</option>
		</datalist>
		<div class="formBtn">
		<button>Update Wedding Hall</button>
		</div>
	</form>
</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="cancelWeddingHall">
<div style="justify-content: space-between;width:100%">
	<form action="CancelWeddingHall" method="post">
		<label>Wedding Hall Number :</label>
		<input type="text" id=weddingHallNumber name="weddingHallNumber" required class="form-control">
		<div class="formBtn">
		<button>cancel Wedding Hall</button>
		</div>
		</form>
</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="bookMeetingHall">
<div style="justify-content: space-between;width:100%">
		<form  action="BookMeetingHall"method="post">
		<label>Check-In :</label>
		<input type="date" id="bookMeetingHallCheckIn" name="checkIn" required class="form-control">
		<label>Check-Out :</label>
		<input type="date" id="bookMeetingHallCheckOut" name="checkOut" required class="form-control">
		<label>Category :</label>
		<br><input type="text" id="cat" list="category" name="category" required class="form-control">
		<datalist id="category" name="category">
		<option value="premium">premium</option>
		<option value="luxury">luxury</option>
		<option value="standard">standard</option>
		<option value="budget">budget</option>
		</datalist>
		<label>Location :</label>
		<input type="text" id="loc" list="location" name="location" required class="form-control">
		<datalist id="location" name="location">
		<option value="chennai">Chennai</option>
		<option value="coimbatore">Coimbatore</option>
		<option value="madurai">Madurai</option>
		<option value="sivakasi">Sivakasi</option>
		</datalist>
		<div class="formBtn">
		<input type="submit" value="Book Meeting hall">
		</div>
	</form>
</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="updateMeetingHall">
<div style="justify-content: space-between;width:100%">
		<form action="UpdateMeetingHall" method="post">
			<label>Meeting Hall Nummber :</label>
			<input type="text" id=meetingHallNumber name="meetingHallNumber" required class="form-control">
			<label>Check-In :</label>
			<br><input type="date" id="updateMeetingHallCheckIn" name="checkIn" required class="form-control">
			<label>Check-Out :</label>
			<input type="date" id="updateMeetingHallCheckOut" name="checkOut" required class="form-control">
			<label>Category :</label>
			<br><input type="text" id="cat" list="category" name="category" required class="form-control">
			<datalist id="category" name="category">
			<option value="premium">premium</option>
			<option value="luxury">luxury</option>
			<option value="standard">standard</option>
			<option value="budget">budget</option>
			</datalist>
			<label>Location :</label>
			<input type="text" id="loc" list="location" name="location" required class="form-control">
			<datalist id="location" name="location">
			<option value="chennai">Chennai</option>
			<option value="coimbatore">Coimbatore</option>
			<option value="madurai">Madurai</option>
			<option value="sivakasi">Sivakasi</option>
			</datalist>
			<div class="formBtn">
			<button>Update Meeting Hall</button>
			</div>
			</form>
		</div>
	</div>
	<div class="col-sm-8 align-items-center" style="display: none;" id="cancelMeetingHall">
<div style="justify-content: space-between;width:100%">
			<form action="CancelMeetingHall" method="post">
				<label>Meeting Hall Number :</label>
				<input type="text" id=meetingHallNumber name="meetingHallNumber" required class="form-control">
				<div class="formBtn">
			<button>cancel Meeting Hall</button>
			</div>
			</form>
		</div>
</div>
<div class="col-sm-8 align-items-center mt-3" style="display: none;" id="myBookings">
<div style="justify-content: space-between;width:100%;height: 80vh;overflow: auto;">
<table cellpadding=2px cellspacing=20px width="100%" class="myBooking">
<tr>
<th>Room Number</th>
<th>CheckIn Date</th>
<th>CheckOut Date</th>
<th>Category</th>
<th>Location</th>
</tr>

<%


RoomTransactionDaoImpl roomTransDaoImpl = new RoomTransactionDaoImpl();
List<RoomTransaction> roomList = roomTransDaoImpl.showRoomBooking(guestObj);

WeddingHallTransactionDaoImpl weddingHallDaoImpl = new WeddingHallTransactionDaoImpl();
List<WeddingHallTransaction> weddingHallList = weddingHallDaoImpl.showWeddingHallBooking(guestObj);

MeetingHallTransactionDaoImpl meetingHallDaoImpl = new MeetingHallTransactionDaoImpl();
List<MeetingHallTransaction> meetingHallList = meetingHallDaoImpl.showMeetingHallBooking(guestObj);


for(int i=0;i<roomList.size();i++)
{
	RoomTransaction roomTransObj = roomList.get(i);
%>
<tr>
<td><%=roomTransObj.getroomNumber() %></td>
<td><%=roomTransObj.getCheckIn() %></td>
<td><%=roomTransObj.getCheckOut() %></td>
<td><%=roomTransObj.getCategory() %></td>
<td><%=roomTransObj.getLocation() %></td>
</tr>

<%}

for(int i=0;i<weddingHallList.size();i++)
{
	WeddingHallTransaction weddingHallTransObj = weddingHallList.get(i);
%>
<tr>
<td><%=weddingHallTransObj.getroomNumber() %></td>
<td><%=weddingHallTransObj.getCheckIn() %></td>
<td><%=weddingHallTransObj.getCheckOut() %></td>
<td><%=weddingHallTransObj.getCategory() %></td>
<td><%=weddingHallTransObj.getLocation() %></td>
</tr>

<%}

for(int i=0;i<meetingHallList.size();i++)
{
	MeetingHallTransaction meetingHallTransObj = meetingHallList.get(i);
%>
<tr>
<td><%=meetingHallTransObj.getroomNumber() %></td>
<td><%=meetingHallTransObj.getCheckIn() %></td>
<td><%=meetingHallTransObj.getCheckOut() %></td>
<td><%=meetingHallTransObj.getCategory() %></td>
<td><%=meetingHallTransObj.getLocation() %></td>
</tr>

<%}%>
</table>
</div>
</div>
</div>
</div>
</body>
<script type="text/javascript">
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; 
var yyyy = today.getFullYear();
if(dd<10){
  dd='0'+dd
} 
if(mm<10){
  mm='0'+mm
} 

today = yyyy+'-'+mm+'-'+dd;
document.getElementById("bookRoomCheckIn").setAttribute("min", today);
document.getElementById("bookRoomCheckOut").setAttribute("min", today);
document.getElementById("updateRoomCheckOut").setAttribute("min", today);
document.getElementById("updateRoomCheckIn").setAttribute("min", today);

document.getElementById("bookWeddingHallCheckIn").setAttribute("min", today);
document.getElementById("bookWeddingHallCheckOut").setAttribute("min", today);
document.getElementById("updateWeddingHallCheckIn").setAttribute("min", today);
document.getElementById("updateWeddingHallCheckOut").setAttribute("min", today);

document.getElementById("bookMeetingHallCheckIn").setAttribute("min", today);
document.getElementById("bookMeetingHallCheckOut").setAttribute("min", today);
document.getElementById("updateMeetingHallCheckIn").setAttribute("min", today);
document.getElementById("updateMeetingHallCheckOut").setAttribute("min", today);
</script>
</html>