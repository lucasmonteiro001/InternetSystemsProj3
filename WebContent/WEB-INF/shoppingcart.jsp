<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Flight"%>
<%@page import="model.Book"%>

<jsp:include page="../WEB-INF/classes/header.jsp" />

<div class="well well-sm span4">
	<h4 style="display: inline">See your selected flights. </h4>

	<br> <br>
	<p style="disply: block">Choose your destiny and when you want to
		go.</p>

	<form name="input" action="FlightSearchResult" method="post"
		role="form">

		<%
			ArrayList <Book> fs = (ArrayList <Book>) session.getAttribute("ticketToBook");
			if (fs.size() != 0) {
		%>
		<table class="table table-hover" style="background-color: white">
			<thead>
				<tr>
					<th>Select</th>
					<th>Date</th>
					<th>From</th>
					<th>To</th>
					<th>Flight Number</th>
					<th>Departure Time</th>
					<th>Arrival Time</th>
					<th>Number of Stops</th>
					<th>Number of seats Economy </th>
					<th>Number of seats First Class </th>
					<th>Number of seats Business </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cart" items="${requestScope['ticketToBook']}">
					<tr>
						<td><input type="radio" id="${cart.id}"
							name="choosenFlight" value="${cart.id}" required></td>
						<td><c:out value="${cart.departure}" /></td>
						<td><c:out value="${cart.source}" /></td>
						<td><c:out value="${cart.destination}" /></td>
						<td><c:out value="${cart.id}" /></td>
						<td><c:out value="${cart.departure}" /></td>
						<td><c:out value="${cart.arrival}" /></td>
						<td><c:out value="${cart.economyClassReserved}" /></td>
						<td><c:out value="${cart.firstClassReserved}" /></td>
						<td><c:out value="${cart.businessClassReserved}" /></td>
						<td>1</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<label for="choosenFlight" class="error" style="display: none">Please
			select one option</label><br> <input type="button" class="btn"
			value="Back to Search page" onclick="window.history.back();">
		<input type="button" class="btn" value="See booking history page"
			onclick="window.location='BookingHistory';"> <input
			type="submit" class="btn btn-primary" name="send"
			value="View and Book" align="right">
	</form>
	<%
		} else {
	%>
	<p style="disply: block">Sorry. We were unable to find any results
		for your search.</p>

	<input type="button" class="btn" value="Back to Search page"
		onclick="window.history.back();"> <input type="button"
		class="btn" value="See booking history page"
		onclick="window.location='BookingHistory';">
	<%
		}
	%>

</div>

<script>
	$("form").validate({
		rules : {
			choosenFlight : {
				required : true
			}
		}
	});
</script>

<jsp:include page="../WEB-INF/classes/bottom.jsp" />