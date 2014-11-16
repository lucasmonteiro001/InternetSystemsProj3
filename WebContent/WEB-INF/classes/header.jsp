<%@page import="utilities.PageUtilities"%>
<%@page import="java.util.*"%>
<%@ include file="head.jsp"%>

<% if (session.getAttribute("user") != null) { %>
	<jsp:useBean id="user" class="model.User" scope="session" />
<% } %>

<% PageUtilities pg = new PageUtilities(request); %>

<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">

				<div class="navbar-header">
					<a class="navbar-brand" href="#"><b>Air Minas</b></a>
				</div>


				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#"> 
						<% if (pg.getSystemPages().get(pg.getCurrPage()) != null) { %> 
							<%=pg.getSystemPages().get(pg.getCurrPage())%> 
						<% } %>
						</a></li>
						<!-- Check if there's a session -->
						<%
							if (session.getAttribute("user") != null
									&& !pg.getCurrPage().equals("bookinghistory.jsp")
									&& !pg.getCurrPage().equals("login.jsp")
									&& !pg.getCurrPage().equals("registration.jsp")) {
						%>
						<li><a href="BookingHistory"> Booking history </a></li>
						<%
							}
						%>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><img class="logo" src="style/airplane.png"></li>
						<li><img class="logo" src="style/background-mg.png"></li>
						<!-- Check if there's a session -->
						<%
							if (session.getAttribute("user") != null
									&& !pg.getCurrPage().equals("login.jsp")
									&& !pg.getCurrPage().equals("registration.jsp")) {
						%>
						<li class="divider-vertical"></li>
						<li><a href="Logout"><span
								class="glyphicon glyphicon-off black">&nbsp</span> <jsp:getProperty
									property="email" name="user" /> </a></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</nav>