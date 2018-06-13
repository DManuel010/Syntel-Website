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
			<a href="menu">Menu </a>
			<a href="about">About </a>
			<a href="contact">Contact </a>
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
	
	<div id="main_container">
		<div id="spacer"></div>
		<div id="jumbotron">
			<div id="title_container">
				<h1 id="title">Mummy's Restaurant</h1>
			</div>
		</div>
		
		<section>
			
			<table>
				<thead>
					<tr>
						<th>Customer ID</th>
						<th>First name</th>
						<th>Last name</th>
						<th>Email</th>
						<th>Home Number</th>
						<th>Mobile Number</th>
						<th>DOB</th>
						<th>Register Date</th>
						<th>Last Login</th>
					</tr>
				</thead>
					
				<c:forEach items="${customers}" var="customer">
		
			    <tr> 
			   		<td>${customer.id}</td>
			        <td>${customer.first_name}</td>
			        <td>${customer.last_name}</td>
			       	<td>${customer.email}</td>
			        <td>${customer.home_number}</td>
			        <td>${customer.mobile_number}</td>
			        <td>${customer.dob}</td>
			        <td>${customer.register_date}</td>
			        <td>${customer.last_login}</td>
			    </tr>
				</c:forEach>
			</table>
			
		</section>
	</div>

	<footer>
		<p>123 example st, Phoenix, AZ, 12345</p>
		<p>123-456-7890</p>
	</footer>

</body>
</html>