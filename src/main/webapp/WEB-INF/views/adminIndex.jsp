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
    background-color: lightgoldenrodyellow;
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

.div1 {
	float: left;
    margin-left: -354px;
    margin-right: 355px;
    margin-top: 75px;
}

.div2 {
	    width: 200px;
    margin-left: -110px;
    padding: 0px 0px;
}

.div3 {
	    width: 120px;
    margin-left: px;
    padding: 0px 0px;
}
</style>
<title>Insert title here</title>
</head>
<body >
	<div class="div_header">
	<h1>Thise admin signin page!!</h1><br>
	<div class="div1">	
		<br> <a href="addForm"><button>Add Book</button></a>
		<!-- <a href="issueForm"><button>Issue Book</button></a> -->
		<a href="returnForm"><button>Return Book</button></a>
		<a href="signin"><button>Home</button></a>
	</div>
	<div class="div2">
		
		<h2>Welcome, ${username}</h2>
		<a href="/SpringMvc3/logout"><button>Logout</button></a>
	</div>
	
	<div class="div3">
	
		
	</div>
		
		<!-- <body onLoad="login();"> -->
	</div>
</body>
</html>