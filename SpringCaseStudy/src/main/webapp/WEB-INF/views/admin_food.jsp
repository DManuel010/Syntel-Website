<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="admin_header.jsp" %>
<style>
<!--
table{
   overflow-y:scroll;
   height:300px;
   display:block;
}

-->
</style>
	<div id="content">
		<h1>Food Menu</h1>
			<table cellpadding="10px">
				<thead>
					<tr>
						<th>Food ID</th>
						<th>Name</th>
						<th>Group</th>
						<th>Description</th>
						<th>Price</th>
						<th>Stock</th>
						<th>Active</th>
					</tr>
				</thead>
					
				<c:forEach items="${foodItems}" var="foodItem">
				
			    <tr> 
			    	<td>${foodItem.foodId}</td>
			        <td>${foodItem.name}</td>
			        <td>${foodItem.foodGroup}</td>
			        <td>${foodItem.description}</td>
			        <td>${foodItem.price}</td>
			        <td>${foodItem.stock}</td>
			        <td>${foodItem.active}</td>
			    </tr>
				</c:forEach>
			</table>
			<br>
		
		<form method="POST" modelAttribute="food" action="food/add">
			<label for="addFood">Add Food</label>
			
			<form:input class="form-control" path="food.name" placeholder="food name" />
			<form:errors path="food.name" />
			
			<form:input class="form-control" path="food.foodGroup" placeholder="food group" />
			<form:errors path="food.foodGroup" />
			
			<form:input class="form-control" path="food.price" placeholder="price" />
			<form:errors path="food.price" />
			
			<form:input class="form-control" path="food.description" placeholder="description" />
			<form:errors path="food.description" />
			
			<form:input class="form-control" path="food.stock" placeholder="stock" />
			<form:errors path="food.stock" />
			
			<form:input class="form-control" path="food.image" placeholder="image/url" />
			<form:errors path="food.image" />
			
			External URL: <form:checkbox path="food.externalURL" />
			
			<input class="btn btn-danger btn-lg" type="submit" value="Add Food" />
			<input class="btn btn-danger btn-lg" type="reset" value="Clear Form" />
		</form>
		
		<br>
		
		<form method="POST" modelAttribute="food" action="food/activate">
			<label for="activateFood">Activate Food</label>
			
			<form:input class="form-control" path="food.foodId" placeholder="Food ID #" />
			<form:errors path="food.foodId" />
			
			<input class="btn btn-danger btn-lg" type="submit" value="Activate Food" />
			<input class="btn btn-danger btn-lg" type="reset" value="Clear Form" />
		</form>
		
		<br>
		
		<form method="POST" modelAttribute="food" action="food/deactivate">
			<label for="deactivateFood">Deactivate Food</label>
			
			<form:input class="form-control" path="food.foodId" placeholder="Food ID #" />
			<form:errors path="food.foodId" />
			
			<input class="btn btn-danger btn-lg" type="submit" value="Deactivate Food" />
			<input class="btn btn-danger btn-lg" type="reset" value="Clear Form" />
		</form>
	</div>
	<div id=spacer"></div>
<%@include file="footer.jsp" %>