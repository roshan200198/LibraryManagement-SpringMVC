<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div>
	
	<h2> Set mail.. </h2>
	<form id="mail_form" action="actionDemoJS" method="post" >
	<p id="status" style="color: red; font-size: 18px;"></p>
		<%-- <c:if test="${otp > 8000}">  
  		 	<p>My income is: <c:out value="${income}"/><p>  
		</c:if> --%>
		
			Enter password :<br><br> <input type="text" id="pswd"
				name="pswd" required /><br><br>
				Cofirm password :<br><br> <input type="text" id="cpswd"
				name="cpswd" required /><br><br>
				<%-- Mail ID :<br> <input type="text" id="mailId"
				name="mailId" value="${mailId}" required /> --%>
			
			<button id="check_pswd" type="submit" onClick="checkPassword()">Submit</button>
	</form>

</div>


<script type="text/javascript">

function checkPassword(){
	
		var pswd = document.getElementById("pswd").value;
		var cpswd = document.getElementById("cpswd").value;
		
		if(pswd != cpswd) {
			event.preventDefault();
			 /* document.getElementById("status").innerHTML = "Password not match.."; */
			 alert("not match..")
			
		} 
		
}

</script>
</body>
</html>