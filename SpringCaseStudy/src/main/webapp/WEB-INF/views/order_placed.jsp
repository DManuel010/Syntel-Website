<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@include file="header.jsp" %>
	<div id="content">

		<c:out value="${sessionScope.customer}"/>
			<h1>You have now successfully placed an order</h1>
	</div>
</body>
</html>