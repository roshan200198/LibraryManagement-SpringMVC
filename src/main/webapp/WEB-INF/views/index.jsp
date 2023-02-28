<!DOCTYPE html>
<html>

<head>
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"> </script>
<script>
$(document).ready(function(){
	
	alert("Alert msg");
	console.log( "document loaded" );
});
</script> -->

<style>

#indexbtn {
	  width: 120px;
    background-color: #04AA6D;
    color: #682A51;
    padding: 15px;
    margin: 20px 10px;
    border: none;
    cursor: pointer;
    border-radius: 6px;
}

button{
      width: 120px;
    background-color: #04AA6D;
    color: #682A51;
    padding: 8px;
    margin: 5px 0px;
    border: none;
    cursor: pointer;
    border-radius: 6px;
}
.index{
width: 100%;
    background: chocolate;
    
}
button:hover {
	opacity: 0.8;
}
</style>



</head>

<body>
<!-- <body onLoad="logout();"> -->
<div class="index">
<h2>Hello index page!</h2>

<a href="admin/signin" ><button id="indexbtn"> Signin </button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="signupForm"><button id="indexbtn">Sign up</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!-- <a href="user/userSigninPage">User Signin Page</a> -->
<!-- <a href="demoJs"><button id="indexbtn">DemoJs</button></a> -->
</div>
</body>
</html>
