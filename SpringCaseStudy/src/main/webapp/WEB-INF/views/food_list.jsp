<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Food List</h1>
	
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Group</th>
				<th>Description</th>
				<th>Price</th>
			</tr>
		</thead>
		<c:forEach items="${foodItems}" var="foodItem">
	    <tr>      
	        <td>${foodItem.name}</td>
	        <td>${foodItem.foodGroup}</td>
	        <td>${foodItem.description}</td>
	        <td>${foodItem.price}</td>
	    </tr>
		</c:forEach>
	</table>

</body>
</html>