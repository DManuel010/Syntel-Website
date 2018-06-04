<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Login successfully</h1>
	<h2>hello ${customer.first_name}</h2>
	
	<c:out value="${sessionScope.customer}"/>
	
	<a href="/order/food">Order Food</a>
	
</body>
</html>