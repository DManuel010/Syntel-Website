<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
	<div id="content">
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
			<form:errors path="customer.password"/>
			<br/>
			
			<input type="submit" value="Log In" />
			<input type="reset" value="Clear Form" />
		</form>
<%@include file="footer.jsp" %>
