package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Book;
import model.Flight;
import model.User;

import org.json.JSONException;
import org.json.JSONObject;

import utilities.AccountDAO;
import utilities.BookingDAO;
import utilities.JsonHelper;

/**
 * Servlet implementation class Transaction
 */
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		session = request.getSession();
		Account account = new Account();
		Book book = new Book();
		User user = (User) session.getAttribute("user");
		Flight flight = (Flight) session.getAttribute("flightBean");
		AccountDAO accountDao = new AccountDAO();
		BookingDAO bookingDao = new BookingDAO();
		JsonHelper js = new JsonHelper();

		try {

			String action = request.getParameter("action");
			String json = request.getParameter("json");
			JSONObject jObj = new JSONObject(json);
			int hId = 0;
			int rNum = 0;

			hId = Integer.parseInt(jObj.get("accountHolderId").toString());
			rNum = Integer
					.parseInt(jObj.get("accountRoutingNumber").toString());

			account.setHolderId(hId);
			account.setRoutingNumber(rNum);

			account = accountDao.readAccount(account);

			// TODO testar se a conta eh nula

			if (account.getBalance()
					- Double.parseDouble(session.getAttribute("totalCost")
							.toString()) >= 0) {
				account.setBalance(account.getBalance()
						- Double.parseDouble(session.getAttribute("totalCost")
								.toString()));
				accountDao.updateAccount(account);
				book.setFlightIds(flight.getId());
				book.setNumberOfSeats(Integer.parseInt(session.getAttribute(
						"totalSeats").toString()));
				book.setTotalCost(Double.parseDouble(session.getAttribute(
						"totalCost").toString()));
				book.setAccountId(account.getId());
				book.setUserId(user.getId());
				bookingDao.addBooking(book);
				session.setAttribute("booking", book);
				RequestDispatcher rd = request
						.getRequestDispatcher("WEB-INF/transactionconfirmationpage.jsp");
				rd.forward(request, response);
			} else {
				String msg = js.getJsonFormatted(false,
						"Your account number was rejected by your bank. Please contact them and try again.");
				out.print(msg);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			String msg = js.getJsonFormatted(false,
					"Please, enter a valid account");
			out.print(msg);
		} catch (NullPointerException e) {
			String msg = js.getJsonFormatted(false,
					"Invalid account information");
			out.print(msg);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
