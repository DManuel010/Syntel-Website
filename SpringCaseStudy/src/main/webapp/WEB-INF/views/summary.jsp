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

<c:out value="${sessionScope.customer}"/>

<h1>In Checkout</h1>

<form action="/location/delivery" method="POST">
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Group</th>
				<th>Description</th>
				<th>Price</th>
				<th>Qty</th>
			</tr>
		</thead>
		<c:forEach items="${sessionScope.selectedItems}" var="selectedItem">
			<tr>   
		        <td>${selectedItem.name}</td>
		        <td>${selectedItem.foodGroup}</td>
		        <td>${selectedItem.description}</td>
		        <td>${selectedItem.price}</td>
		        <td><input id="number" type="number" min="1" maxlength="10" value="1"/></td>
	    	</tr>
		</c:forEach>
	</table>
	
	<label for="instructions">Instructions:</label>
	<br>
	<textarea name="instructions" rows="10" cols="20"></textarea>
	<br>
	<label for="expectedDate">Expected Date:</label>
	<input name="expectedDate" type="text" />
	<br>
	<fieldset>
			<legend>Payment</legend>
			<label for="paymentType"></label>
			<select name="paymentType">
				<option value="0">Paypal</option>
				<option value="1">Card</option>
				<option value="2">Cash</option>
			</select>
		</fieldset>
	<input type="submit" value="Continue"/>
</form>
</body>
</html>	