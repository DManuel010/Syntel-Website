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
		
		<form method="POST" modelAttribute="emp" action="employees/add">
			<label for="addEmployee">Add Employee</label>
			
			<form:input path="emp.firstName" placeholder="first name" />
			<form:errors path="emp.firstName" />
			
			<form:input path="emp.lastName" placeholder="last name" />
			<form:errors path="emp.lastName" />
			
			<form:input path="emp.email" placeholder="employee@email.com" />
			<form:errors path="emp.email" />
			
			<form:input path="emp.title" placeholder="employee title" />
			<form:errors path="emp.title" />
			
			<form:input path="emp.phoneNumber" placeholder="Phone Number (111-222-3333)" />
			<form:errors path="emp.phoneNumber" />
			
			<form:input path="emp.workAddrId" placeholder="# work address id" />
			<form:errors path="emp.workAddrId" />
			
			<form:input path="emp.homeAddrId" placeholder="# home address id" />
			<form:errors path="emp.homeAddrId" />
			
			<form:input path="emp.password" placeholder="password" />
			<form:errors path="emp.password" />
			
			<input type="submit" value="Add Employee" />
			<input type="reset" value="Clear Form" />
		</form>
		
		<form method="POST" modelAttribute="emp" action="employees/delete">
			<label for="deleteFood">Delete Employee</label>
			
			<form:input path="emp.employeeId" placeholder="Employee Id #" />
			<form:errors path="emp.employeeId" />
			
			<input type="submit" value="Delete Employee" />
			<input type="reset" value="Clear Form" />
		</form>

<%@include file="footer.jsp" %>