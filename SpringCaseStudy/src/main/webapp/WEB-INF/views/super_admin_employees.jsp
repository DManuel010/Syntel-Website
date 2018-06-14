<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="admin_header.jsp" %>
<link type="text/css" rel="stylesheet" href="${context}/static/css/home.css">
<style>
<!--
table{
   overflow-y:scroll;
   height:300px;
   display:block;
}
-->
</style>
		<h1>Food Menu</h1>
			<table cellpadding="10px">
				<thead>
					<tr>
						<th>Employee ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Hire Date</th>
						<th>Title</th>
						<th>Phone Number</th>
						<th>Last Login</th>
					</tr>
				</thead>
					
				<c:forEach items="${empItems}" var="foodItem">
				
			    <tr> 
			    	<td>${empItems.employeeId}</td>
			        <td>${empItems.firstName}</td>
			        <td>${empItems.lastName}</td>
			        <td>${empItems.email}</td>
			        <td>${empItems.hireDate}</td>
			        <td>${empItems.title}</td>
			        <td>${empItems.phoneNumber}</td>
			        <td>${empItems.lastLogin}</td>
			    </tr>
				</c:forEach>
			</table>
			<br>
		
		<form method="POST" modelAttribute="food" action="food/add">
			<label for="addFood">Add Food</label>
			
			<form:input path="food.name" placeholder="food name" />
			<form:errors path="food.name" />
			
			<form:input path="food.foodGroup" placeholder="food group" />
			<form:errors path="food.foodGroup" />
			
			<form:input path="food.price" placeholder="price" />
			<form:errors path="food.price" />
			
			<form:input path="food.description" placeholder="description" />
			<form:errors path="food.description" />
			
			<form:input path="food.stock" placeholder="stock" />
			<form:errors path="food.stock" />
			
			<form:input path="food.image" placeholder="image/url" />
			<form:errors path="food.image" />
			
			<input type="submit" value="Add Food" />
			<input type="reset" value="Clear Form" />
		</form>
		
		<form method="POST" modelAttribute="food" action="food/activate">
			<label for="activateFood">Activate Food</label>
			
			<form:input path="food.foodId" placeholder="Food ID #" />
			<form:errors path="food.foodId" />
			
			<input type="submit" value="Activate Food" />
			<input type="reset" value="Clear Form" />
		</form>
		
		<form method="POST" modelAttribute="food" action="food/deactivate">
			<label for="deactivateFood">Deactivate Food</label>
			
			<form:input path="food.foodId" placeholder="Food ID #" />
			<form:errors path="food.foodId" />
			
			<input type="submit" value="Deactivate Food" />
			<input type="reset" value="Clear Form" />
		</form>

<%@include file="footer.jsp" %>