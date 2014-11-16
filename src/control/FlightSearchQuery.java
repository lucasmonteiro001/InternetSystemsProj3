package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.FlightSearchDAO;
import model.Flight;
import model.User;

/**
 * Servlet implementation class FlightSearch
 */
public class FlightSearchQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightSearchDAO searchFlightDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightSearchQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * This doPost calls the model Flight and sets the attribute if there is a result from the search. 
	 * Then it redirects for the appropriate page.
	 * 

	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Just can work if a session is there
		HttpSession session = request.getSession();

		ArrayList<Flight> flights = null;
		if (request.getParameter("source") != null
				&& request.getParameter("destination") != null) {
			Flight flight = new Flight();
			session.setAttribute("source", request.getParameter("source"));
			session.setAttribute("destination",
					request.getParameter("destination"));
			flight.setDestination(session.getAttribute("destination")
					.toString());
			flight.setSource(session.getAttribute("source").toString());
			if ((request.getParameter("departure") != null || !request
					.getParameter("departure").toString().equals("MM/DD/YYYY"))) {
				session.setAttribute("departure",
						request.getParameter("departure"));
				searchFlightDao = new FlightSearchDAO();
				System.out.println(session.getAttribute("departure"));
				
				flight.setDeparture(new Date(session.getAttribute("departure").toString()));
				if (request.getParameter("class").equals("economy")) {
					session.setAttribute ("economyClass", Integer.parseInt(request.getParameter("number_of_seats")));
					session.setAttribute("businessClass", 0);
					session.setAttribute("firstClass", 0);
				}
				else if (request.getParameter("class").equals("business")) {
					session.setAttribute ("economyClass", 0);
					session.setAttribute("businessClass", Integer.parseInt(request.getParameter("number_of_seats")));
					session.setAttribute("firstClass", 0);
				}
				else if (request.getParameter("class").equals("firstclass")) {
					session.setAttribute ("economyClass", 0);
					session.setAttribute("businessClass", 0);
					session.setAttribute("firstClass", Integer.parseInt(request.getParameter("number_of_seats")));
					
				}
				flights = searchFlightDao.readFlight(flight);
				request.setAttribute("flights", flights);
				session.setAttribute("flights", flights);
				

			}

			RequestDispatcher rd = request
					.getRequestDispatcher("WEB-INF/flightsearchresult.jsp");
			rd.forward(request, response);
		}

	}
}
