<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="super_admin_header.jsp" %>
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
		<h1>Employees</h1>
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
					
				<c:forEach items="${employees}" var="employee">
				
			    <tr> 
			    	<td>${employee.employeeId}</td>
			        <td>${employee.firstName}</td>
			        <td>${employee.lastName}</td>
			        <td>${employee.email}</td>
			        <td>${employee.hireDate}</td>
			        <td>${employee.title}</td>
			        <td>${employee.phoneNumber}</td>
			        <td>${employee.lastLogin}</td>
			    </tr>
				</c:forEach>
			</table>
			<br>
		
		<form method="POST" modelAttribute="employee" action="employees/add">
			<label for="addEmployee">Add Employee</label>
			
			<form:input path="employee.firstName" placeholder="first name" />
			<form:errors path="employee.firstName" />
			
			<form:input path="employee.lastName" placeholder="last name" />
			<form:errors path="employee.lastName" />
			
			<form:input path="employee.email" placeholder="employee@email.com" />
			<form:errors path="employee.email" />
			
			<form:input path="employee.title" placeholder="employee title" />
			<form:errors path="employee.title" />
			
			<form:input path="employee.phoneNumber" placeholder="111-222-3333" />
			<form:errors path="employee.phoneNumber" />
			
			<form:input path="employee.workAddrId" placeholder="# work address id" />
			<form:errors path="employee.workAddrId" />
			
			<form:input path="employee.homeAddrId" placeholder="# home address id" />
			<form:errors path="employee.homeAddrId" />
			
			<form:input path="employee.password" placeholder="password" />
			<form:errors path="employee.password" />
			
			<input type="submit" value="Add Employee" />
			<input type="reset" value="Clear Form" />
		</form>
		
		<form method="POST" modelAttribute="employee" action="employees/delete">
			<label for="deleteFood">Delete Employee</label>
			
			<form:input path="employee.employeeId" placeholder="Employee Id #" />
			<form:errors path="employee.employeeId" />
			
			<input type="submit" value="Delete Employee" />
			<input type="reset" value="Clear Form" />
		</form>

<%@include file="footer.jsp" %>