<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/foundation/5.5.2/css/foundation.min.css">
</head>
<body>
	<center>
		<h2>Brain Honey</h2>
		<div ng-app="">
			<div class="alert-box">
			${message}
			</div>
		</div>
		<h3>			
			<a href="hello?page=createDomain">Create Domain</a></br>
			<a href="hello?page=createUser">Create User</a></br>
			<a href="hello?page=logout">Logout</a></br>
			<a href="jasperReport?reportPage=report1">Report1</a>
			<a href="runJRS?reportPage=report1">Jasper Server Report</a>
		</h3>		
	</center>
</body>
</html>