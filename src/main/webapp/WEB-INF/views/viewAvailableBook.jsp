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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.value-button {
  display: inline-block;
  border: 1px solid #ddd;
  margin: -5px 5px;
  width: 20px;
  height: 20px;
  text-align: center;
  vertical-align: middle;
  padding: 0px 0px;
  background: cadetblue;
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

input[type=number] {
  text-align: center;
  border: none;
  border-top: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  margin: 4px;
  width: 40px;
  height: 20px;
  margin: -6px;
}

table, th, td {
	border: 1px solid black;
}

#a1 {
	color: black;
	font-size: large;
}

#a1:hover {

 background-color: yellowgreen;
}

.search_input {
	width: 168px;
    height: 25px;
}

.search {
	float: left;
	margin-left: 15px;
}

.table {
	width: 50%;
}

#book_table th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: cadetblue;
	color: white;
	align: center;
	text-align: center;
}

#book_table tr:nth-child(even) {
	background-color: #f2f2f2;
	border: 3px solid green;
}

#book_table tr:hover {
	background-color: #ddd;
}

td {
	text-align: center;
}

#book_table {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 600px;
	border: 3px solid green;
	margin-left: auto;
}

.main {
	display: flex;
	background-image: url("");
	width: 680px;
    margin-left: 355px;
}

.dropdown {
	margin-left:8px;
	width: 150px;
	height: 30px;
	font-size: large;
	margin-top: 25px;
}

#dropdown {
	margin-left: 40px;
	width: 150px;
	height: 30px;
	font-size: large;
}

.searchbtn {
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

.searchbtn:hover {
	opacity: 0.8;
    background-color: green;
}

.left_div {
	margin-left: -345px;
}

.dropbtn {
            background-color: #04AA6D;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 290px;
            box-shadow: 0px 0px 3px -1px rgba(0, 0, 0, 0.2);
            
        }

        .dropdown-content h4 {
            color: black;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #ddd;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown:hover .dropbtn {
            background-color: #3e8e41;
        }
        
        #dropdownbtn {
        	width: 120px;
    background-color: orange;
    color: darkblue;
    padding: 8px 5px;
    margin: 5px 75px;
    border: none;
    cursor: pointer;
    border-radius: 6px;
    font-size: larger;
        }

input[type=checkbox] {
    background-color: initial;
    cursor: default;
    appearance: auto;
    box-sizing: border-box;
    margin: 0px 10px;
    padding: initial;
    border: initial;
}

</style>
</head>
<body>
	<%@ include file="userSigninPage.jsp"%><br />
	<br />
	<div class="main">
	<div class="left_div">
			<a id="a1" href="downloadExcelSheet?type=excel" var="xlxURL">Download
				ExcelSheet</a><br>
			<br>
			<!-- br> <a id="a1" href="viewUser?page=1">Users who issue book</a> -->
		</div>

		<table id="book_table" style="width: 500px;">
			<tr>

				<th>Book ID</th>
				<th>Book Name</th>
				<th>Book Status</th>
				<th>No of Books</th>
			</tr>
			<c:forEach items="${obj}" var="list1">
				<%-- <option value="${list}">${list1}</option> --%>

				<tr style="height: 40px">

					<td value="${list1}">${list1.bookId}</td>
					<td value="${list1}">${list1.bookName}</td>
					<td value="${list1}">${list1.bookStatus}</td>
					<td value="${list1}">${list1.bookNumbers}</td>
				</tr>

			</c:forEach>
		</table>

		<div class="search">
			<form action="searchAvailableBook">
				<input class="search_input" name="txtbookname"
					, placeholder="Enter Book Name" /> <br>
				<button class="searchbtn" type="submit">Search</button>
			</form>
			
			<div class="dropdown">
				<form id="dropdown_form">
					
					<c:forEach items="${obj}" var="list1">
				
						<button class="dropbtn" id="dropdownbtn" onClick="dropdownFunc()">Dropdown</button>
						<div class="dropdown-content" id="dropdown-content"
							style="display: block;">
							<h4 href="#">
							
								<input type="checkbox" value="${list1.bookName}" name="bookName" id="${list1.bookName}"
									onclick="countShow('${list1.bookName}')">${list1.bookName}
									   	
								<div id="${list1.bookName}${list1.bookName}" style="display: flex; display: none; float: right;" >
								<div class="value-button" id="decrease"
									onclick="decreaseValue('${list1.bookName}_count')" value="Decrease Value">-</div>
								<input type="number" id="${list1.bookName}_count" value="0" name="bookCount" />
								<div class = "value-button" id="increase"
									onclick="increaseValue('${list1.bookName}_count')" value="Increase Value">+</div>
									</div>
								</input>
								
							</h4>
							
							<button id="dropdownbtn" onClick="checkAvailableBookAndCountBook()" type="button">Issue Book</button>
						</div>

					</c:forEach>
				</form>
			</div>
		</div>
		
		<!-- <button type="submit" float="left"><a href="viewBookTable.jsp?page=1">1</a></button> -->

	</div>
	<br>
	<br>
	<!-- <button type="submit" float="left"><a href="downloadExcelSheet?type=excel" var="xlxURL">Download ExcelSheet</a></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="viewUser?page=1">Users who issue book</a> -->

