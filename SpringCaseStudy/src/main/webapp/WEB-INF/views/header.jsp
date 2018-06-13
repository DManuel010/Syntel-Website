<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.syntinel.model.Customer" %>



<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mummy's Restaurant - Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${context}/static/libraries/bootstrap.min.css">
	<c:set var="context"  value="${pageContext.request.contextPath}" />
	<link type="text/css" rel="stylesheet" href="${context}/static/css/universal.css">
	<link type="text/css" rel="stylesheet" href="${context}/static/css/header.css">
</head>
<body>
	<nav>
		<a href="${context}/" class="active">
			<img id="logo"src="${context}/static/images/global/logo.png" alt="logo">
		</a>
		<div id="nav_container">
			<ul>
				<li><a href="${context}/" class="active">Home</a></li>
				<li><a href="${context}/order/food">Menu</a></li>
				<c:if test="${sessionScope.customer != null && sessionScope.customer.id != 0}">
					<li><a href="${context}/customer/dashboard">Order</a></li>
					<li><a href="${context}/customer/logout">Log Out</a></li>
				</c:if>
				<li><a href="${context}/info/contact">Contact</a></li>
				<li><a href="${context}/info/about">About</a></li>
				<c:if test="${sessionScope.customer == null || sessionScope.customer.id == 0}">
					<li><a href="${context}/customer/registration">Register</a></li>
					<li><a href="${context}/customer/login">Login</a></li>
				</c:if>
			</ul>
			<div id="spacer"></div>
		</div>
	</nav>
	<div id="main_container">
	
