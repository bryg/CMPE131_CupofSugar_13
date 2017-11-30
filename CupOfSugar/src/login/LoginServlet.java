//import java.io.IOException;
package login;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.include(request,response);
		
		// for now, erase all previous session data
		request.getSession().removeAttribute("userID");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("username");
		String p=request.getParameter("userpass");
		
		if(LoginDao.validate(n, p)) {
			HttpSession session = request.getSession();
			session.setAttribute("userID", Integer.valueOf(LoginDao.retrieveUserID(n)));
			
			RequestDispatcher rd=request.getRequestDispatcher("loggedin.html");
			rd.forward(request,response);
		}
		else {
			out.print("Sorry username or password error");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request,response);
		}
		
		out.close();
	}

}
