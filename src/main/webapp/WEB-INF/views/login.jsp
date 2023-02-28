<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="process_login" method="POST" >
<h1>Custom login Page </h1>
	Username: <input type="text" name="username" /><br><br>
	Password: <input type="password" name="password" /><br><br>
	<button type="submit" name="submit" >Submit</button>
</form:form>




<%-- <form action="/login" method="post">
	Username: <input type="text" name="username" /><br><br>
	Password: <input type="password" name="password" /><br><br>
	<button type="submit" name="submit" >Submit</button>

</form> --%>
</body>
</html>