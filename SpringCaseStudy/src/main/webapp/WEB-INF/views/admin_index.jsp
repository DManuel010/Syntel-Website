
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_header.jsp" %>
<link type="text/css" rel="stylesheet" href="${context}/static/css/home.css">


		<div id="jumbotron" style="background-image: url('${context}/static/images/home/food.jpg')">
			<div id="title_container">
				<h1 id="title">Mummy's Restaurant</h1>
			</div>
		</div>

		
		<section>
			<article>
				<h4>Welcome, ${sessionScope.employee.firstName}</h4>
				<p>Your last login was: ${sessionScope.employee.lastLogin}</p>
			</article>
			
		</section>

<%@include file="footer.jsp" %>




