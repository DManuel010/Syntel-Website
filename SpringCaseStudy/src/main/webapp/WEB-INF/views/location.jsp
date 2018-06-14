<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
	<div id="content">

		<h1>Insert your address</h1>
		
		<form method="POST" modelAttribute="location" action="/location/added">
		
		<label for="location_country">Country</label>
			<form:input class="form-control" path="location.country" placeholder="Enter your country"/>
			<form:errors path="location.country"/>
			<br/>
			
		<label for="location_state">State</label>
			<form:input class="form-control" path="location.state" placeholder="Enter your state"/>
			<form:errors path="location.state"/>
			<br/>
		
		<label for="location_city">City</label>
			<form:input class="form-control" path="location.city" placeholder="Enter your city"/>
			<form:errors path="location.city"/>
			<br/>
			
		<label for="location_street_number">Address</label>
			<form:input class="form-control" path="location.street_number" placeholder="Enter your address"/>
			<form:errors path="location.street_number"/>
			<br/>
			
		<label for="location_room_number">Room or apartment number (optional)</label>
			<form:input class="form-control" path="location.room_number" placeholder="Enter your room #"/>
			<br/>
			
		<label for="location_zip_code">Zip Code</label>
			<form:input class="form-control" path="location.zip_code" placeholder="Enter your zip code"/>
			<form:errors path="location.zip_code"/>
			<br/>
			
			<input class="btn btn-danger btn-lg btn-block" type="submit" value="Confirm address" />
			<input class="btn btn-danger btn-lg btn-block" type="reset" value="Clear Form" />
		
		</form>
	</div>
<%@include file="footer.jsp" %>
