<jsp:include page="../WEB-INF/classes/header.jsp"/>
<form class="form-horizontal" role="form" name="input" action="Transaction" method="post">
<jsp:useBean id="flightBean" class="model.Flight" scope="session" />
<h3>Confirmation</h3>
<div class="well well-sm span4">

	<table class="table table-hover" style="background-color:white">
			<thead>
				<tr>
					<th> Date </th>
					<th> From </th>
					<th> To </th>
					<th> Flight Number </th>
					<th> Departure Time </th>
					<th> Arrival Time </th>
					<th> Number of Stops </th>
					<th> Cost </th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><jsp:getProperty property="departure" name="flightBean" /></td>
					<td><jsp:getProperty property="source" name="flightBean" /></td>
					<td><jsp:getProperty property="destination" name="flightBean" /></td>
					<td><jsp:getProperty property="id" name="flightBean" /></td>
					<td><jsp:getProperty property="departure" name="flightBean" /></td>
					<td><jsp:getProperty property="arrival" name="flightBean" /></td>
					<td> 1 </td>
					<td> <%= session.getAttribute("totalCostFormatted") %></td>

				</tr>
			</tbody>
		</table><br>
</div>

<h3>Account information</h3>
<div class="well well-sm span4">	
			<div class="form-group">
				<label class="col-sm-2 control-label" for="accHolderName">Account holder name</label>
				<div class="col-sm-10">
					<input type="textfield" class="form-group" id="accountHolderId" placeholder="Account holder name" name="accountHolderId">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="routNumber">Routing number</label>
				<div class="col-sm-10">
					<input type="textfield" class="form-group" id="accountRoutingNumber" placeholder="Routing number" name="accountRoutingNumber" data-mask="0000000000">
				</div>
			</div>

			<input class="col-sm-offset-2 btn btn-primary btn-sm" type="submit" value="Submit">
		</form>
</div>

<script type="text/javascript" src="js/mask/jquery.mask.js"></script>
<script type="text/javascript" src="js/masked_input_1.3.js"></script>

<script>
	$("form").validate({
		rules: {
			accountHolderId: {
		      	required: true
		    },
		    accountRoutingNumber: {
		    	required: true
		    }
		    
		  }
	});
</script>
<jsp:include page="../WEB-INF/classes/bottom.jsp"/>