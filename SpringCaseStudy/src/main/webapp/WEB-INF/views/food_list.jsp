<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
	<div id="content">

		<h1>Food List</h1>
		
		<form action="/order/summary" method="POST">
			<table>
				<thead>
					<tr>
						<th>Add</th>
						<th>Name</th>
						<th>Group</th>
						<th>Description</th>
						<th>Price</th>
					</tr>
				</thead>
					
				<c:forEach items="${foodItems}" var="foodItem">
				
			    <tr> 
			    	<td>
			    		<input name="foodItemChkbx" type="checkbox" value="${foodItem.foodId}" />
			    	</td>   
			        <td>${foodItem.name}</td>
			        <td>${foodItem.foodGroup}</td>
			        <td>${foodItem.description}</td>
			        <td>${foodItem.price}</td>
			    </tr>
				</c:forEach>
			</table>
			
			<input type="submit" value="Summary"/>
		</form>
	</div>
</body>
</html>