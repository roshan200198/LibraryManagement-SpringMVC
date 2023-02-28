<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style >
.button {
    width: 95px;
    background-color: #04AA6D;
    color: #682A51;
    padding: 8px 10px;
    margin: 5px 8px;
    border: none;
    cursor: pointer;
    border-radius: 6px;
}

#import_btn {
	width: 75px;
    padding: 15px 0px;
    background-color: coral;
    color: whitesmoke;
    padding: 2px 0px;
    font-size: medium;
    margin: 5px 0px;
    border: none;
    cursor: pointer;
    margin-top: 10px;
}

#import_btn:hover {
	opacity: 0.8;
    background-color: green;
}

}
.button:hover {
    opacity: 0.8;
    background-color: cadetblue;
}

.userIndex {
	width: 100%;
	background: chocolate;
}

#a1 {
	color: black;
	font-size: large;
}

#a1:hover {

 background-color: yellowgreen;
}

button:hover {
	opacity: 0.8;
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

.divmain {
	display: flex;
	
}

.homediv1 {
	margin-right: 185px;
    margin-left: 5px;
    margin-top: 25px;
    
}

.homediv2 {
	width: 270px;
	 
}
</style>
<title>Insert title here</title>
</head>
<body>
<%-- <%@ include file="index.jsp" %> --%>
<%
	String status = (String) request.getAttribute("status");
	String fail_status = (String) request.getAttribute("fail_status");
%>

<%@ include file="adminIndex.jsp" %>
	<div class="divmain">
		<div class="homediv1">
		<!-- <a href="viewIssueUser">View User who issue a Book.</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<a id="a1" href="viewBookTableForAdmin">View Book Table</a><br><br>
		<a id="a1" href="viewUserForAdmin?page=1">Users who issue book</a>
		</div>
		
		<div class="homediv2">
		<form action="import_excel" method="post" enctype="multipart/form-data">
			<h3>Select excel sheet </h3>
			<%if(status != null) {
				%>
				<p style="color:green;text-align:center;"><%=status %></p>
			<%}%>
			
			<%if(fail_status != null) {
				%>
				<p style="color:red;text-align:center;"><%=fail_status %></p>
			<%}%>
			<input type="file" id="fileInput" onchange="handleFileSelect()" name="file" /><br>
			<button id=import_btn type="submit"> Import</button>
		</form>
		</div>
	</div>
	
	
	<script>
	function handleFileSelect() {
	    var filePath = document.getElementById('fileInput').value;
	    console.log(filePath);
	}
	</script>
</body>
</html>