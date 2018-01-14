<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<title>CICD Login Page</title> 
	<style>body {background-color:#6A5ACD}</style>
</head> 
<body>
	<center><h1>Continuous Integration And Continuous Delivery</h1></center>
	<img src="images/CI-CD.png" alt="Mountain View" style="width:1370px;height:200px;">
	<center><h2>Signup Details</h2> 
		
	<form action="ValidatorServlet" method="post"> <br/>
		<b>Username:</b><input type="text" name="username"> <br/><br>
		<b>Password:</b><input type="password" name="password"> <br/><br>
		<input id="click" type="submit" value="Submit"> 
	</form> 
	
	</center>
</body> 
</html>

