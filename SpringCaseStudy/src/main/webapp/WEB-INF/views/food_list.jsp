<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>
<script>
function displayButton() {
	  var checkBoxes = document.getElementsByClassName("chkbx");
	  var text = document.getElementById("summary");
	  var count = 0;
	  for (var i = 0, len = checkBoxes.length; i < len; i++) {
		  if (checkBoxes[i].checked == true){
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
		
		<form action="/order/summary" method="POST">
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
					
				<c:forEach items="${foodItems}" var="foodItem">
				
			    <tr> 
			    	<td>
			    		<input class="chkbx" name="foodItemChkbx" type="checkbox" value="${foodItem.foodId}" onClick="displayButton()")/>
			    	</td>   
			        <td>${foodItem.name}</td>
			        <td>${foodItem.foodGroup}</td>
			        <td>${foodItem.description}</td>
			        <td>${foodItem.price}</td>
					<td><img src="${context}/static/images/food/${foodItem.image}" width="350"></td>
			        
			    </tr>
				</c:forEach>
			</table>
			<c:if test="${sessionScope.customer != null && sessionScope.customer.id != 0}">
				<input id="summary" type="submit" value="Checkout" style="display:none"/>
			</c:if>
		</form>
	</div>
<%@include file="footer.jsp" %>
