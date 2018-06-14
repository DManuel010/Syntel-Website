<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
	<div id="content">
		<h1>Hello ${customer.first_name}</h1 	>
		<br>
		<h2>My current orders</h2>
		
		<div style="overflow-x:auto;">
			<table>
				<tr>
					<td>Date</td>
					<td>Expected Date</td>
					<td>Items</td>
					<td>Total Price</td>
					<td>Status</td>
				</tr>
				
				<c:forEach items="${orders}" var="order">
					<tr>
						<!--<td>${myOrders.name}</td>-->
						<!-- <td>${myOrders.quantity}</td> -->
						
						<td>${order.orderDate}</td>
						<td>${order.expectedDate}</td>
						
						<td>
							<table>
								<c:forEach items="${order.foodOrders}" var="myFoodOrders">
									<tr>
										<td>${myFoodOrders.cost}</td>
										<td>${myFoodOrders.name}</td>
										<td>${myFoodOrders.quantity}</td>
									</tr>
								</c:forEach>
							</table>
						</td>

						
						<td>${order.cost}</td>
						<td>TODO</td>
						
						<!-- <td>${myOrders.status}</td> -->
					</tr>
				</c:forEach>
			
			</table>
		</div>
		
		
	</div>
<%@include file="footer.jsp" %>
