<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/forgetPassword.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="loginPage">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-sm-6">
					<div class="loginForm row align-items-center">
						<div class="col-sm-6">
							<img src="assets/forget.png" alt="forget" class="img-fluid">
						</div>
						<div class="col-sm-6">
							<form action="Forgetpassword" method="post">
								<div class="col-sm-12 p-0">
									<label for="email">Email</label> <input type="text" id="email"
										name="email" class="form-control" placeholder="Enter Email"
										pattern="[a-zA-Z0-9]+[@][a-z]+[.][a-z]+" title="Invalid Email"
										required>
								</div>
								<div class="col-sm-12 p-0">
									<label for="password">Password</label> <input type="password"
										id="password" name="password" class="form-control"
										placeholder="Enter Password" pattern="[A-Z]+[a-z]+[0-9]+"
										title="password must contain
				atleast one UPPER CASE, LOWER CASE, NUMBER"
										required>
								</div>
								<div class="col-sm-12 p-0">
									<label for="password">Confirm Password</label> <input
										type="password" id="confirmPassword" name="confirmPassword"
										class="form-control" placeholder="Confirm Password"
										pattern="[A-Z]+[a-z]+[0-9]+"
										title="password must contain
				atleast one UPPER CASE, LOWER CASE, NUMBER"
										required>
								</div>
								<div class="formBtn mt-4">
									<input type="submit" value="CHANGE PASSWORD"
										onmousedown="return passwordValidation()"
										class="btn btn-success">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
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