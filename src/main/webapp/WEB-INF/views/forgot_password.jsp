<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Forgot Password</title>
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f5f5f5;
		}

		h1 {
			font-size: 28px;
			margin-top: 40px;
			margin-bottom: 20px;
			text-align: center;
			color: #333;
		}

		form {
			max-width: 500px;
			margin: 0 auto;
			background-color: #fff;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0 2px 10px rgba(0,0,0,0.3);
		}

		label {
			display: block;
			margin-bottom: 10px;
			color: #333;
		}

		input[type="text"] {
			display: block;
			width: 90%;
			padding: 10px;
			border: 2px solid #ccc;
			border-radius: 6px;
			font-size: 16px;
			
		}

		button[type="submit"] {
			background-color: #333;
			color: #fff;
			padding: 7px 10px;
			border: none;
			border-radius: 10px;
			font-size: 16px;
			cursor: pointer;
		}

		button[type="submit"]:hover {
			background-color: #555;
		}
	</style>
</head>
<body>
<% String status = (String) request.getAttribute("status");
	
%>
<%@ include file="index.jsp" %>
	<h1>Forgot Password</h1>
	
	<form action="generate_otp" method="post">
		<%if(status != null){
			%>
			<p style="color: red; font-size: 18px;" align="center"><%=status %></p>
		<% }%>
			
		
	<p style="color: green; font-size: 18px;"></p>
		<label for="email">Email Address:</label>
		<input type="text" id="mailId" name="mailId" placeholder="Please enter your registered email-ID to reset your password." required>
		<button type="submit">Send OTP</button>
	</form>
</body>
</html>
