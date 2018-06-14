<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="admin_header.jsp" %>
<link type="text/css" rel="stylesheet" href="${context}/static/css/home.css">

	<h1>Orders</h1>
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
				<td>${order.getOrder().getOrderId()}</td>
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

<%@include file="footer.jsp" %>