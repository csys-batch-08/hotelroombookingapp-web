<%@page
	import="com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl"%>
<%@page
	import="com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.model.WeddingHallTransaction"%>
<%@page import="com.hotelroombooking.model.MeetingHallTransaction"%>
<%@page import="com.hotelroombooking.model.Guest"%>
<%@page import="com.hotelroombooking.daoimpl.RoomTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.model.RoomTransaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert Title</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/guestDashboard.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="assets/js/javascripts.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


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

		<h3>
			Welcome
			<c:out value="${currentUser.firstName }"></c:out>
		</h3>


	</div>






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
								</ul></li>
							<li><a onclick="weddingShow()">WEDDING HALLS</a>
								<ul class="dropdown">
									<li onclick="bookWeddingHall()">Book Wedding Hall</li>
									<li onclick="updateWeddingHall()">Update Wedding Hall</li>
									<li onclick="cancelWeddingHall()">Cancel Wedding Hall</li>
								</ul></li>
							<li><a onclick="meetingShow()">MEETING HALL BOOKING</a>
								<ul class="dropdown">
									<li onclick="bookMeetingHall()">Book Meeting Hall</li>
									<li onclick="updateMeetingHall()">Update Meeting Hall</li>
									<li onclick="cancelMeetingHall()">Cancel Meeting Hall</li>
								</ul></li>
							<li><a href="Bookings">MY BOOKINGS</a></li>
						</ul>
						<div class="logoutbtn">
							<a href="Logout"><h3>LOGOUT</h3></a>
						</div>
					</div>

				</div>
			</div>
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="bookRoom">
				<div style="justify-content: space-between; width: 100%">
					<form action="BookRoom" method="post">
						<label for="bookRoomCheckIn">Check-In :</label> <input type="date"
							id="bookRoomCheckIn" name="checkIn" required class="form-control" />
						<label>Check-Out:</label> <input type="date" id="bookRoomCheckOut"
							name="checkOut" required class="form-control"> <label>Category
							:</label> <input type="text" id="cat" list="category" name="category"
							required class="form-control">
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" required class="form-control"><br>
						<datalist id="location" name="location">
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
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="updateRoom">
				<div style="justify-content: space-between; width: 100%">
					<form action="UpdateRoom" method="post">
						<label>Room Number :</label> <input type="text" id=roomNumber
							name="roomNumber" required class="form-control"> <label>Check-In
							:</label> <input type="date" id="updateRoomCheckIn" name="checkIn"
							required class="form-control"> <label>Check-Out :</label>
						<br> <input type="date" id="updateRoomCheckOut"
							name="checkOut" required class="form-control"> <label>Category
							:</label> <br> <input type="text" id="cat" list="category"
							name="category" required class="form-control">
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <br> <input type="text" id="loc"
							list="location" name="location" required class="form-control">
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
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="cancelRoom">
				<form action="CancelRoom" method="post">
					<label>Room Number :</label> <input type="text" id=roomNumber
						name="roomNumber" required class="form-control"><br>
					<div class="formBtn">
						<button>Cancel room</button>
					</div>
				</form>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="bookWeddingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="BookWeddingHall" method="post">
						<label>Check-In :</label> <input type="date"
							id="bookWeddingHallCheckIn" name="checkIn" required
							class="form-control"> <label>Check-Out :</label> <input
							type="date" id="bookWeddingHallCheckOut" name="checkOut" required
							class="form-control"> <label>Category :</label> <input
							type="text" id="cat" list="category" name="category" required
							class="form-control">
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" required class="form-control">
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
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="updateWeddingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="UpdateWeddingHall" method="post">
						<label>Wedding Hall Number :</label> <input type="text"
							id=weddingHallNumber name="weddingHallNumber" required
							class="form-control"> <label>Check-In :</label> <input
							type="date" id="updateWeddingHallCheckIn" name="checkIn" required
							class="form-control"> <label>Check-Out :</label> <input
							type="date" id="updateWeddingHallCheckOut" name="checkOut"
							required class="form-control"> <label>Category :</label>
						<br> <input type="text" id="cat" list="category"
							name="category" required class="form-control">
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" required class="form-control">
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
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="cancelWeddingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="CancelWeddingHall" method="post">
						<label>Wedding Hall Number :</label> <input type="text"
							id=weddingHallNumber name="weddingHallNumber" required
							class="form-control">
						<div class="formBtn">
							<button>cancel Wedding Hall</button>
						</div>
					</form>
				</div>
			</div>
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="bookMeetingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="BookMeetingHall" method="post">
						<label>Check-In :</label> <input type="date"
							id="bookMeetingHallCheckIn" name="checkIn" required
							class="form-control"> <label>Check-Out :</label> <input
							type="date" id="bookMeetingHallCheckOut" name="checkOut" required
							class="form-control"> <label>Category :</label> <br>
						<input type="text" id="cat" list="category" name="category"
							required class="form-control">
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" required class="form-control">
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
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="updateMeetingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="UpdateMeetingHall" method="post">
						<label>Meeting Hall Nummber :</label> <input type="text"
							id=meetingHallNumber name="meetingHallNumber" required
							class="form-control"> <label>Check-In :</label> <br>
						<input type="date" id="updateMeetingHallCheckIn" name="checkIn"
							required class="form-control"> <label>Check-Out :</label>
						<input type="date" id="updateMeetingHallCheckOut" name="checkOut"
							required class="form-control"> <label>Category :</label>
						<br> <input type="text" id="cat" list="category"
							name="category" required class="form-control">
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" required class="form-control">
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
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="cancelMeetingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="CancelMeetingHall" method="post">
						<label>Meeting Hall Number :</label> <input type="text"
							id=meetingHallNumber name="meetingHallNumber" required
							class="form-control">
						<div class="formBtn">
							<button>cancel Meeting Hall</button>
						</div>
					</form>
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