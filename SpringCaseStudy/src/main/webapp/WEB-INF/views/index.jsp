<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<link type="text/css" rel="stylesheet" href="${context}/static/css/home.css">
		
		<div id="jumbotron" style="background-image: url('${context}/static/images/home/food.jpg')">
			<div id="title_container">
				<h1 id="title">Mummy's Restaurant</h1>
			</div>
		</div>
		
		<section>
			<article>
				<h3>Menu</h3>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>Group</th>
							<th>Description</th>
							<th>Price</th>
						</tr>
					</thead>
						
					<c:forEach items="${foodItems}" var="foodItem" varStatus="i">
					    <tr>  
					        <td>${foodItem.name}</td>
					        <td>${foodItem.foodGroup}</td>
					        <td>${foodItem.description}</td>
					        <td>$${foodItem.price}</td>
					    </tr>
					</c:forEach>
				</table>
			</article>
			
			<article>
				<h3>About</h3>
				<p>
					We have more than 20 years of experience in making delicious traditional dishes, with recipes from the North and the South of India. Our hard work has paid off by winning the recognition of LA Times & Daily News as the best Indian restaurant in Southern California, and being critically acclaimed by AAA Magazine, LA Times and Daily News.
				</p>
		</section>

<%@include file="footer.jsp" %>




