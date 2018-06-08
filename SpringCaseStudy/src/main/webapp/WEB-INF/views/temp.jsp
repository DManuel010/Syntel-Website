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
		<td>Cost	</td>
		<td>Order date	</td>
		<td>Expected Date	</td>
		<td>Status	</td>
		</tr>
		<c:forEach items="${orders}" var="orders">
		<tr>
			<td>${orders.cost}</td>
			<td>${orders.orderDate}</td>
			<td>${orders.expectedDate}</td>
			<td>Pending</td>
		</tr>
		
		</c:forEach>
		</table>
		
		
	</div>
</body>
</html>