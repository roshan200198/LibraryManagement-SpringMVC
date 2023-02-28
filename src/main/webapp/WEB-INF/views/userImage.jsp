<%@page import="java.io.OutputStream"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@ page language="java" contentType="image/jpg; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
				byte[] imgByte = (byte[])request.getAttribute("imageByte");
				//response.setContentType("image/jpeg");
				OutputStream img = response.getOutputStream();
				img.write(imgByte);
				img.flush();
				img.close();
			%>
</body>
</html>