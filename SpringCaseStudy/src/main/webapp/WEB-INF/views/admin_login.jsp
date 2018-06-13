<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
</head>
<body>
<h1>Admin Login</h1>
<form method="POST" modelAttribute="employee" action="/admin/login">

			<!-- employee id -->
			<label for="employee_employeeId">Employee ID:</label>
			<form:input path="customer.employeeId" placeholder="Enter your ID"/>
			<form:errors path="customer.employeeId"/>
			<br/>
			
			<!-- employee Email -->
			<label for="employee_email">Email:</label>
			<form:input path="employee.email" placeholder="Enter your email address"/>
			<form:errors path="employee.email"/>
			<br/>
		
			<input type="submit" value="Log In" />
			<input type="reset" value="Clear Form" />
		</form>
</body>
</html>