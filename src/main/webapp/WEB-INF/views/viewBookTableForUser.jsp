<%@page import="com.app.Pojo.LibraryBean"%>
<%@page import="java.util.*" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%-- <%@ include file="index.jsp" %> --%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table, th, td {
  border: 1px solid black;
}
.search{
   float: left;
    margin-left: 30px;
    padding: 12px 10px;
    margin: -10px 0px;
    display: inline-block;
    /* border: 1px solid #ccc; */
    box-sizing: border-box;
    width: 198px;
    height: 90px;
}

.search_button {
	width: 64px;
    padding: 15px 0px;
    background-color: coral;
    color: whitesmoke;
    padding: 2px 0px;
    font-size: medium;
    margin: 5px 80px;
    border: none;
    cursor: pointer;
}

.search_button:hover {
	opacity: 0.8;
    background-color: green;
}

.table{
width: 50%;
}
.main_div{
display: flex;


}
.dropdown{
margin-left: -40px;
margin-top: 15px;
width: 150px;
height: 30px;
font-size: large;
}
#dropdown{
margin-left: 40px;
width: 150px;
height: 30px;
font-size: large;
}

#a1 {
	color: black;
	font-size: large;
}

#a1:hover {

 background-color: yellowgreen;
}

.button {
	width: 140px;
	padding: 15px 0px;
	background-color: lightgreen;
	color: #682a51;;
	padding: 16px 5px;
	font-size: larger;
	margin: 15px 10px;
	border: none;
	cursor: pointer;
}

.search_input {
	width: 168px;
    height: 25px;
}

.button:hover {
    opacity: 0.8;
    background-color: cadetblue;
}

 

.left_div {
	margin-left: -260px;
}

.main_div {
	background-image: url("");
	width: 680px;
    margin-left: 260px;
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

#book_table tr:nth-child(even){background-color: #f2f2f2; border: 3px solid green;}

#book_table tr:hover {background-color: #ddd; }

#book_table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
      border: 3px solid green;
      margin: 10px 260px;
}

#caption {
	    background-color: chocolate;
    color: ghostwhite;
}

.tableclass {
	margin-right: 275px;
    margin-left: -205px;
    margin-top: -7px;
}
</style>
</head>
<body>
<%@ include file="userSigninPage.jsp" %><br><br>
<div class="main_div" >
		<!-- <div class="left_div">
			<a id="a1" href="downloadExcelSheet?type=excel" var="xlxURL">Download
				ExcelSheet</a> <br><br>
			
		</div> -->
		
		<div class="tableclass">
		<table id="book_table" padding="10">
				<tr>
					<th>User ID</th>
					<th>Book ID</th>
					<th>Book Name</th>
					<th>Book Status</th>
				</tr>
		<c:forEach items="${obj}" var="list1" >
			<%-- <option value="${list}">${list1}</option> --%>

				<tr style="height:40px">
					<td value="${list1}">${list1.userId}</td>
					<td value="${list1}">${list1.bookId}</td>
					<td value="${list1}">${list1.bookName}</td>
					<td value="${list1}">${list1.bookStatus}</td>
				</tr>
		
		</c:forEach>
		</table>
		</div>
		<div class="search">
			<form action="searchForUser">
				<input class="search_input" name="txtbookname"
					, placeholder="Enter Book Name" /> <br>
				<button class="search_button" type="submit">Search</button>
			</form>

			<div class="dropdown">
				<select id="dropdown" name="cars" id="cars">
					<c:forEach items="${obj}" var="list1">
						<%-- <option value="${list}">${list1}</option> --%>
						<option value="${obj}">${list1.bookName}</option>

					</c:forEach>
				</select>

			</div>
		</div>

		<!-- <button type="submit" float="left"><a href="viewBookTable.jsp?page=1">1</a></button> -->

</div><br><br>

		
</body>
</html>




