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
		HttpSession session = request.getSession();
		Account account = new Account();
		AccountDAO accountDao = new AccountDAO();
		User user = (User) session.getAttribute("user");
		Flight flight = (Flight) session.getAttribute("flightBean");

		try {

			String action = request.getParameter("action");
			String json = request.getParameter("json");
			// JSONObject jObj = new JSONObject(json);
			// int hId =
			// Integer.parseInt(jObj.get("accountHolderId").toString());
			// int rNum = Integer
			// .parseInt(jObj.get("accountRoutingNumber").toString());
			int hId = 14;
			int rNum = 22;

			account.setHolderId(hId);
			account.setRoutingNumber(rNum);

			account = accountDao.readAccount(account);

			BankModel bm = new BankModel(account);

			if (bm.isTransactionAllowed(1000.0).equals("Yes")) {
				System.out.println("y");
			} else {
				System.out.println("no");
			}

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
