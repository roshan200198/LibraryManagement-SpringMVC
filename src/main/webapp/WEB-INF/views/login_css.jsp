<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!DOCTYPE html>
<html>
<head>
<!-- <script type="text/javascript" src="javascript/jquery.js"></script> -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

input {
	width: 300px;
}

#otp_button {
	width: 64px;
    padding: 15px 0px;
    background-color: coral;
    color: whitesmoke;
    padding: 2px 0px;
    font-size: medium;
    margin: 5px 80px;
    border: none;
    cursor: pointer;
}

#otp_button:hover {
	opacity: 0.8;
    background-color: green;
}

.main {
	background-color: red;
}

.signup_form {
	border: 3px solid #f1f1f1;
	width: 900px;
}

.login_form {
	border: 3px solid #f1f1f1;
	width: 300px;
}

input[type=text], input[type=password] {
	width: 300px;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	    width: 120px;
    background-color: #04AA6D;
    color: #682A51;
    padding: 8px 15px;
    margin: 5px 8px;
    border: none;
    cursor: pointer;
    border-radius: 6px;
}

button:hover {
	opacity: 0.8;
}

.login_div {
	width: 300px;
	float: left;
	margin-left: 220px;
}

.logout_div {
	float: right;
	display: flex;
	margin-right: 220px;
}

.otp_button1 {
	float: right;
    display: flex;
        margin-left: -121px;
    padding: 8px 56px;
}

.contact_num {
	float: left;
	display: flex;
}

.otp_main {
	display: flex;
}

}
.login_head {
	font-style: revert;
	width: 300px;
	font-size: xx-large;
	background-color: darkgray;
	color: darkblue;
}

.cancelbtn {
	width: 80px;
	padding: 8px 5px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

.book_img {
	width: 15%;
	border-radius: 50%;
}

.container_input {
	padding: 16px;
	width: 900px;
	display: flex;
	float: left;
}

.input_left {
	width: 300px;
	float: left;
	margin-right: 335px;
}

.container_bottom {
	padding: 16px;
	width: 600px;
	margin-left: -90px;
	margine-right: 16px;
	margine-bottom: 270px;
	float: left;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}

input[type="file"]::file-selector-button {
  border: 2px solid #6c5ce7;
  padding: 0.2em 0.4em;
  border-radius: 0.2em;
  background-color: #a29bfe;
  transition: 1s;
}

input[type="file"]::file-selector-button:hover {
  background-color: #81ecec;
  border: 2px solid #00cec9;
}
</style>
</head>
<body>
<%
	String error = (String) request.getParameter("error");
	String status = request.getParameter("status");
%>

	<div>

		<%@ include file="index.jsp"%>

		<h2 id=error_msg></h2>

		<div class="login_div" id="login_div">
			
			<form:form action="process_login" method="POST" class="login_form">
			
				<h2 align="center" class="login_head">Login</h2>
				<%if(error != null) {
			%>
				<p style="color: red; font-size: 18px;" align="center">Incorrect username or password!</p>
		<% }%>
		
		<%if(status != null) {
			%>
				<p style="color: green; font-size: 18px;" align="center"><%=status %></p>
		<% }%>
				<%-- <h3>${errorMessage}</h3> --%>
				<c:if test="true}">
					<h5 style="color: red; font-size: 18px;">${param.error}</h5>
				</c:if>
				<!-- <div class="imgcontainer">
					<img
						src="https://cdn.vectorstock.com/i/1000x1000/52/09/book-icon-design-library-symbol-web-vector-11415209.webp"
						alt="img" class="book_img">
				</div> -->
				
				<c:if test="${not empty ${status}">
					<h5 style="color: red; font-size: 18px;">${status}</h5>
				</c:if>

				<div class="container">
					<label for="username"><b>Username</b></label> <input type="text"
						placeholder="Enter Username" name="username" required> <label
						for="password"><b>Password</b></label> <input type="password"
						placeholder="Enter Password" name="password" required>

					<button type="submit">Login</button>
					<!-- <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label> -->
				</div>

				<div class="container" style="background-color: #f1f1f1">
					<button type="reset" class="cancelbtn" onClick=>Cancel</button>
					<span class="psw">Forgot <a href="forgot_password">password?</a></span>
					<!-- <a href="signupForm">Sign up</a> -->
					<button onclick="signupShow()" id="btn1">Sign up</button>
				</div>
			</form:form>

		</div>
		<div class="logout_div" id="logout_div">


			<form:form action="signup" method="POST" enctype="multipart/form-data" class="signup_form">
				<h2 align="center" class="login_head">Sign up</h2>
				<!-- <div class="imgcontainer">
				<img
					src="https://cdn.vectorstock.com/i/1000x1000/52/09/book-icon-design-library-symbol-web-vector-11415209.webp"
					alt="img" class="book_img">
			</div> -->

				<div class="container_input">
					<div class="input_left">
						<label for="name"><b>Full Name</b></label> <input type="text"
							placeholder="Enter Username" name="name" required> <label
							for="username"><b>Username</b></label> <input type="text"
							placeholder="Enter Password" name="username" required /> <br>
							
							<label for="contact"><b>Contact Number</b></label><br>
						<div class="otp_main">
						
						
							<div class="contact_num">
								 <input
									type="text" placeholder="Enter Password" name="mobNumber"
									required />
							</div>
							<div class="otp_button1">
								<button id="otp_button">Verify</button>
							</div>
						</div>		
							
							<label for="password"><b>Password</b></label> <input
							type="password" placeholder="Enter Password" name="password"
							required /> <label for="cpassword"><b>Confirm
								Password</b></label> <input type="password" placeholder="Confirm Password"
							name="cpassword" required />
							<label> Choose your Role: </label> <br> 
		<input type="radio" id="role_admin" name="role" value="ADMIN" /> ADMIN <br> 
		<input type="radio" id="role_user" name="role" value="USER" /> USER <br />
						<p id="image">Photo: <input type="file" name="photo"> </p>


						<button type="submit">Submit</button>
						<button type="reset" class="cancelbtn" onClick=>Cancel</button>
						<!-- <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label> -->
					</div>


					<div class="container_bottom" style="background-color: #f1f1f1">
						<button type="reset" class="cancelbtn" onClick=>Cancel</button>
						<span class="psw">Forgot <a href="#">password?</a></span>
						<button onclick="loginShow()" id="btn2">Login</button>

					</div>
				</div>
			</form:form>
		</div>
	</div>


	<script type="text/javascript">
	
	$(document).ready(function(){
		
		/* alert("Alert msg"); */
		document.getElementById("login_div").style.display = "block";

		document.getElementById("logout_div").style.display = "none";
		document.getElementById("image").style.display = "none";

		
		 const queryString = window.location.search;
	    const urlParams = new URLSearchParams(queryString);
	    const product = urlParams.get('login_error');
	    
	    if(product=='' || product==null || product=="" || product==undefined){
	    }
	    else{
			alert("--" + product);
	    } 

	});
	
	$(document).ready(function() {
		  $('#btn1').click(function() {
			  $("#login_div").hide(300);
			  $("#logout_div").show(300);
		  });
		  $('#btn2').click(function() {
			  $("#logout_div").hide(300);
			  $("#login_div").show(300);
		  });
		  
		  $('#role_user').click(function() {
			  $("#image").show(300);
		  });
		  $('#role_admin').click(function() {
			  $("#image").hide(300);
		  });
		  
		});
	
		
	</script>
</body>
</html>
