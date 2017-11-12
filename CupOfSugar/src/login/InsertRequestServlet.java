package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InsertRequestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("request.html");
		rd.include(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String deadline=request.getParameter("deadline");
		String strTitle=request.getParameter("title");
		String strDesc=request.getParameter("description");
		String strPUL=request.getParameter("pickuplocation");
		String strDOL=request.getParameter("dropofflocation");
		
		
		if(RequestDao.save(deadline, strTitle, strDesc, strPUL, strDOL)){
			RequestDispatcher rd=request.getRequestDispatcher("saved");
			rd.forward(request,response);
		}
		else{
			out.print("Error occured while saving data.");
			RequestDispatcher rd=request.getRequestDispatcher("request");
			rd.include(request,response);
		}
		
		out.close();
	}

}
