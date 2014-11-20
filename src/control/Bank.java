package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.BankModel;
import model.Flight;
import model.User;

import org.json.JSONObject;

import utilities.AccountDAO;
import utilities.JsonHelper;

/**
 * Servlet implementation class Bank
 */
public class Bank extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");

		HttpSession session = request.getSession();
		Account account = new Account();
		AccountDAO accountDao = new AccountDAO();
		User user = (User) session.getAttribute("user");
		Flight flight = (Flight) session.getAttribute("flightBean");
		JsonHelper js = new JsonHelper();
		String return_msg = "";

		try {

			String action = request.getParameter("action");
			String json = request.getParameter("json");
			JSONObject jObj = new JSONObject(json);
			int hId = Integer.parseInt(jObj.get("accountHolderId").toString());
			int rNum = Integer.parseInt(jObj.get("accountRoutingNumber")
					.toString());
			// int hId = 123;
			// int rNum = 123;

			account.setHolderId(hId);
			account.setRoutingNumber(rNum);

			account = accountDao.readAccount(account);

			BankModel bm = new BankModel(account);

			if (bm.isTransactionAllowed(Double.parseDouble(session.getAttribute("totalCost")
					.toString()))) {
				accountDao.updateAccount(account);
				return_msg = js.getJsonFormatted(true,
						"Thanks! Your transaction was successfully recorded!");
				response.getWriter().print(return_msg);
			} else {
				return_msg = js.getJsonFormatted(false,
						"The transaction was not allowed by your bank.");
				response.getWriter().print(return_msg);
			}
		} catch (NullPointerException e) {
			return_msg = js.getJsonFormatted(false,
					"Please, enter a valid account!");
			response.getWriter().print(return_msg);
		} catch (NumberFormatException e) {
			return_msg = js.getJsonFormatted(false,
					"Please, enter a valid account!");
			response.getWriter().print(return_msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
