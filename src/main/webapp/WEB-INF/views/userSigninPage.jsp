<%@page import="java.util.Base64"%>
<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript">
	function login(){
		alert("Login Successfull!");
		//window.location.href = "adminIndex";
	}

</script>
<style >
input[type=text], input[type=password] {
	width: 300px;
	padding: 12px 20px;
	margin: 0px 33px;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	    width: 120px;
    background-color: chocolate;
    color: black;
    padding: 8px 5px;
    margin: 5px 8px;
    border: none;
    cursor: pointer;
    border-radius: 6px;
    font-size: larger;
}

.div_header {
	display: flex;
	width: 100%;
	background-color: burlywood;
	border: black;
	height: 185px;
}

.userIndex {
	width: 100%;
	background: chocolate;
}

button:hover {
	opacity: 0.8;
	background-color: black;
	color: white;
}


.div_header h1 {
	margin: 2px 5px;
}

.div1 {
	float: left;
    margin-left: -275px;
    margin-right: 605px;
    margin-top: 75px;
}

img {
	    border-radius: 50%;
    margin-top: 35px;
}

.div2 {
	    width: 200px;
    margin-left: -110px;
    padding: 10px 0px;
}

.div3 {
	    width: 120px;
   /*  margin-left: 265px; */
    padding: 0px 0px;
}


    
.myDIV:hover {
  color: green;

}
</style>
<title>Insert title here</title>
</head>
<body >
	<div class="div_header">
	<h1>Thise user signin page!!</h1><br>
	<div class="div1">	
		<br> <a href="viewAvailableBook"><button> View Available Books </button></a>
		<a href="issueForm"><button>Issue Book</button></a>
		<a href="viewUser?page=1"><button >My history</button></a>
		<a href="returnFormForUser"><button >Return Book</button></a>
		
		<a href="userSigninPage"><button>Home</button></a>
	</div>
	<div class="div2">
		
		 <h2>Welcome, <p class="myDIV">${username}</p></h2>
		<a href="/SpringMvc3/logout"><button>Logout</button></a>
	</div>
	
	<div class="div3">
		
		<%
				byte[] imgByte = (byte[]) session.getAttribute("imgByte");
				if(imgByte != null) { 
					String base64ImageData = new String(Base64.getEncoder().encode(imgByte));
					String imageSrc = "data:image/jpeg;base64," + base64ImageData; %>
					
					<img class="hide"  src='<%= imageSrc%> ' alt='My Image' width='135' height='140' >
		<% } %>
		
	</div>
		
		<!-- <body onLoad="login();"> -->
	</div>
</body>
</html>