<script>
 function countShow(arg) {
//	  alert(arg+arg)
	  
	  var checkbox = document.getElementById(arg);
//	  alert(checkbox.id);
   
    var countbox = document.getElementById(arg+arg);
    if (checkbox.checked == true) {
        countbox.style.display = "block";
    } else {
        countbox.style.display = "none";
    }
	}; 

	function checkAvailableBookAndCountBook() {
		alert("bbhc");
		  const checkboxes = document.querySelectorAll('input[type="checkbox"]');
		  
		  const checkedBooks = [];
		  const bookCounts = [];

		  // Loop through all checkboxes and get the checked ones
		  checkboxes.forEach(checkbox => {
		    if (checkbox.checked) {
		      checkedBooks.push(checkbox.value);
			  alert("#"+checkbox.value + "_count");
		      const bookCount = document.getElementById(checkbox.value + "_count");
		      alert(checkbox.value + "_count");
		      bookCounts.push(bookCount.value);
		      console.log(bookCounts);
		      alert(bookCounts);
		    }
		  });
		  
		  // Log the checked books and corresponding counts to the console
		  console.log(checkedBooks);
		  console.log(bookCounts);
		  alert(checkedBooks);
		  alert(bookCounts);
		  
/* 		  const xhr = new XMLHttpRequest();
		  xhr.open("POST", "/issueMultipleBooks", true);
		  xhr.setRequestHeader("Content-Type", "application/json");
		  xhr.send(JSON.stringify(bookCounts));
		  xhr.send(JSON.stringify(checkedBooks));
		  alert(JSON.stringify(bookCounts)); */
		  
		  var new_data = {
				    "checkBooks": checkedBooks,
				    "checkCounts": bookCounts
				};
		  
		  $.ajax({
		      url: "http://localhost:8080/SpringMvc3/user/issueMultipleBooks",
		      type: "POST",
		      data: JSON.stringify(new_data),
		      contentType: "application/json",
		      success: function(response) {
		          // Handle the response
		          console.log(JSON.stringify(new_data));
		          alert("in AJAX POST");
		      },
		      error: function(xhr, status, error) {
		          // Handle the error
		          console.log(JSON.stringify(new_data));
		      		alert("in AJAX error score"+error+xhr+status+" "+JSON.stringify(new_data));
		      }
		  });
		  
		}
	

function increaseValue(arg) {
	  var value = parseInt(document.getElementById(arg).value, 10);
	  value = isNaN(value) ? 0 : value;
	  value++;
	  document.getElementById(arg).value = value;
	}
	
function decreaseValue(arg) {
	  var value = parseInt(document.getElementById(arg).value, 10);
	  value = isNaN(value) ? 0 : value;
	  value < 1 ? value = 1 : '';
	  value--;
	  document.getElementById(arg).value = value;
	}


</script>

</body>
</html>




