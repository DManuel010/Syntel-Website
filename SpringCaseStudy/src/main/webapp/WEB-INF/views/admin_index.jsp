<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Mummy's Restaurant </title>
	<link rel="stylesheet" type="text/css" href="/css/universal.css">
	<link rel="stylesheet" type="text/css" href="/css/home.css">
	<script src="home.js"></script>
</head>

<body>
	<nav>
	<!-- navbar href paths should be "absolute" (forward slash in front of each link) -->
		<div id="nav_container">
			<a href="/admin">Home </a>
			<a href="/admin/customers">Manage Customers </a>
			<a href="/admin/food">Manage Food </a>
			<a href="/admin/orders">Manage Orders </a>
			<c:choose>
			    <c:when test="${sessionScope.currentUser!=null}">
			        <a href="account">My Account</a>
			        <a href="logout">Logout</a>
			        <!-- Need to have sessions blocking account/logout pages with redirect so that they can't be accessed without logging in -->
			        <br />
			    </c:when>
			    <c:otherwise>
			        <a href="login">Login</a>
					<a href="register">Register</a> 
			        <br />
			    </c:otherwise>
			</c:choose>
		</div>
	</nav>
	
	<c:out value="${sessionScope.employee}"></c:out>

	<div id="main_container">
		<div id="spacer"></div>
		<div id="jumbotron">
			<div id="title_container">
				<h1 id="title">Mummy's Restaurant</h1>
			</div>
		</div>
		
		<section>
			<article>
				<h3>Welcome, ${employee.firstName}</h3>
				<p>Your last login was: ${employee.lastLogin}</p>
			</article>
			
		</section>
	</div>

	<footer>
		<p>123 example st, Phoenix, AZ, 12345</p>
		<p>123-456-7890</p>
	</footer>

</body>
</html>