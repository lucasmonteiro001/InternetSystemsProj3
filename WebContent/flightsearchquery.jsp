<%
 	if (session.getAttribute("user") == null) {
 		response.sendRedirect("LoginError");
 	}
 %>

<%
//Airline iata codes
String[] airlines = {"AA", "DL", "UA", "WN", "B6", "AS", "NK", "F9"};

//airport iata codes
String[] airports = {"ATL", "ANC", "AUS", "BWI", "BOS", 
		"CLT", "MDW", "ORD", "CVG", "CLE", "CMH", "DFW", 
		"DEN", "DTW", "FLL", "RSW", "BDL", "HNL", "IAH", 
		"HOU", "IND", "MCI", "LAS", "LAX", "MEM", "MIA", 
		"MSP", "BNA", "MSY", "JFK", "LGA", "EWR", "OAK", 
		"ONT", "MCO", "PHL", "PHX", "PIT", "PDX", "RDU", 
		"SMF", "SLC", "SAT", "SAN", "SFO", "SJC", "SNA", 
		"SEA", "STL", "TPA", "IAD", "DCA"}; 
%>

<jsp:include page="WEB-INF/classes/header.jsp"/>

<script type="text/javascript" src="js/masked_input_1.3.js"></script>

<div class="well well-sm span4">
    <h4>Details of your travel: </h4>
	<form class="form-horizontal form-group-sm" role="form" name="input" action="FlightSearchQuery" method="post" id="input">
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="source">Source</label>
  			<select name="source" form="input" id="source" required>
  				<option value=""> </option>
  				<% for (int i = 0; i < airports.length ; i++) {%>
  					<option value=<%=airports[i]%> ><%=airports[i]%></option>
  				<% } %>
  			</select>
		</div>
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="destination">Destination</label>
  			<select name="destination" form="input" id="destination" required>
  				<option value=""> </option>
  				<% for (int i = 0; i < airports.length ; i++) {%>
  					<option value=<%=airports[i]%> ><%=airports[i]%></option>
  				<% } %>
  			</select>
		</div>
		<div class="form-group">
  		<label class="col-sm-2 control-label" for="departure">Departure</label>
  			<div class="col-sm-10">
  				<input type="textfield" class="form-group" id="departure" name="departure" required>
  			</div>
		</div>
		<div class="form-group">
  			<label class="col-sm-2 control-label" for="class">Class</label>
  			<div class="col-sm-10">
				<select class="form-group" id="class" name="class">
		    		<option value="economy">Economy</option>
		    		<option value="business">Business</option>
		    		<option value="firstclass">First Class</option>
		    	</select>
		    </div>
    	</div>
    	<button type="submit" class="btn btn-primary btn-sm col-md-offset-2" value="Search">Search</button>
		<button type="button" class="btn btn-danger btn-sm" value="Exit" onClick="window.location='login.jsp'">Exit</button>
	</form>    
</div>

<script>
	$("form").validate({
		rules: {
		    number_of_seats: {
		      	required: true
		    },
			source: {
				required: true
			},
			destination: {
				required: true
			},
			departure: {
				required: true,
				date: true
			}
		  }
	});
</script>

<script>
	MaskedInput({
		  elm: document.getElementById('departure'),
		  format: 'MM/DD/YYYY',
		  separator: '\/',
		  typeon: 'MDY'
		});
	MaskedInput({
		  elm: document.getElementById('arrival'),
		  format: 'MM/DD/YYYY',
		  separator: '\/',
		  typeon: 'MDY'
		});
</script>

<jsp:include page="WEB-INF/classes/bottom.jsp"/>

