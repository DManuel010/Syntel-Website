<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="admin_header.jsp" %>
<link type="text/css" rel="stylesheet" href="${context}/static/css/home.css">


		<h1>Order Details</h1>
		
		
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
			 	<td>${orderid}</td>
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
		<p><b>Instructions: </b>${instructions}</p>
		</div>

<%@include file="footer.jsp" %>