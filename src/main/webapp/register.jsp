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
		<link rel="stylesheet" href="assets/css/regsiter.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

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