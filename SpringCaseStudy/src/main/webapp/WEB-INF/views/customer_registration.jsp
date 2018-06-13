<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
<link type="text/css" rel="stylesheet" href="${context}/static/css/customer_registration.css">
<div id="content">
	<h1>Customer Registration</h1>
	
	<form method="POST" modelAttribute="customer" action="/customer/register">
	
		<!-- Customer First Name -->
		<label for="customer_first_name">First Name</label>
		<form:input class="form-control" path="customer.first_name" placeholder="John"/>
		<form:errors path="customer.first_name" />
		<br/>
		<!-- Customer Last Name -->
		<label for="customer_last_name">Last Name</label>
		<form:input class="form-control" path="customer.last_name" placeholder="Smith"/>
		<form:errors path="customer.last_name"/>
		<br/>
		<!-- Customer Email -->
		<label for="customer_email">Email</label>
		<form:input class="form-control" path="customer.email" placeholder="john@smith.com"/>
		<form:errors path="customer.email"/>
		<br/>
		<!-- Customer password -->
		<label for="customer_password">Password</label>
		<form:input class="form-control" path="customer.password" type="password"/>
		<form:errors path="customer.password"/>
		<br/>
		<!-- Customer confirm password -->
		<label for="customer_last_name">Confirm Password</label>
		<form:input class="form-control" path="customer.confirm_password" type="password"/>
		<form:errors path="customer.confirm_password"/>
		<br/>
		<!-- Customer birthday -->
		<label for="customer_dob">Birthday</label>
		<form:input class="form-control" path="customer.dob" placeholder="11-Nov-2011"/>
		<form:errors path="customer.dob"/>
		<br/>
		<!-- Customer home phone -->
		<label for="customer_home_number">Home Number</label>
		<form:input class="form-control" path="customer.home_number" placeholder="123-456-7890"/>
		<form:errors path="customer.home_number"/>
		<br/>
		<!-- Customer mobile phone -->
		<label for="customer_mobile_number">Mobile Number</label>
		<form:input class="form-control" path="customer.mobile_number" placeholder="123-456-7890"/>
		<form:errors path="customer.mobile_number"/>
		<br/>
		<input class="btn btn-danger btn-lg btn-block" type="submit" value="Register" />
		<input class="btn btn-danger btn-lg btn-block" type="reset" value="Clear Form" />
	</form>
	<div id="spacer"></div>
</div>
<%@include file="footer.jsp" %>
