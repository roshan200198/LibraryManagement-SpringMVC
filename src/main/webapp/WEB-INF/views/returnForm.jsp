<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style >
.value-button {
  display: inline-block;
  border: 1px solid #ddd;
  margin: 0px;
  width: 40px;
  height: 20px;
  text-align: center;
  vertical-align: middle;
  padding: 11px 0;
  background: #eee;
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.value-button:hover {
  cursor: pointer;
}

input#number {
  text-align: center;
  border: none;
  border-top: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  margin: 0px;
  width: 40px;
  height: 40px;
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

form {
			max-width: 615px;
			margin: 40px auto;
			background-color: #fff;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0 2px 10px rgba(0,0,0,0.3);
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

label {
			display: block;
			color: #333;
		}

input[type="text"] {
			display: block;
			width: 90%;
			padding: 10px;
			border: 2px solid #ccc;
			border-radius: 6px;
			font-size: 16px;
			
		}

button:hover {
	opacity: 0.8;
}
</style>
<title>Insert title here</title>
</head>
<body>
<%@ include file="adminIndex.jsp" %>
<h1 align="center">Return Book page!!</h1>
<form action="returnBook" method="post">
<c:if test="${!empty status}">
			<h3  style="color: green; text-align: center;">${status}</h3>
		</c:if>

		<c:if test="${!empty fail_status}">
			<h3 align="center" style="color: red; text-align: center;">${fail_status}</h3>
		</c:if>
	<label for="email">User ID:</label> <input type="text" name="userId" /><br><br>
	<label for="email">User Name:</label> <input type="text" name="userName" /><br><br>
	<label for="email">Book ID:</label> <input type="text" name="bookId" /><br><br>
	<label for="email">Book Name:</label> <input type="text" name="bookName" /><br><br>
	
	Enter Number of Book <br>
		<div class="value-button" id="decrease" onclick="decreaseValue()"
			value="Decrease Value">-</div>
		<input type="number" id="number" value="0" name="bookCount" />
		<div class="value-button" id="increase" onclick="increaseValue()"
			value="Increase Value">+</div>
	
	<button class="button" type="submit"> Submit</button>
</form><br>
<a id="a1" href="viewBookTableForAdmin">View Book Table</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a id="a1" href="viewUser?page=1">Users who issue book</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;



<script>

function increaseValue() {
	  var value = parseInt(document.getElementById('number').value, 10);
	  value = isNaN(value) ? 0 : value;
	  value++;
	  document.getElementById('number').value = value;
	}
	
function decreaseValue() {
	  var value = parseInt(document.getElementById('number').value, 10);
	  value = isNaN(value) ? 0 : value;
	  value < 1 ? value = 1 : '';
	  value--;
	  document.getElementById('number').value = value;
	}


</script>
</body>
</html>