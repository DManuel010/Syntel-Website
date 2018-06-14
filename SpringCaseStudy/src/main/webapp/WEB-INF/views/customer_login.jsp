<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
	<div id="content">
		<h1>Customer Login</h1>
		<form method="POST" modelAttribute="customer" action="/customer/login">
			<table>
				<tr>
					<th>
						<label for="customer_email">Email</label>
					</th>
					<th>
						<form:input  class="form-control" path="customer.email" placeholder="john@smith.com"/>
						<form:errors path="customer.email"/>
					</th>
				</tr>
				<tr>
					<th>
						<label for="customer_password">Password</label>
					</th>
					<th>
						<form:input  class="form-control" path="customer.password" type="password"/>
						<form:errors path="customer.password"/>
					</th>
				</tr>
			</table>
			<input class="btn btn-danger btn-lg btn-block" type="submit" value="Log In" />
			<input class="btn btn-danger btn-lg btn-block" type="reset" value="Clear Form" />
		</form>
		<a href="/admin/login">Admin Login</a>
	</div>
<%@include file="footer.jsp" %>
