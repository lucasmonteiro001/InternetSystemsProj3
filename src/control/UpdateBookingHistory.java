package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Book;
import model.Flight;
import model.User;

import org.json.JSONObject;

import utilities.AccountDAO;
import utilities.BookingDAO;
import utilities.JsonHelper;

/**
 * Servlet implementation class UpdateBookingHistory
 */
public class UpdateBookingHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookingHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		HttpSession session 	= request.getSession();
		Flight flight 			= (Flight) session.getAttribute("flightBean");
		User user 				= (User) session.getAttribute("user");
		
		Book book 				= new Book();
		BookingDAO bookingDao	= new BookingDAO();
		JsonHelper js 			= new JsonHelper();
		Account account			= new Account();
		AccountDAO accountDao 	= new AccountDAO();
		
		try {
			String action 	= request.getParameter("action");
			String json 	= request.getParameter("json");
			JSONObject jObj = new JSONObject(json);
			
			int hId 	= Integer.parseInt(jObj.get("accountHolderId").toString());
			int rNum 	= Integer.parseInt(jObj.get("accountRoutingNumber").toString());

			account.setHolderId(hId);
			account.setRoutingNumber(rNum);

			account = accountDao.readAccount(account);
			
			book.setFlightIds(flight.getId());
			book.setNumberOfSeats(Integer.parseInt(session.getAttribute(
					"totalSeats").toString()));
			book.setTotalCost(Double.parseDouble(session.getAttribute(
					"totalCost").toString()));
			book.setAccountId(account.getId());
			book.setUserId(user.getId());
			bookingDao.addBooking(book);
			session.setAttribute("booking", book);
			
			String return_msg = js.getJsonFormatted(true,
					"Thanks! Your transaction was successfully recorded!");
			response.getWriter().print(return_msg);
			
		} catch (Exception e) {
			String return_msg = js.getJsonFormatted(true,
					"Failed to update booking history!");
			response.getWriter().print(return_msg);
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
