<%@page import="com.app.DAO.JdbcConnection"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="com.app.DAO.BookDao"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.app.Pojo.UserTable"%>
<%@page import="com.app.Pojo.LibraryBean"%>
<%@page import="java.util.*" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%-- <%@ include file="index.jsp" %> --%>
	
	<%@ include file="adminIndex.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
#pagination_button {
	width: 64px;
    padding: 15px 0px;
    background-color: coral;
    color: whitesmoke;
    padding: 2px 0px;
    font-size: medium;
    margin: 0px 0px;
    border: none;
    cursor: pointer;
}

.pagination_div {
	width: 362px;
    margin: 3px 250px;
    margin-top: -40px;
    margin-bottom: -230px;
	
}

.search_button:hover {
	opacity: 0.8;
    background-color: green;
}

table, th, td {
	border: 1px solid black;
}

#book_table th {
	padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: cadetblue;
  color: white;
  align:center;
  text-align: center;
      
}

td { text-align: center;}

#a1 {
	color: black;
	font-size: large;
	float: left;
	margin-top: 70px;
}

#a1:hover {

 background-color: yellowgreen;
}

#book_table tr:nth-child(even){background-color: #f2f2f2; border: 3px solid green;}

#book_table tr:hover {background-color: #ddd; }

#book_table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
      border: 3px solid green;
      margin: 10px 200px;
}

#caption {
	    background-color: chocolate;
    color: ghostwhite;
}

.main_div {
	background-image: url("");
}
</style>

</head>
<body class="main_div">

		<table id="book_table" style="width: 40%" padding="10">
		<caption id="caption"><h1>User who issue a book..</h1></caption>
		<tr>
			<th>User ID</th>
			<th>User Name</th>
			<th>Book ID</th>
			<th>Book Name</th>
			<th>No of Books</th>
		</tr>
		<c:forEach items="${list}" var="list1">
			<%-- <option value="${list}">${list1}</option> --%>

			<tr style="height: 40px">
				<td value="${list}">${list1.userId}</td>
				<td value="${list}">${list1.userName}</td>
				<td value="${list}">${list1.bookId}</td>
				<td value="${list}">${list1.book}</td>
				<td value="${list}">${list1.bookNumbers}</td>
			</tr>

		</c:forEach>
	</table> <br><br>

	<div class="pagination_div">
		<c:forEach begin="1" end="${noOfBtn}" var="i">
				<a href="viewUser?page=${i}"><button id="pagination_button">${i}</button></a> 
		</c:forEach>
	</div>
	<br>

	<a id="a1" href="viewBookTableForAdmin">View Book Table</a>

</body>
</html>