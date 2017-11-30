package login;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoggedInServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1988453544156025722L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("username");
		Integer userID = (Integer) request.getSession().getAttribute("userID");
		out.print("Welcome "+ n + ". Your ID = " + userID);
		
		out.close();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Integer userID = (Integer) request.getSession().getAttribute("userID");
		out.print("Welcome. Your ID = " + userID);
		
		out.close();
	}
	

}
