<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Spring 4 MVC</title>
	<jsp:include page="include/mappedResources.jsp" />
	
	
	<script type="text/javascript" language="javascript" class="init">		

		$(document).ready(function() {
			var oTable = $("#domain").dataTable({				
				tableTools: {
					"sRowSelect": "os"
				}
			});			
			
// 			oTable.fnClearTable();
			for(var i = 0; i < ${listDomain}.length; i++) {				
				oTable.fnAddData([
				${listDomain}[i]['id'],
				${listDomain}[i]['name'],
				${listDomain}[i]['parentid'],
				${listDomain}[i]['userspace'],
				${listDomain}[i][''],
				]);
			} // End For
			
			function deleteDomain() {			
				$('.delete').on('click',function(){
					var domain = $(this).attr('id');
					alert(domain);
					$.ajax({
						url: 'deleteDomain/' + domain,
						dataType: 'json',
						success: function(s){
							$("#message").html("<span class='success'>Domain is deleted.</span>");
							oTable.fnClearTable();
							for(var i = 0; i < s.length; i++) {
								oTable.fnAddData([
								${listDomain}[i]['id'],
								${listDomain}[i]['name'],
								${listDomain}[i]['parentid'],
								${listDomain}[i]['userspace'],
								${listDomain}[i][''],
								]);
							} // End For
							deleteDomain();
						},
						error: function(e){
							console.log(e.responseText);
						}
					});
				});
			}
			deleteDomain();
		} );
		/* var RestPost = function() {
			
			 $.ajax({  
			     type : "Get",   
			     url : "/spring4-mvc-gradle-xml/hello",   
			     data : "page=createDomain",  
			     success : function(response) { 
			      
			     },  
			     error : function(e) {  
			      alert('Error: ' + e);   
			     }  
			    });
			} */
	</script>
	</head>
	<body class="dt-example">		
	        <div class="container">
	        <section>
		        <h4> Brain-Honey </h4>
		        <input type="hidden" name="currentTab" id="currentTab" value="${tabIndex}" />
	                <ul class="tabs">
						<li class="active">Domain</li>
						<li>User</li>
					</ul>
					<div class="tabs">             
	                <div class="domain">
	                	<h4><span id="message">  ${message} </span></h4>              
	                    <form method="post" action="createDomain">
	                        <label for="reference">Reference id:</label> <br /> 
	                        <input type="text" name="reference" /> <br />
	                        <label for="domainName">Domain Name:</label> <br /> 
	                        <input type="text" name="domainName" /> <br />
	                        <label for="userspace">User space:</label> <br /> 
	                        <input type="text" name="userspace" /> <br />
	                        <br /> <input type="submit" value="Submit">
	                    </form>
	                    <br />
	                    <div id="demo_jui">
	                    	<table id="domain" class="display table table-bordered">
	                    		<thead>
	                    			<tr>
	                    				<th><u>id</u></th>
				                    	<th><u>name</u></th>
				                      	<th><u>parentid</u></th>
				                      	<th><u>userspace</u></th>
				                      	<th><u></u></th>
					                </tr>
					            </thead>                	
	                    	</table>	                    	
					    </div>
					    <br />
	                </div>
	                <div class="user">
	                    <h4> ${message} </h4>                    
	                    <form method="post" action="createUser">
	                        <label for="email">First Name:</label> <br /> 
	                        <input type="text" name="firstName" /> <br />
	                        <label for="email">Last Name:</label> <br /> 
	                        <input type="text" name="lastName" /> <br />
	                        <label for="password">User Name:</label> <br /> 
	                        <input type="text" name="userName" /> <br />
	                        <label for="password">Password:</label> <br /> 
	                        <input type="password" name="password" /> <br />
	                        <br /> <input type="submit" value="Submit">	                        
	                    </form>
	                    <br />
	                </div>
	            </div>
	        </section>
	    </div>
	</body>
</html>