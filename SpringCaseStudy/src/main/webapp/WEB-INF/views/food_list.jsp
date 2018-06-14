<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@include file="header.jsp" %>
<script>
function displayButton() {
	  var numBoxes = document.getElementsByClassName("numCount");
	  var text = document.getElementById("summary");
	  var count = 0;
	  for (var i = 0, len = numBoxes.length; i < len; i++) {
		  if (numBoxes[i].value != "0"){
			    text.style.display = "block";
			   	count++;
			  }
		}
	  if(count == 0){
		  text.style.display = "none";
	  }
	 
	}
</script>
	<div id="content">

		<h1>Menu</h1>
		
		<form:form action="/order/summary" modelAttribute="menuOrder" method="POST">
			<table>
				<thead>
					<tr>
						<c:choose>
						    <c:when test="${sessionScope.customer != null && sessionScope.customer.id != 0}">
						        <th>Add</th> 
						        <br />
						    </c:when>    
						    <c:otherwise>
						        <th></th> 
						        <br />
						    </c:otherwise>
						</c:choose>
						
						<th>Name</th>
						<th>Group</th>
						<th>Description</th>
						<th>Price</th>
					</tr>
				</thead>
					
				<c:forEach items="${foodItems}" var="foodItem" varStatus="i">
				
			    <tr> 
			    	<td>
			    		<c:if test="${sessionScope.customer != null && sessionScope.customer.id != 0}">
			    		<form:input path="itemCounts[${i.index}]" onClick="displayButton()" class="numCount" 
			    		style="width: 3em" type="number" max="10" min='0' value='0'/>
                        <form:errors path="itemCounts[${i.index}]" cssClass="error"/></td>
                        </c:if>
			    		
			    	</td>   
			        <td>${foodItem.name}</td>
			        <td>${foodItem.foodGroup}</td>
			        <td>${foodItem.description}</td>
			        <td>${foodItem.price}</td>
			        
			        <c:choose>
			        	<c:when test="${fn:substring(foodItem.image, 0, 1) == '&'}">
			        		<td><img src="${fn:substring(foodItem.image, 1, fn:length(foodItem.image))}" width="150vw"></td>
			        	</c:when>
			        	<c:otherwise>
							<td><img src="${context}/static/images/food/${foodItem.image}" width="150vw"></td>
			        	</c:otherwise>
			        </c:choose>
			    </tr>
			    
				</c:forEach>
			</table>
			<c:if test="${sessionScope.customer != null && sessionScope.customer.id != 0}">
				<input id="summary" type="submit" value="Checkout" style="display:none"/>
				<div id="spacer"></div>
			</c:if>
		</form:form>
	</div>
<%@include file="footer.jsp" %>
