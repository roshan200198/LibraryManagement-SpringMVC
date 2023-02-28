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
<%
	String status =  (String) request.getAttribute("status");

%>
<h2><%=status %></h2>

<%-- <%
	String bookid = (String) request.getAttribute("id");
	String bookname = (String) request.getAttribute("name");
	String status = (String) request.getAttribute("status");
	
	out.print(status);
	out.print(bookid);
	out.print(bookname);
	
%> --%>
<%-- <h2> <%= status %> </h2>
BookId :: <h2> <%= bookid %></h2>
Book Name :: <h2> <%= bookname %></h2> --%>
</body>
</html>