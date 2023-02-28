<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="index.jsp" %>
<h1>User Signup Page..</h1><br><br>
<form action="userSignup" method="post">
	
	Mail-ID: <input type="text" name="mailId" namespace="Enter Email-Id" /><br><br>
	Contact No: <input type="text" name="contactNo" namespace="Enter Mob. Number" /><br><br>
	Password: <input type="text" id="password" name="password" namespace="Enter Password" /><br><br>
	Confirm Password: <input type="text" id="cpassword" name="cpassword" namespace="Confirm password" /><br><br>
	
	<button type="submit" > Submit</button> <br><br>
</form>

<a href="signinForm">User Sign in</a>&nbsp;&nbsp;
</body>
</html>