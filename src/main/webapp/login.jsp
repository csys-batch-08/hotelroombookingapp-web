<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<title>Hotel Room Booking Application</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/login.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


</head>
<body>

	<c:if test="${not empty invalidLogin }">
		<script type="text/javascript">
alert("Invalid Login");
</script>
	</c:if>

	<div class="loginPage">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-sm-6">
					<div class="loginForm row align-items-center">
						<div class="col-sm-6">
							<img src="assets/login_img.jpg" alt="login" class="img-fluid">
						</div>
						<div class="col-sm-6">
							<form action="Login" method="post">
								<h3 class="text-center">Login</h3>
								<div class="col-sm-12 p-0">
									<label for="email">Email</label> <input type="text" id="email"
										name="email" class="form-control" placeholder="Enter Email"
										pattern="[a-zA-Z0-9]+[@][a-z]+[.][a-z]+" title="Invalid Email"
										required>
								</div>
								<div class="col-sm-12 p-0">
									<label for="password">Password</label> <input type="password"
										id="password" name="password" class="form-control"
										placeholder="Enter Password" required>
								</div>
								<div class="formBtn d-flex justify-space-between">
									<input type="submit" value="LOGIN" class="btn btn-success">
									<a href="register.jsp"><input type="button"
										class="btn btn-success" value="REGISTER"></a>
								</div>
								<div class="text-center forget">
									<a href="forgetPassword.jsp"><label>Forget Password</label></a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>