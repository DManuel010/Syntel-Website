<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
	<div id="content">
		<h1>Food Menu</h1>
		
		<form action="/order/summary" method="POST">
			<table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Group</th>
						<th>Description</th>
						<th>Price</th>
						<th>Stock</th>
					</tr>
				</thead>
					
				<c:forEach items="${foodItems}" var="foodItem">
				
			    <tr> 
			        <td>${foodItem.name}</td>
			        <td>${foodItem.foodGroup}</td>
			        <td>${foodItem.description}</td>
			        <td>${foodItem.price}</td>
			        <td>${foodItem.stock}</td>
			    </tr>
				</c:forEach>
			</table>
			
		
		<form method="POST" modelAttribute="food" action="admin/food/add">
			<label for="addFood">Add Food</label>
			
			<form:input path="food.name" placeholder="Enter food name" />
			<form:errors path="food.name" />
			
			<form:input path="food.foodGroup" placeholder="Enter food group" />
			<form:errors path="food.foodGroup" />
			
			<form:input path="food.price" placeholder="Enter price" />
			<form:errors path="food.price" />
			
			<form:input path="food.description" placeholder="Enter description" />
			<form:errors path="food.description" />
			
			<form:input path="food.stock" placeholder="Enter quantity in stock" />
			<form:errors path="food.stock" />
			
			<input type="submit" value="Add Food" />
			<input type="reset" value="Clear Form" />
		</form>
		
		<form method="POST" modelAttribute="food" action="admin/food/delete">
			<label for="deleteFood">Delete Food</label>
			
			<form:input path="food.foodId" placeholder="Enter Food ID #" />
			<form:errors path="food.foodId" />
			
			<input type="submit" value="Delete Food" />
			<input type="reset" value="Clear Form" />
		</form>
		
	</div>
</html>