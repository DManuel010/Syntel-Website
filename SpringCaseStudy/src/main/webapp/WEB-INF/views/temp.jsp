<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
	<div id="content">
		<h1>Hello ${customer.first_name}</h1 	>
		<br>
		<h2>My current orders</h2>
		
		<table>
		<tr>
		<td>Food</td>
		<td>Quantity</td>
		<td>Cost</td>
		<td>Expected Date</td>
		<td>Order date</td>
		<td>Status</td>
		</tr>
		
		<c:forEach items="${orders}" var="myOrders">
		<tr>
		<td>${myOrders.name}</td>
		<!-- <td>${myOrders.quantity}</td> -->
		<td>1</td>
		<td>${myOrders.cost}</td>
		<td>${myOrders.expecteddate}</td>
		<td>${myOrders.orderdate}</td>
		<td>${myOrders.status}</td>
		</tr>
		</c:forEach>
		
		</table>
		
		
	</div>
</body>
</html>