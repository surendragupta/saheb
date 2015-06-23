<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Spring 4 MVC</title>
	
	<link rel="stylesheet" href="css/jquery-ui.css">
	<link rel="stylesheet" href="css/style.css">
	 
	<script src="js/jquery-1.11.1.js"></script>
	<script src="js/jquery-ui.js"></script>
	
	
	<script>
		$(function() {		  
			$( "#tabs" ).tabs();	
		});
	</script>
</head>
<body>
	<div class="wrapper">
        <div class="container">
        <div class="container-fluid">
  <h1>Brain-Honey</h1>
  
</div>
       
            <div id="tabs">
                <ul>
                    <li><a href="#login">Login</a></li>                   
                </ul>
                <div id="login">                	
                    <h4> ${message} </h4>
                    <form:form method="post" action="login" modelAttribute="user">
                        <label for="email">Prefix Name:</label> <br />
                        <form:input path="prefixName" /> <br />
                        <label for="email">User Name:</label> <br /> 
                        <form:input path="userName" /> <br />
                        <label for="password">Password:</label> <br />
                        <form:password path="userPassword" /> <br />
                        <br /> <input type="submit" value="Login">                    
                    </form:form>                    
                </div>
            </div>
        </div>
    </div>
</body>
</html>