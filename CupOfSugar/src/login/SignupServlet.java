package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("signup.html");
		rd.include(request,response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("username");
		String p=request.getParameter("password");
		String email = request.getParameter("email");
		String homeaddr = request.getParameter("homeaddr");
		String stateid = request.getParameter("stateid");
		int cell = strToInt(request.getParameter("cellphone"));
		int zipcode = strToInt(request.getParameter("zipcode"));
		
		
		
		if(LoginDao.registerNewUser(n, p, email, homeaddr, stateid, cell, zipcode)){
			RequestDispatcher rd=request.getRequestDispatcher("loggedin.html");
			rd.forward(request,response);
		}
		else {
			out.print("<link href=\"css/style.css\" rel='stylesheet' type='text/css' /><div id=\"userExists\">Sorry, this username already exists.</div>");
			RequestDispatcher rd=request.getRequestDispatcher("signup.html");
			rd.include(request,response);
		}
		
		out.close();
	}

	
	public int strToInt(String str) {
		return Integer.parseInt(str);
	}
}

