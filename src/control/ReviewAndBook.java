package control;

import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Flight;

/**
 * Servlet implementation class ReviewAndBook
 */
public class ReviewAndBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session;
    private final double TX_ECONOMY_SEAT = 123.43;
    private final double TX_FIRST_CLASS_SEAT = 550;
    private final double TX_BUSINESS_CLASS_SEAT = 325.56;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewAndBook() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		Flight flight = (Flight) session.getAttribute("flightBean");
		int economyClass = (int) session.getAttribute("economyClass");
		int businessClass = (int) session.getAttribute("businessClass");
		int firstClass = (int) session.getAttribute("firstClass");
		
		double totalCost = 0;
		if ((flight.getBusinessClassReserved() - businessClass >= 0) && (flight.getEconomyClassReserved() - economyClass >= 0) && (flight.getFirstClassReserved() - firstClass >= 0)) {
			
			totalCost = economyClass*TX_ECONOMY_SEAT+businessClass*TX_BUSINESS_CLASS_SEAT+firstClass*TX_FIRST_CLASS_SEAT;
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			
			session.setAttribute("totalCostFormatted", formatter.format(totalCost));
			session.setAttribute("totalCost", totalCost);
			session.setAttribute("totalSeats", firstClass+businessClass+economyClass);
			
			RequestDispatcher rd = request
					.getRequestDispatcher("WEB-INF/transaction.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request
					.getRequestDispatcher("WEB-INF/reviewandbook.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
