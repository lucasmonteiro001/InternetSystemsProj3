package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import utilities.AccountDAO;
import utilities.BookingDAO;
import utilities.JsonHelper;
import model.Account;
import model.Book;
import model.Flight;
import model.User;

/**
 * Servlet implementation class ReviewAndBook
 */
public class ReviewAndBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private final double TX_ECONOMY_SEAT = 123.43;
	private final double TX_FIRST_CLASS_SEAT = 550;
	private final double TX_BUSINESS_CLASS_SEAT = 325.56;
	private List ticketToBook;
	private Flight flight;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewAndBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet (HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JsonHelper js = new JsonHelper();
		session = request.getSession();
		Account account = new Account();
		Book book = new Book ();
		


		try {

			String action = request.getParameter("action");
			String json = request.getParameter("json");
			JSONObject jObj = new JSONObject(json);
			if (ticketToBook == null) {
	    		ticketToBook = new ArrayList <Book> ();
	    	}
	    	else {
	    		ticketToBook = (ArrayList<Book>) session.getAttribute ("ticketToBook");
	    	}

			flight = (Flight) session.getAttribute("flightBean");
			int economyClass = (int) session.getAttribute("economyClass");
			int businessClass = (int) session.getAttribute("businessClass");
			int firstClass = (int) session.getAttribute("firstClass");
			double totalCost = economyClass * TX_ECONOMY_SEAT + businessClass
					* TX_BUSINESS_CLASS_SEAT + firstClass * TX_FIRST_CLASS_SEAT;
			User user = (User) session.getAttribute("user");
			book.setNumberOfSeats(economyClass+businessClass+firstClass);
			book.setUserId(user.getId());
			book.setFlightIds(flight.getId());
			book.setTotalCost(totalCost);
			double allCosts = 0;
			Iterator it = ticketToBook.iterator();
			while (it.hasNext()) {
				allCosts += ((Book) it.next()).getTotalCost();
			}
			session.setAttribute("allCosts", allCosts);
			session.setAttribute("totalCost", totalCost);
			ticketToBook.add(book);
		    session.setAttribute("ticketToBook", ticketToBook);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			String msg = js.getJsonFormatted(false,
					"Please, enter a valid number of seats");
			out.print(msg);
		} catch (NullPointerException e) {
			String msg = js.getJsonFormatted(false,
					"Invalid flight information");
			out.print(msg);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }
	


	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();

		flight = (Flight) session.getAttribute("flightBean");
		int economyClass = (int) session.getAttribute("economyClass");
		int businessClass = (int) session.getAttribute("businessClass");
		int firstClass = (int) session.getAttribute("firstClass");

		double totalCost = 0;
		if ((flight.getBusinessClassReserved() - businessClass >= 0)
				&& (flight.getEconomyClassReserved() - economyClass >= 0)
				&& (flight.getFirstClassReserved() - firstClass >= 0)) {

			totalCost = economyClass * TX_ECONOMY_SEAT + businessClass
					* TX_BUSINESS_CLASS_SEAT + firstClass * TX_FIRST_CLASS_SEAT;
			NumberFormat formatter = NumberFormat.getCurrencyInstance();

			session.setAttribute("totalCostFormatted",
					formatter.format(totalCost));
			session.setAttribute("totalCost", totalCost);
			session.setAttribute("totalSeats", firstClass + businessClass
					+ economyClass);

			RequestDispatcher rd = request
					.getRequestDispatcher("WEB-INF/transaction.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request
					.getRequestDispatcher("WEB-INF/reviewandbook.jsp");
			rd.forward(request, response);
		}

	}
}
