package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

public class SugarcubesServlet extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440869407955920788L;

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
		System.out.println("Entered Sugarcubes Servlet doPost");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String strRequestID = request.getParameter("requestID");
		String strOutcome = request.getParameter("outcome");
		
		int requestID = strToInt(strRequestID);
		
		if(save(requestID, strOutcome)){
			response.sendRedirect("loggedin.html");
		}
		
		else{
			out.print("Error occured while saving data.");
			RequestDispatcher rd=request.getRequestDispatcher("list");
			rd.include(request,response);
		}
		//response.sendRedirect("loggedin");
		out.close();
		
	}
	
	public int strToInt(String str) {
		return Integer.parseInt(str);
	}
	
	public static boolean save(int requestID, String outcome) {
		boolean isRecordInserted = false;
		try {
			//defining database driver to use
			Class.forName("com.mysql.jdbc.Driver");
			
			//getting connection from the mysql database
			//jdbc:mysql://localhost:3306 is database url
			//login is database name
			//root : username               
			//root: password
			//syntex : databaseurl/databasename, username , password
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/login", "root", "");
			
			

			//prepared statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from users where username = name and password = pass
			int value = 0;
			if (outcome.equals("success")) value = 1;
			else value = -2;
			PreparedStatement oPrStmt = con
					.prepareStatement("UPDATE requests SET complete=1 where id=?");// ? represents some parameter to include
			
			oPrStmt.setInt(1, requestID);
			
			
			int nInsertedRecords = oPrStmt.executeUpdate(); // executing the query and getting the updated/inserted row counts from databse
			
			// Selects Acceptor's User ID
			java.sql.Statement stmt = con.createStatement();									// Output display statement
		      
		      String query = "SELECT requests.acceptedby" + " FROM requests " + " WHERE id=" + requestID + ";";
		      ResultSet rs = stmt.executeQuery(query);
		      rs.next();
		      int acceptorID = rs.getInt("acceptedby");
		      
		      // Updates Sugarcubes of acceptor
		      oPrStmt = con
						.prepareStatement("UPDATE users SET sugarcubes=sugarcubes + " + value +" where id=?");// ? represents some parameter to include
				
				oPrStmt.setInt(1, acceptorID);
				
				
				nInsertedRecords = oPrStmt.executeUpdate();
			
			
			if(nInsertedRecords>0){ // check that the data is inserted successfully or not
				isRecordInserted = true;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return isRecordInserted;
	}

}
	