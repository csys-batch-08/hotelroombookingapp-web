<%@page import="com.hotelroombooking.model.Guest"%>
<%@page import="java.util.List"%>
<%@page import="com.hotelroombooking.daoimpl.GuestDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Title</title>
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
    line-heditMeetingHall: 1.5;
    font-size: 15px;
    font-weditMeetingHall: bold;
    margin-bottom: 6px;
    color: white;
}
.sideMenu {
    heditMeetingHall: 100%;
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
    font-weditMeetingHall: bold;
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
    font-weditMeetingHall: bold;
    width:100%
}

</style>
</head>
<body>
<div class="header container-fluid">
<h3>Welcome Admin</h3>
</div>

<script type="text/javascript">
function addRoom()
{
	var room=document.getElementById("addRoom");
	room.style.display="flex";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}
function editRoom()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="flex";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function deleteRoom()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="flex";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function addWeddingHall()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="flex";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function editWeddingHall()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="flex";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function cancelWeddingHall()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="flex";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function addMeetingHall()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="flex";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function editMeetingHall()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="flex";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function cancelMeetingHall()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="flex";
	var room=document.getElementById("listAll");
	room.style.display="none";
}function listAll()
{
	var room=document.getElementById("addRoom");
	room.style.display="none";
	var room=document.getElementById("editRoom");
	room.style.display="none";
	var room=document.getElementById("deleteRoom");
	room.style.display="none";
	var room=document.getElementById("addWeddingHall");
	room.style.display="none";
	var room=document.getElementById("editWeddingHall");
	room.style.display="none";
	var room=document.getElementById("cancelWeddingHall");
	room.style.display="none";
	var room=document.getElementById("addMeetingHall");
	room.style.display="none";
	var room=document.getElementById("editMeetingHall");
	room.style.display="none";
	var room=document.getElementById("cancelMeetingHall");
	room.style.display="none";
	var room=document.getElementById("listAll");
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
						<li onclick="addRoom()">Add Rooms</li>
						<li onclick="editRoom()">Edit Rooms</li>
						<li onclick="deleteRoom()">Delete Room</li>
					</ul>
				</li>
				<li><a onclick="weddingShow()">WEDDING HALLS</a>
					<ul class="dropdown">
						<li onclick="addWeddingHall()">Add Wedding Hall</li>
						<li onclick="editWeddingHall()">Edit Wedding Hall</li>
						<li onclick="cancelWeddingHall()">Delete Wedding Hall</li>
					</ul>
				</li>
				<li><a onclick="meetingShow()">MEETING HALLS</a>
					<ul class="dropdown">
						<li onclick="addMeetingHall()">Add Meeting Hall</li>
						<li onclick="editMeetingHall()">Edit Meeting Hall</li>
						<li onclick="cancelMeetingHall()">Delete Meeting Hall</li>
					</ul>
				</li>
				<li><a onclick="listAll()">LIST ALL USERS</a></li>
			</ul>
			<div class="logoutbtn">
				<a href="Logout"><h3>LOGOUT</h3></a>
			</div>
		</div>
		
	</div>
</div>
<div class="col-sm-8 align-items-center" style="display: none;" id="addRoom">
<div style="justify-content: space-between;width:100%">
<form action="AddRoom" method="post">
<label>room number:</label>
<input type="text" id="roomNumber"name="roomNumber" class="form-control" required>
<label>Category :</label>
<input type="text" id="cat" list="category" name="category" class="form-control" required>
<datalist id="category" name="category">
<option value="premium">premium</option>
<option value="luxury">luxury</option>
<option value="standard">standard</option>
<option value="budget">budget</option>
</datalist>
<label>Location :</label>
<input type="text" id="loc" list="location" name="location" class="form-control" required>
<datalist id="location" name="location">
<option value="chennai">Chennai</option>
<option value="coimbatore">Coimbatore</option>
<option value="madurai">Madurai</option>
<option value="sivakasi">Sivakasi</option>
</datalist>
<label>price:</label>
<input type="text" id="price"name="price" class="form-control" required>
<div class="formBtn">
<button>Add room</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="editRoom">
<div style="justify-content: space-between;width:100%">
<form action="EditRoom" method="post">
<label>room number:</label>
<input type="text" id="roomNumber"name="roomNumber" class="form-control" required>
<label>Category :</label>
<input type="text" id="cat" list="category" name="category" class="form-control" required>
<datalist id="category" name="category">
<option value="premium">premium</option>
<option value="luxury">luxury</option>
<option value="standard">standard</option>
<option value="budget">budget</option>
</datalist>
<label>Location :</label>
<input type="text" id="loc" list="location" name="location" class="form-control" required>
<datalist id="location" name="location">
<option value="chennai">Chennai</option>
<option value="coimbatore">Coimbatore</option>
<option value="madurai">Madurai</option>
<option value="sivakasi">Sivakasi</option>
</datalist>
<label>price:</label>
<input type="text" id="price"name="price" class="form-control" required>
<div class="formBtn">
<button>update room</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="deleteRoom">
<div style="justify-content: space-between;width:100%">
<form action="DeleteRoom" method="post">
room number:<input type="text" id="roomNumber"name="roomNumber" class="form-control" required>
<div class="formBtn">
<button>Delete room</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="addWeddingHall">
<div style="justify-content: space-between;width:100%">
<form action="AddWeddingHall" method="post">
<label>wedding hall number:</label>
<input type="text" id=weddingHallNumber name="weddingHallNumber" class="form-control" required>
<label>Category :</label>
<input type="text" id="cat" list="category" name="category" class="form-control" required>
<datalist id="category" name="category">
<option value="premium">premium</option>
<option value="luxury">luxury</option>
<option value="standard">standard</option>
<option value="budget">budget</option>
</datalist>
<label>Location :</label>
<input type="text" id="loc" list="location" name="location" class="form-control" required>
<datalist id="location" name="location">
<option value="chennai">Chennai</option>
<option value="coimbatore">Coimbatore</option>
<option value="madurai">Madurai</option>
<option value="sivakasi">Sivakasi</option>
</datalist>
<label>price:</label>
<input type="text" id="price"name="price" required>
<div class="formBtn">
<button>Add wedding hall</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="editWeddingHall">
<div style="justify-content: space-between;width:100%">
<form action="EditWeddingHall" method="post">
<label>wedding hall number:</label>
<input type="text" id=weddingHallNumber name="weddingHallNumber" class="form-control" required>
<label>Category :</label>
<input type="text" id="cat" list="category" name="category" class="form-control" required>
<datalist id="category" name="category">
<option value="premium">premium</option>
<option value="luxury">luxury</option>
<option value="standard">standard</option>
<option value="budget">budget</option>
</datalist>
<label>Location :</label>
<input type="text" id="loc" list="location" name="location" class="form-control" required>
<datalist id="location" name="location">
<option value="chennai">Chennai</option>
<option value="coimbatore">Coimbatore</option>
<option value="madurai">Madurai</option>
<option value="sivakasi">Sivakasi</option>
</datalist>
<label>price:</label>
<input type="text" id="price"name="price" class="form-control" required>
<div class="formBtn">
<button>update wedding hall</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="cancelWeddingHall">
<div style="justify-content: space-between;width:100%">
<form action="DeleteWeddingHall" method="post">
<label>wedding hall number:</label>
<input type="text" id=weddingHallNumber name="weddingHallNumber" class="form-control" required>
<div class="formBtn">
<button>delete wedding hall</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="addMeetingHall">
<div style="justify-content: space-between;width:100%">
<form action="AddMeetingHall" method="post">
<label>meeting hall number:</label>
<input type="text" id=meetingHallNumber name="meetingHallNumber" class="form-control" required>
<label>Category :</label>
<input type="text" id="cat" list="category" name="category" class="form-control" required>
<datalist id="category" name="category">
<option value="premium">premium</option>
<option value="luxury">luxury</option>
<option value="standard">standard</option>
<option value="budget">budget</option>
</datalist>
<label>Location :</label>
<input type="text" id="loc" list="location" name="location" class="form-control" required>
<datalist id="location" name="location">
<option value="chennai">Chennai</option>
<option value="coimbatore">Coimbatore</option>
<option value="madurai">Madurai</option>
<option value="sivakasi">Sivakasi</option>
</datalist>
<label>price:</label>
<input type="text" id="price"name="price" class="form-control" required>
<div class="formBtn">
<button>Add meeting hall</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="editMeetingHall">
<div style="justify-content: space-between;width:100%">
<form action="EditMeetingHall" method="post">
<label>meeting hall number:</label>
<input type="text" id=meetingHallNumber name="meetingHallNumber" class="form-control" required>
<label>Category :</label>
<input type="text" id="cat" list="category" name="category" class="form-control" required>
<datalist id="category" name="category">
<option value="premium">premium</option>
<option value="luxury">luxury</option>
<option value="standard">standard</option>
<option value="budget">budget</option>
</datalist>
<label>Location :</label>
<input type="text" id="loc" list="location" name="location" class="form-control" required>
<datalist id="location" name="location">
<option value="chennai">Chennai</option>
<option value="coimbatore">Coimbatore</option>
<option value="madurai">Madurai</option>
<option value="sivakasi">Sivakasi</option>
</datalist>
<label>price:</label>
<input type="text" id="price"name="price" class="form-control" required>
<div class="formBtn">
<button>update meeting hall</button>
</div>
</form>
</div>
</div>

<div class="col-sm-8 align-items-center" style="display: none;" id="cancelMeetingHall">
<div style="justify-content: space-between;width:100%">
<form action="DeleteMeetingHall" method="post">
<label>meeting hall number:</label>
<input type="text" id=meetingHallNumber name="meetingHallNumber" class="form-control" required>
<div class="formBtn">
<button>delete meeting hall</button>
</div>
</form>
</div>
</div>




<div class="col-sm-8 align-items-center mt-3" style="display: none;" id="listAll">
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

<%
GuestDaoImpl guestDaoObj = new GuestDaoImpl();
List<Guest> guestList = guestDaoObj.showAllUser();

for(int i=0;i<guestList.size();i++)
{
	Guest guestObj = guestList.get(i);
%>
<tr>
<td><%=guestObj.getFirstName()%></td>
<td><%=guestObj.getLastName()%></td>
<td><%=guestObj.getEmail()%></td>
<td><%=guestObj.getPassword()%></td>
<td><%=guestObj.getMobileNumber()%></td>
</tr>
<%}%>
</table>
</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>