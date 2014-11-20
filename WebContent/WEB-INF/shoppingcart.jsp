<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Flight"%>
<%@page import="model.Book"%>

<jsp:include page="../WEB-INF/classes/header.jsp" />


<div class="well well-sm span4">
	<h4 style="display: inline">Shopping Cart</h4>

	<br> <br>
	<p style="disply: block">Flights you have chosen.</p>

	<%
		double aggregateCost = 0;	
		ArrayList<Book> fs = (ArrayList<Book>) session
				.getAttribute("shoppingCart");
		if (fs != null) {
	%>
	<table class="table table-hover" style="background-color: white">
		<thead>
			<tr>
				<th>Flight</th>
				<th>Seats</th>
				<th>Costs</th>
			</tr>

			<%
				Iterator<Book> it = fs.iterator();
					while (it.hasNext()) {
						Book book = (Book) it.next();
						%>
			<tr>
				<td> <%book.getFlightIds(); %> </td>
				<td> <%book.getNumberOfSeats(); %></td>
				<td> <%book.getTotalCost(); %> </td>
			</tr>			
						<%  
					 aggregateCost += book.getTotalCost();
					}
				} else {
			%>
				

			<%
				}
			%>
			<jsp:include page="../WEB-INF/classes/bottom.jsp" />