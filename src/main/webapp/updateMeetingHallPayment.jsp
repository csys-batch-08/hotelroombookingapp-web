<%@page
	import="com.hotelroombooking.daoimpl.MeetingHallTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.model.MeetingHallTransaction"%>
<%@page
	import="com.hotelroombooking.daoimpl.WeddingHallTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.model.WeddingHallTransaction"%>
<%@page import="com.hotelroombooking.daoimpl.RoomTransactionDaoImpl"%>
<%@page import="com.hotelroombooking.model.Guest"%>
<%@page import="com.hotelroombooking.model.RoomTransaction"%>
<%@page import="com.hotelroombooking.model.MeetingHallDetails"%>
<%@page import="com.hotelroombooking.model.WeddingHallDetails"%>
<%@page import="com.hotelroombooking.model.RoomDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/updateMeetingHallPayment.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>


	<div class="registerForm row">

		<div class="col-sm-6 row verticalRule">

			<div class="col-sm-12">
				<label>Room Number :</label>
				<c:out value="${updateMeetingHallDetails.roomNumber}"></c:out>
			</div>
			<div class="col-sm-6">
				<label>Check-In Date :</label>
				
				<fmt:parseDate value="${updateMeetingHallDetails.checkIn}" pattern="yyyy-MM-dd" type="date" var="meetingCheckIn" />
				<fmt:formatDate value="${meetingCheckIn}" pattern="dd-MM-yyyy" var="meetingCheckInDate" />
				<c:out value="${meetingCheckInDate}"></c:out>
			</div>
			<div class="col-sm-6">
				<label>Check-Out Date :</label>
				<c:out value="${updateMeetingHallDetails.checkOut}"></c:out>
			</div>
			<div class="col-sm-6">
				<label>Category :</label>
				<c:out value="${updateMeetingHallDetails.category}"></c:out>
			</div>
			<div class="col-sm-6">
				<label>Location :</label>
				<c:out value="${updateMeetingHallDetails.location}"></c:out>
			</div>
			<div class="col-sm-12">
				<label>Price :</label>
				<c:out value="${updateMeetingPrice }"></c:out>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="formcontent">
				<h3 class="text-center">PAYMENT PORTAL</h3>
				<form action="payment" method="post">
					<label for="cardNumber">Card Number:</label> <input type="text" id="cardNumber"
						name="cardNumber" placeholder="Card Number" pattern="[0-9]{16}"
						title="invalid card number" required> <label for="expiryDate">Expiry
						Date:</label> <input type="text" id="expiryDate" name="expiryDate"
						placeholder="mm/yy" pattern="[0-9]{2}[/][0-9]{2}"
						title="invalid expiry date" required> <label for="cvv">CVV:</label>
					<input type="text" id="cvv" name="cvv" placeholder="CVV"
						pattern="[0-9]{3}" title="invalid cvv" required> <input
						type="checkbox" id=cvvCheck name="cvvCheck"> Save CVV for
					later use<br>
					<button class="btn btn-primary">Pay Now</button>
				</form>
			</div>
		</div>
	</div>






</body>
</html>