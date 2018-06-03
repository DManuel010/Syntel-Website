<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Customer Login</h1>
	<form method="POST" modelAttribute="customer" action="/customer/login">
		<!-- Customer Email -->
		<label for="customer_email">Email:</label>
		<form:input path="customer.email" placeholder="Enter your email address"/>
		<form:errors path="customer.email"/>
		<br/>
		
		<!-- Customer password -->
		<label for="customer_password">Password:</label>
		<form:input path="customer.password" placeholder="Enter a password"/>
		
		
		<!-- Customer password -->
		<label for="customer_id">id:</label>
		<form:input path="customer.id"/>
		<br/>
		<input type="submit" value="Log In" />
		<input type="reset" value="Clear Form" />
	</form>
</body>
</html>