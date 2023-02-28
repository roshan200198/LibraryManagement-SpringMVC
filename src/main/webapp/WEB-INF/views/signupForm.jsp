<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<!-- <script>

function verifyPassword() {  
	  var pw = document.getElementById("password").value;  
	  var cpw = document.getElementById("cpassword").value;
	  //check empty password field  
	  if(pw == cpw) {  
	      return true;
	  else
		  alert("Password doesn't match!"); 
	  return false;
	  }  

</script> -->
<body>
	<%@ include file="index.jsp"%>
	<h1>Signup Page..</h1>
	<br>
	<br>
	<form action="signup" method="post">
		Full Name: <input type="text" name="name" namespace="Enter full name" /><br><br> 
		
		Mail-ID: <input type="text" name="username" namespace="Enter Email-Id" /><br><br>
			 
		Contact No: <input type="text" name="mobNumber" namespace="Enter Mob. Number" /><br><br> 
			
		Password: <input type="text" id="password" name="password" namespace="Enter Password" /><br><br> 
		
		Confirm Password: <input type="text" id="cpassword"
			name="cpassword" namespace="Confirm password" /><br><br>
			 
		<label> Choose your Role: </label> <br> 
		<input type="radio" id="role" name="role" value="ADMIN" /> ADMIN <br> 
		<input type="radio" id="role" name="role" value="USER" /> USER <br />

		<button type="submit">Submit</button><br><br>
		
	</form>

	<a href="admin/signin">Sign in</a>&nbsp;&nbsp;
</body>
</html>