<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Spring 4 MVC</title>
		<jsp:include page="include/mappedResources.jsp" />	
		<script type="text/javascript" language="javascript" class="init">
		</script>
	</head>
	<body class="dt-example">
		<div class="container">
	        <section>
	        	<h4> Brain-Honey </h4>
	      
	      		<ul class="tabs">
				<li class="active">Login</li>				
				</ul>
	           	<div id="tabs">                
	              <div id="login">                	
	                  <h4> ${message} </h4>
	                  <form method="post" action="login" modelAttribute="user">
	                      <label for="prefixName">Prefix Name:</label> <br />
	                      <input type="text" name="prefixName" /> <br />
	                      <label for="userName">User Name:</label> <br /> 
	                      <input type="text" name="userName" /> <br />
	                      <label for="userPassword">Password:</label> <br />
	                      <input type="password" name="userPassword" /> <br />
	                      <br /> <input type="submit" value="Login">                    
	                  </form>                    
	              </div>                
	          	</div>   
	         </section>     
	    </div>
	</body>
</html>