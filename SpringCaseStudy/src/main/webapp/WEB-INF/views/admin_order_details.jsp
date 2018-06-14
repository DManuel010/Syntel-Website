<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Detail</title>
</head>
<body>
		<h1>Order Details</h1>
		
		<div>
			<p><b>Order ID:</b>${orderid}</p>
		</div>
		
		<table>
			<thead>
			<tr>
				<th>Order ID:</th>
				<th>Name:</th>
				<th>Description:</th>
				<th>Group:</th>
				<th>Price:</th>
				<th>Qty:</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${details}" var="detail">
			 <tr>
			 	<td>${detail.getFood().getName()}</td>
			 	<td>${detail.getFood().getDescription()}</td>
			 	<td>${detail.getFood().getFoodGroup()}</td>
			 	<td>${detail.getFood().getPrice()}</td>
			 	<td>${detail.getFoodOrder().getQuantity()}</td>
			 <tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div>
		<p><b>Instructions</b>${instructions}</p>
		</div>
</body>
</html>