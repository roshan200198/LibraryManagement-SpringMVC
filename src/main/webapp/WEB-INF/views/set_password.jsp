<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	form {
			max-width: 615px;
			margin: 40px auto;
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

		#password {
			display: block;
			width: 40%;
			padding: 5px 0px;
			border: 2px solid #ccc;
			border-radius: 6px;
			font-size: 16px;
			
		}
		
		#cpassword {
			display: block;
			width: 40%;
			padding: 5px 0px;
			border: 2px solid #ccc;
			border-radius: 6px;
			font-size: 16px;
			
		}
		
		#btn_setpassword {
			background-color: #333;
			color: #fff;
			padding: 7px 10px;
			border: none;
			border-radius: 10px;
			font-size: 16px;
			cursor: pointer;
		}
		
		#btn_setpassword:hover {
			background-color: #555;
		}
		
		body {
			font-family: Arial, sans-serif;
			background-color: #f5f5f5;
		}
		
		h2{
			font-size: 28px;
			margin-top: 40px;
			margin-bottom: 20px;
			text-align: center;
			color: #333;
		}


</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<%@ include file="index.jsp" %>
<%
	String mailId = (String) session.getAttribute("mailId");
	
%>
	
	<form action="new_password" method="post">
	<c:if test="${not empty status}">
   		<h3 style="color: red; font-size: 18px;">${status}</h3>
	</c:if>
	
		<h2 name="mailId">Set new password for MailID:<h3 align="center" id="mailId"> <%=mailId %> </h3></h2>
		<p style="color: red; font-size: 18px;" id="pswd_status" align="center"></p>
		Enter password: <input type="password" name="password" id="password" /><br><br>
		Confirm Password: <input type="password" name="cpassword" id="cpassword"/><br>
		<button type="submit" id="btn_setpassword" onClick="checkPassword()" >Submit</button>
	
	</form>

</div>


<script type="text/javascript">

function checkPassword(){
	
		var pswd = document.getElementById("password").value;
		var cpswd = document.getElementById("cpassword").value;
		
		
		if(pswd != cpswd) {
			event.preventDefault();
			  document.getElementById("pswd_status").innerHTML = "Password not match. Reset password..";
			  
		} 
		
}

</script>
</body>
</html>