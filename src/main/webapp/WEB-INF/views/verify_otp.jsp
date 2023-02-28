<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
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
		
		#check_otpbtn {
			background-color: #333;
			color: #fff;
			padding: 7px 10px;
			border: none;
			border-radius: 10px;
			font-size: 16px;
			cursor: pointer;
		}
		
		#check_otpbtn:hover {
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<%@ include file="index.jsp" %>
	<h2> OTP is sent to your Mobile Number ${mobNumber} </h2>
	<form id="otp_form" action="setpassword" method="post" id="myForm" >
		${status}
		
		<p style="color: red; font-size: 18px;" id="status"></p>
			Enter OTP ${mail_otp} :<br><br> <input type="text" id="txt_otp"
				name="txt_otp" required /><br><br>
				<%-- Mail ID :<br> <input type="text" id="mailId"
				name="mailId" value="${mailId}" required /> --%>
			
			<button id="check_otpbtn" type="submit">Confirm</button>
	</form>
</div>

<script type="text/javascript">


	
</script>
</body>
</html>