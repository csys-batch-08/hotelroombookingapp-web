<%@page import="com.hotelroombooking.model.Guest"%>
<%@page import="java.util.List"%>
<%@page import="com.hotelroombooking.daoimpl.GuestDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert Title</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/adminDashboard.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="assets/js/adminDashboard.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="header container-fluid">
		<h3>Welcome Admin</h3>
	</div>


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
								</ul></li>
							<li><a onclick="weddingShow()">WEDDING HALLS</a>
								<ul class="dropdown">
									<li onclick="addWeddingHall()">Add Wedding Hall</li>
									<li onclick="editWeddingHall()">Edit Wedding Hall</li>
									<li onclick="cancelWeddingHall()">Delete Wedding Hall</li>
								</ul></li>
							<li><a onclick="meetingShow()">MEETING HALLS</a>
								<ul class="dropdown">
									<li onclick="addMeetingHall()">Add Meeting Hall</li>
									<li onclick="editMeetingHall()">Edit Meeting Hall</li>
									<li onclick="cancelMeetingHall()">Delete Meeting Hall</li>
								</ul></li>
							<li><a href="Users">LIST ALL USERS</a></li>
						</ul>
						<div class="logoutbtn">
							<a href="Logout"><h3>LOGOUT</h3></a>
						</div>
					</div>

				</div>
			</div>
			<div class="col-sm-8 align-items-center" style="display: none;"
				id="addRoom">
				<div style="justify-content: space-between; width: 100%">
					<form action="AddRoom" method="post">
						<label>room number:</label> <input type="text" id="roomNumber"
							name="roomNumber" class="form-control" required> <label>Category
							:</label> <input type="text" id="cat" list="category" name="category"
							class="form-control" required>
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" class="form-control" required>
						<datalist id="location" name="location">
							<option value="chennai">Chennai</option>
							<option value="coimbatore">Coimbatore</option>
							<option value="madurai">Madurai</option>
							<option value="sivakasi">Sivakasi</option>
						</datalist>
						<label>price:</label> <input type="text" id="price" name="price"
							class="form-control" required>
						<div class="formBtn">
							<button>Add room</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="editRoom">
				<div style="justify-content: space-between; width: 100%">
					<form action="EditRoom" method="post">
						<label>room number:</label> <input type="text" id="roomNumber"
							name="roomNumber" class="form-control" required> <label>Category
							:</label> <input type="text" id="cat" list="category" name="category"
							class="form-control" required>
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" class="form-control" required>
						<datalist id="location" name="location">
							<option value="chennai">Chennai</option>
							<option value="coimbatore">Coimbatore</option>
							<option value="madurai">Madurai</option>
							<option value="sivakasi">Sivakasi</option>
						</datalist>
						<label>price:</label> <input type="text" id="price" name="price"
							class="form-control" required>
						<div class="formBtn">
							<button>update room</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="deleteRoom">
				<div style="justify-content: space-between; width: 100%">
					<form action="DeleteRoom" method="post">
						<label for="roomNumber">room number:</label><input type="text"
							id="roomNumber" name="roomNumber" class="form-control" required>
						<div class="formBtn">
							<button>Delete room</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="addWeddingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="AddWeddingHall" method="post">
						<label>wedding hall number:</label> <input type="text"
							id=weddingHallNumber name="weddingHallNumber"
							class="form-control" required> <label>Category :</label>
						<input type="text" id="cat" list="category" name="category"
							class="form-control" required>
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" class="form-control" required>
						<datalist id="location" name="location">
							<option value="chennai">Chennai</option>
							<option value="coimbatore">Coimbatore</option>
							<option value="madurai">Madurai</option>
							<option value="sivakasi">Sivakasi</option>
						</datalist>
						<label>price:</label> <input type="text" id="price" name="price"
							class="form-control" required>
						<div class="formBtn">
							<button>Add wedding hall</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="editWeddingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="EditWeddingHall" method="post">
						<label>wedding hall number:</label> <input type="text"
							id=weddingHallNumber name="weddingHallNumber"
							class="form-control" required> <label>Category :</label>
						<input type="text" id="cat" list="category" name="category"
							class="form-control" required>
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" class="form-control" required>
						<datalist id="location" name="location">
							<option value="chennai">Chennai</option>
							<option value="coimbatore">Coimbatore</option>
							<option value="madurai">Madurai</option>
							<option value="sivakasi">Sivakasi</option>
						</datalist>
						<label>price:</label> <input type="text" id="price" name="price"
							class="form-control" required>
						<div class="formBtn">
							<button>update wedding hall</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="cancelWeddingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="DeleteWeddingHall" method="post">
						<label for="weddingHallNumber">wedding hall number:</label> <input
							type="text" id=weddingHallNumber name="weddingHallNumber"
							class="form-control" required>
						<div class="formBtn">
							<button>delete wedding hall</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="addMeetingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="AddMeetingHall" method="post">
						<label>meeting hall number:</label> <input type="text"
							id=meetingHallNumber name="meetingHallNumber"
							class="form-control" required> <label>Category :</label>
						<input type="text" id="cat" list="category" name="category"
							class="form-control" required>
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label>Location :</label> <input type="text" id="loc"
							list="location" name="location" class="form-control" required>
						<datalist id="location" name="location">
							<option value="chennai">Chennai</option>
							<option value="coimbatore">Coimbatore</option>
							<option value="madurai">Madurai</option>
							<option value="sivakasi">Sivakasi</option>
						</datalist>
						<label>price:</label> <input type="text" id="price" name="price"
							class="form-control" required>
						<div class="formBtn">
							<button>Add meeting hall</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="editMeetingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="EditMeetingHall" method="post">
						<label>meeting hall number:</label> <input type="text"
							id=meetingHallNumber name="meetingHallNumber"
							class="form-control" required> <label for="category">Category
							:</label> <input type="text" id="cat" list="category" name="category"
							class="form-control" required>
						<datalist id="category" name="category">
							<option value="premium">premium</option>
							<option value="luxury">luxury</option>
							<option value="standard">standard</option>
							<option value="budget">budget</option>
						</datalist>
						<label for="location">Location :</label> <input type="text"
							id="loc" list="location" name="location" class="form-control"
							required>
						<datalist id="location" name="location">
							<option value="chennai">Chennai</option>
							<option value="coimbatore">Coimbatore</option>
							<option value="madurai">Madurai</option>
							<option value="sivakasi">Sivakasi</option>
						</datalist>
						<label for="price">price:</label> <input type="text" id="price"
							name="price" class="form-control" required>
						<div class="formBtn">
							<button>update meeting hall</button>
						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-8 align-items-center" style="display: none;"
				id="cancelMeetingHall">
				<div style="justify-content: space-between; width: 100%">
					<form action="DeleteMeetingHall" method="post">
						<label for="">meeting hall number:</label> <input type="text"
							id=meetingHallNumber name="meetingHallNumber"
							class="form-control" required>
						<div class="formBtn">
							<button>delete meeting hall</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>