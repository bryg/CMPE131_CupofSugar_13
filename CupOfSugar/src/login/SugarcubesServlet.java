package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SugarcubesServlet extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440869407955920788L;
	String rid = "";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered Accept Request Servlet doGet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String rid = request.getParameter("ider");
		System.out.println("rid=" + rid);
		if (request.getSession().getAttribute("userID") == null) {
			// user did not log in before accessing this page
			out.write("You are not logged in!");
			out.close();
			return;
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("list");
		rd.include(request,response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered Accept Request Servlet doPost");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		rid = request.getParameter("ider");
		System.out.println("rid = " + rid);
		int requestID = strToInt(rid);
		
		int userID = ((Integer)request.getSession().getAttribute("userID")).intValue();
		System.out.println("USERID = " + userID);
		if(FeedbackDao.save(requestID, userID)){
			RequestDispatcher rd=request.getRequestDispatcher("list");
			rd.forward(request,response);
		}
		
		else{
			out.print("Error occured while saving data.");
			RequestDispatcher rd=request.getRequestDispatcher("list");
			rd.include(request,response);
		}
		response.sendRedirect("ListDao");
		out.close();
		
	}
	
	public int strToInt(String str) {
		return Integer.parseInt(str);
	}

}
	