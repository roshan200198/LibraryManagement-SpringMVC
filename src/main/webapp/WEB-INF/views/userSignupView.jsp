<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="index.jsp" %>
<%
	String status = (String) request.getAttribute("status");

%>

<h1> <%= status %></h1>
<h1> ${mailId} .. Signup Successfull!</h1>
</body>
</html>