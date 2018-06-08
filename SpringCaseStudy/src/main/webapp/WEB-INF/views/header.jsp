<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mummy's Restaurant - Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${context}/static/libraries/bootstrap.min.css">
	<c:set var="context"  value="${pageContext.request.contextPath}" />
	<link type="text/css" rel="stylesheet" href="${context}/static/css/styles.css">
</head>
<body>
	<div class="logo">
		<a href="${context}/" class="active">
			<img src="${context}/static/images/global/logo.png" alt="logo">
		</a>
	</div>
	
	<nav>
		<c:if test="${sessionScope.customer != null}">
		<ul style="width: 27vw">	
		</c:if>	
		<c:if test="${sessionScope.customer == null}">
		<ul>	
		</c:if>	
			<li><a href="${context}/" class="active">Home</a></li>
			<li><a href="${context}/order/food">Menu</a></li>
			<c:if test="${sessionScope.customer != null}">
				<li><a href="${context}/customer/dashboard">Order</a></li>
				<li><a href="${context}/customer/logout">Log Out</a></li>
			</c:if>
			<li><a href="${context}/info/contact">Contact</a></li>
			<li><a href="${context}/info/about">About</a></li>
			<c:if test="${sessionScope.customer.id == null}">
				<li><a href="${context}/customer/registration">Register</a></li>
				<li><a href="${context}/customer/login">Login</a></li>
			</c:if>
		</ul>
	</nav>
