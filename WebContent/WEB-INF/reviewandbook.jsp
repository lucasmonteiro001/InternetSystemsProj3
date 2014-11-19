<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="flightBean" class="model.Flight" scope="session" />

<jsp:include page="../WEB-INF/classes/header.jsp" />
<form name="input" action="ReviewAndBook" method="post" role="form">
	<h3>You have choosen this flight</h3>
	<div class="well well-sm span4">
		<table class="table table-hover" style="background-color: white">
			<thead>
				<tr>
					<th>Date</th>
					<th>From</th>
					<th>To</th>
					<th>Flight Number</th>
					<th>Departure Time</th>
					<th>Arrival Time</th>
					<th>Number of Stops</th>
					<th>Cost</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<tr>
					<td><jsp:getProperty property="departure" name="flightBean" /></td>
					<td><jsp:getProperty property="source" name="flightBean" /></td>
					<td><jsp:getProperty property="destination" name="flightBean" /></td>
					<td><jsp:getProperty property="id" name="flightBean" /></td>
					<td><jsp:getProperty property="departure" name="flightBean" /></td>
					<td><jsp:getProperty property="arrival" name="flightBean" /></td>
					
					</div>

				</tr>
				</tr>
			</tbody>
		</table>
		<br>
	</div>
	<input type="button" class="btn btn-sm" value="Back to see the flights"
		onclick="window.history.back();"> <input type="button"
		class="btn btn-sm" value="Add to the cart"
		onclick="AJAX_FUNCTION_ADD_TO_CART TODO;"> <input
		type="submit" class="btn btn-primary btn-sm" name="send"
		value="Confirm" align="right">
</form>
<script>

	 $("form").submit(function (event) {
		
		var flightId 		= $("#flightBean.id").val();
		
		var jsonDataObject = new Object();
		jsonDataObject.flightId 		= flightId;
		
		var jsonData = JSON.stringify(jsonDataObject);

		
		$.ajax({
			url : "ReviewAndBook",
			type : "GET",
			data : {action:"export",json:jsonData},
			contentType: 'application/json',
			cache: false,
			success: function(data) {
				if(data.success == "false")
			    	error(data.data);
				else {
					clearError();
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			}
			
		});
		
		event.preventDefault();
	});
	
	function clearError() {
		$(".error").hide();
	};
	 
	function error(msg) {
		$(".error").html(msg).show();
	};
	
</script>
<jsp:include page="../WEB-INF/classes/bottom.jsp" />