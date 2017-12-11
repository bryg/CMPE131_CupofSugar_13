package login;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class UserHistoryDao extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		    
		  response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    
		    out.println("<html>");
		    out.println("<head><title>Cup of Sugar - User History</title><link href=\"css/bootstrap.css\" rel='stylesheet' type='text/css' />\r\n" + 
		    		"<link href=\"css/bootstrap-form.css\" rel='stylesheet' type='text/css' />\r\n" + 
		    		"<link href=\"https://fonts.googleapis.com/css?family=Lobster|Open+Sans+Condensed:300\" rel=\"stylesheet\"> </head>");
		    out.println("<body>");
		    out.println("<h1 style=\"font-family: 'Lobster', cursive;\">Cup of Sugar User History</h1>");
		    out.println("<h2 style=\"font-family: 'Lobster', cursive;\">Your Cups of Sugar</h2>");
		    out.println("<table class=\"table table-striped\">");
		    out.println("<tr>");
		    out.println("<th>Deadline</th>");
		    out.println("<th>Title</th>");
		    out.println("<th>Description</th>");
		    out.println("<th>Pick up Location</th>");
		    out.println("<th>Drop off Location</th>");
		    out.println("<th>Completion</th>");
		    // the out.println for this section is a collection of string outputs that are headers for the table. This is simply a text string output
		
		  
		    Connection conn = null;		// initialization variable
		    Statement stmt = null;		// initialization variable 
		    int userID = ((Integer)request.getSession().getAttribute("userID")).intValue();
		    try {																// Try block used to catch any exceptions that may occur
		      Class.forName("com.mysql.jdbc.Driver");							// Attempts to select an appropriate driver
		      conn = DriverManager.getConnection(								// Attempts to establish a connection to the given database URL
						"jdbc:mysql://localhost:3306/login", "root", "");		// Database
		      stmt = conn.createStatement();									// Output display statement
		      
		      // Displays User's Requests
		      String query = "SELECT requests.deadline, requests.title, " + "requests.description, "
		          + "requests.pickuplocation, requests.dropofflocation, requests.acceptedby, requests.complete" + " FROM requests " + " WHERE userid=" + userID;
		      ResultSet rs = stmt.executeQuery(query);
		      while (rs.next()) {
		        Date deadline = rs.getDate("deadline");
		        String title = rs.getString("title");							// Simple text display using string
		        String description = rs.getString("description");
		        String pickuplocation = rs.getString("pickuplocation");
		        String dropofflocation = rs.getString("dropofflocation");
		        int acceptedby = rs.getInt("acceptedby");
		        int complete = rs.getInt("complete");
		        out.println("<tr>");
		        out.print("<td>" + deadline + "</td>");					// Dynamic listing of details for cups of sugar available on database
		        out.print("<td>" + title + "</td>");					
		        out.print("<td>" + description + "</td>");
		        out.print("<td>" + pickuplocation + "</td>");
		        out.print("<td>" + dropofflocation + "</td>");
		        if(complete== 1) {
		        	out.print("<td>Complete</td>");
		        }
		        else if(complete==0 && acceptedby!=0) {
		        	out.print("<td>In Progress</td>");
		        }
		        else out.print("<td>Waiting for an Accept</td>");
		        out.print("</tr>");

		      }
		      out.println("</table>");
		      
		      // Requests User has accepted
		      out.println("<h2 style=\"font-family: 'Lobster', cursive;\">Cups of Sugar You've Accepted</h2>");
			    out.println("<table class=\"table table-striped\">");
			    out.println("<tr>");
			    out.println("<th>Deadline</th>");
			    out.println("<th>Title</th>");
			    out.println("<th>Description</th>");
			    out.println("<th>Pick up Location</th>");
			    out.println("<th>Drop off Location</th>");
			    out.println("<th>Completion</th>");
			    
			    rs = null;
			    query = "SELECT requests.deadline, requests.title, " + "requests.description, "
				          + "requests.pickuplocation, requests.dropofflocation, requests.complete" + " FROM requests " + " WHERE acceptedby=" + userID;
				      rs = stmt.executeQuery(query);
				      while (rs.next()) {
				        Date deadline = rs.getDate("deadline");
				        String title = rs.getString("title");							// Simple text display using string
				        String description = rs.getString("description");
				        String pickuplocation = rs.getString("pickuplocation");
				        String dropofflocation = rs.getString("dropofflocation");
				        int complete = rs.getInt("complete");
				        
				        out.println("<tr>");
				        out.print("<td>" + deadline + "</td>");					// Dynamic listing of details for cups of sugar available on database
				        out.print("<td>" + title + "</td>");					
				        out.print("<td>" + description + "</td>");
				        out.print("<td>" + pickuplocation + "</td>");
				        out.print("<td>" + dropofflocation + "</td>");
				        if(complete== 1) {
				        	out.print("<td>Complete</td>");
				        }
				        else if(complete==0) {
				        	out.print("<td>In Progress</td>");
				        }
				        else out.print("<td>Error</td>");
				        out.print("</tr>");

				      }
				      out.println("</table>");
		    } catch (SQLException e) {
		      out.println("An error occured while retrieving the list." 
		          + e.toString());
		    } catch (ClassNotFoundException e) {
		      throw (new ServletException(e.toString()));
		    } finally {
		      try {
		        if (stmt != null) {
		          stmt.close();
		        }
		        if (conn != null) {
		          conn.close();
		        }
		      } catch (SQLException ex) {
		      }
		    }
		    out.println("</center>");
		    out.println("</body>");
		    out.println("</html>");
		    out.close();
		  }
		}