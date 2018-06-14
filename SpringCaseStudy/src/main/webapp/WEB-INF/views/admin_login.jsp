<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>

<div id="content">
	<h1>Admin Login</h1>
	<form method="POST" modelAttribute="employee" action="/admin/login">


	<!-- employee id -->
	<label for="employee_employeeId">Employee ID:</label>
	<form:input class="form-control" path="employee.employeeId" placeholder="Enter your ID"/>
	<form:errors path="employee.employeeId"/>
	<br/>
	
	<!-- employee Email -->
	<label for="employee_email">Email:</label>
	<form:input class="form-control" path="employee.email" placeholder="Enter your email address"/>
	<form:errors path="employee.email"/>
	<br/>

	<input class="btn btn-danger btn-lg btn-block" type="submit" value="Log In" />
	<input class="btn btn-danger btn-lg btn-block" type="reset" value="Clear Form" />
	</form>
</div>

	
<%@include file="footer.jsp" %>