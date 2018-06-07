<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mummy's Restaurant - Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="static/libraries/bootstrap.min.css">
	<c:set var="context"  value="${pageContext.request.contextPath}" />
	<link type="text/css" rel="stylesheet" href="${context}/static/css/styles.css">
</head>
<body>
	<div class="logo">
		<img src="${context}/static/images/global/logo.png" alt="logo">
	</div>

	<nav>
		<ul>
			<li><a href="${context}/" class="active">Home</a></li>
			<li><a href="${context}/menu">Menu</a></li>
			<li><a href="${context}/order">Order</a></li>
			<li><a href="${context}/contact">Contacts</a></li>
			<li><a href="${context}/about">About</a></li>
			<li><a href="${context}/customer/registration">Register</a></li>
			<li><a href="${context}/customer/login">Login</a></li>
		</ul>
	</nav>
