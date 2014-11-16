package control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import model.User;

/**
 * In this class the server will receive the attempt to login and will process it. 
 * 
 */
/**
 * @author Mateus
 *
 */
public class Login extends HttpServlet {
	private User user;
	private Users us;
	private String EMAIL_PARAMETER = "email";
	private String PASSWORD_PARAMETER = "password";

	/**
	 * This doGet is "just in cause". Will not be used in this application. Call
	 * doPost.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * This doPost calls the servlet Users to get if the user exists or not. The
	 * it redirects for the appropriate page.
	 * 
	 * @param request
	 *            HttpServletRequest with information of request like username
	 *            and password.
	 * @param response
	 *            HttpServletResponse with information of where to go in case if
	 *            the username exists or not
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		Users users = new Users();
		User usr = new User();
		
		usr.setEmail((String) request.getParameter(EMAIL_PARAMETER));
		usr.setPassword((String) request.getParameter(PASSWORD_PARAMETER));
		
		if (users.userExists(usr) == true) {
			String hashed = "" + usr.getPassword().hashCode();
				if (hashed.equals(users.getUser().getPassword())) {
					usr = users.getUser();
					session.setAttribute("user", usr);
					response.sendRedirect("flightsearchquery.jsp");
				}
				else {
					session.setAttribute("status", "Wrong username or password.");
					response.sendRedirect("login.jsp");
				}
			}
			else {
				session.setAttribute("status", "Wrong username or password.");
				response.sendRedirect("login.jsp");
			}
			
		}
/*		
		// Create a session object if it is already not created.
		HttpSession session = request.getSession();
		
		User usr = (User) session.getAttribute("usr");
		// Get session creation time.
		Date createTime = new Date(session.getCreationTime());
		// Get last access time of this web page.
		//Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		user = new User(session.getAttribute(EMAIL_PARAMETER).toString(),
				session.getAttribute(PASSWORD_PARAMETER).toString());

		us = new Users();
		if (us.userExists(user)== true) {
			String hashed = ""+user.getPassword().hashCode();
			if (hashed.equals(us.getUser().getPassword()))
				response.sendRedirect("flightsearchquery.jsp");
			else {
			response.sendRedirect("login.jsp");
		}

		} else {
			response.sendRedirect("registration.jsp");
		}

	}*/
}
