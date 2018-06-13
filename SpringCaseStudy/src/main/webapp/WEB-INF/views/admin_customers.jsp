<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_header.jsp" %>
<link type="text/css" rel="stylesheet" href="${context}/static/css/home.css">
	
		<h1> Customers </h1>
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
		
<%@include file="footer.jsp" %>