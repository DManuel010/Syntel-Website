<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Orders</title>
</head>
<body>

<h1>View Orders</h1>
	<table>
		<thead>
		<tr>
			<th>Order ID:</th>
			<th>Total:</th>
			<th>Expected Date:</th>
			<th>Delivery Date:</th>
			<th>First Name:</th>
			<th>Last Name:</th>
			<th>Email:</th>
			<th>Paid With</th>
		</tr>
		</thead>
		<c:forEach items="${orders}" var="order">
		 <tr>
		 	
			<td>
				<a href="<c:url value="/admin/orders/detail">
					<c:param name="orderid" value="${order.getOrder().getOrderId()}" />
				</c:url>">${order.getOrder().getOrderId()}</a>
			</td>
			<td>${order.getOrder().getCost()}</td>
			<td>${order.getOrder().getExpectedDate()}</td>
			<td>${order.getOrder().getDeliveryDate()}</td>
			<td>${order.getCustomer().getFirst_name()}</td>
			<td>${order.getCustomer().getLast_name()}</td>
			<td>${order.getCustomer().getEmail()}</td>
			<td>${order.getPayment_name()}</td>
		 <tr>
		</c:forEach>
		<tbody>
		</tbody>
	</table>
</body>
</html>