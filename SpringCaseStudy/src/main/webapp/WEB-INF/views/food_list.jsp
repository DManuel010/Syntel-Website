<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
<script>
function displayButton() {
	  var checkBoxes = document.getElementsByClassName("numCount");
	  var text = document.getElementById("summary");
	  var count = 0;
	  for (var i = 0, len = checkBoxes.length; i < len; i++) {
		  if (checkBoxes[i].value != "0"){
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
						<th>Add</th>
						<th>Name</th>
						<th>Group</th>
						<th>Description</th>
						<th>Price</th>
					</tr>
				</thead>
					
				<c:forEach items="${foodItems}" var="foodItem" varStatus="i">
				
			    <tr> 
			    	<td>
			    		
			    		<form:input path="itemCounts[${i.index}]" onClick="displayButton()" class="numCount" type="number" max="10" value='0' style="width: 3em"/>
                        <form:errors path="itemCounts[${i.index}]" cssClass="error"/></td>
			    		
			    	</td>   
			        <td>${foodItem.name}</td>
			        <td>${foodItem.foodGroup}</td>
			        <td>${foodItem.description}</td>
			        <td>${foodItem.price}</td>
					<td><img src="${context}/static/images/food/${foodItem.image}" width="150vw"></td>
			        
			    </tr>
				</c:forEach>
			</table>
			<c:if test="${sessionScope.customer != null && sessionScope.customer.id != 0}">
				<input id="summary" type="submit" value="Checkout" class="btn btn-danger btn-lg btn-block" style="display:none"/>
				<div id="spacer"></div>
			</c:if>
		</form:form>
	</div>
<%@include file="footer.jsp" %>
