<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="admin_header.jsp" %>    
   

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<link type="text/css" rel="stylesheet" href="${context}/static/css/home.css">


<%@include file="admin_header.jsp" %>
	<div id="content">

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
				<td>
				<a href="<c:url value="/admin/orders/detail/">
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
	</div>
<%@include file="footer.jsp" %>

