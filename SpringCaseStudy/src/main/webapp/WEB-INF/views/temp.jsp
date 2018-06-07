<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
	<div id="content">
		<h1>Login successfully</h1>
		<h2>hello ${customer.first_name}</h2>
		
		<c:out value="${sessionScope.customer}"/>
		
		<a href="/order/food">Order Food</a>
	</div>
</body>
</html>