<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style>
.loginForm {
	box-shadow: 0px 0px 14px 0px #747474;
	padding: 20px 10px;
	background: white;
	border-radius: 10px;
}

.formBtn {
	text-align: center;
	justify-content: space-between;
}

.formBtn input {
	margin: 0px 0px;
	font-size: 13px;
	padding: 5px 15px;
	background: #34c2df;
	color: black;
	border: none;
	font-weight: bold;
}

.loginForm input {
	margin-bottom: 11px;
	border: 1px solid #cbcbcb;
	width: 64%;
	padding: 5px 10px;
	border-radius: 5px;
}

.form-group {
	margin-bottom: 10px;
}

.formBtn input {
	margin: 0px 0px;
	font-size: 13px;
	padding: 5px 44px !important;
	background: #34c2df;
	color: black;
	width: auto !important;
	border: none;
	font-weight: bold;
}

.loginPage {
	height: 100vh;
	display: flex;
	align-items: center;
	background-image: url(./assets/login_background.jpeg);
	opacity: .8;
}

.loginForm label {
	color: black;
	font-weight: bold;
	width: 35%;
}

.forget a label {
	font-size: 13px;
	color: red;
}
</style>
</head>
<body>
<c:if test="${not empty invalidRegister }">
		<script type="text/javascript">
alert("User already exists");
</script>
	</c:if>
	<div class="loginPage">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-sm-6">
					<div class="loginForm row align-items-center">
						<div class="col-sm-12">
							<div class="registerForm">
								<form action="register" method="post">
									<div class="form-group">
										<label>Firstname :</label> <input type="text" id="firstName"
											name="firstName" class="register-firstname"
											pattern="[A-Z][A-Za-z]+" title="first letter must be capital"
											placeholder="Firstname" required>
									</div>
									<div class="form-group">
										<label>Lastname :</label> <input type="text" id="lastName"
											name="lastName" class="register-lastname"
											placeholder="Lastname" pattern="[A-Z][A-Za-z]+"
											title="Invalid Name" required><br>
									</div>
									<div class="form-group">
										<label>Email :</label> <input type="text" id="email"
											name="email" class="register-email" placeholder="Email"
											pattern="[a-zA-Z0-9]+[@][a-z]+[.][a-z]+"
											title="Invalid Email" required><br>
									</div>
									<div class="form-group">
										<label>Password :</label> <input type="password" id="password"
											name="password" class="register-password"
											placeholder="Password" pattern="[A-Z]+[a-z]+[0-9]+"
											title="password must contain
						atleast one UPPER CASE, LOWER CASE, NUMBER"
											required>
									</div>
									<div class="form-group">
										<label>Confirm Password :</label> <input type="password"
											id="confirmPassword" name="confirmPassword"
											class="register-confirmpassword"
											placeholder="Confirm Password" pattern="[A-Z]+[a-z]+[0-9]+"
											title="password must contain
						atleast one UPPER CASE, LOWER CASE, NUMBER"><br>
									</div>
									<div class="form-group">
										<label>Phone Number :</label> <input type="text"
											id="phoneNumber" name="phoneNumber"
											class="register-phonenumber" onkeydown="passwordValidation()"
											placeholder="Phone Number" pattern="[0-9]{10}"
											title="Invalid phone number" required><br>
									</div>
									<div class="formBtn">
										<input type="submit" value="REGISTER"
											class="register-register">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
function passwordValidation()
{
	var password=document.getElementById("password").value;
	var cPassword=document.getElementById("confirmPassword").value;
	if(password==cPassword)
	{
		return true;
	}
	else
	{
		alert("Password Mismatch");
	}
}
</script>
</html>