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
	
	<c:forEach items="${foodItems}" var="foodItem">
    <tr>      
        <td>${foodItem}</td>
    </tr>
</c:forEach>

</table>


<!-- <td>${foodItem.location}</td>
        <td>${foodItem.startDate}</td>
        <td>${foodItem.endDate}</td> -->
</body>
</html>