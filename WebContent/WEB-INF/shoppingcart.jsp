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
				int sum = 0;

				for( Book i : fs ) {
				  sum += i.getTotalCost();
				}
		%>
		<table class="table table-hover" style="background-color: white">
			<thead>
				<tr>
					<th>Booking Id</th>
					<th>Date of Booking</th>
					<th>Flight Number</th>
					<th>Number of Seats</th>
					<th>Cost</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cart" items="${requestScope['ticketToBook']}">
					<tr>
						<td><input type="radio" id="${cart.id}"
							name="choosenFlight" value="${cart.id}" required></td>
						<td><c:out value="${cart.bookingId}" /></td>
						<td><c:out value="${cart.dateOfBooking}" /></td>
						<td><c:out value="${cart.flightIds}" /></td>
						<td><c:out value="${cart.numberOfSeats}" /></td>
						<td><c:out value="${cart.totalCost}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table>
			<tbody>
				<td> Total cost </td> <td><%= sum %> </td>
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
	<p style="disply: block">Your shopping cart is empty.</p>

	<input type="button" class="btn" value="Back to Search page"
		onclick="window.history.back();"> <input type="button"
		class="btn" value="See booking history page"
		onclick="window.location='BookingHistory';">
	<%
		}
	%>

</div>

<script>

</script>

<jsp:include page="../WEB-INF/classes/bottom.jsp" />