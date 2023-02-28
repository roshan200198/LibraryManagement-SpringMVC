<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- <%@ include file="index.jsp" %> --%>
<%@ include file="adminIndex.jsp" %>

<h1>Issue Book Status</h1>

<br><br>
<h1>${status}</h1><br><br>
User Id:: <h1>${userid }</h1><br><br>
Book Id:: <h1>${bookid }</h1><br><br>
<h1>${obj.bookName }</h1><br><br>
</body>
</html>