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

public class ListDao extends HttpServlet{
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		  System.out.println("Entered ListDao");  
		  response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    
		    out.println("<html>");
		    out.println("<head><title>Cup of Sugar - Requests</title></head>");
		    out.println("<body>");
		    out.println("<center><h1>Available Cups of Sugar</h1>");
		    out.println("<table styel=\"width:100%\">");
		    out.println("<tr>");
		    out.println("<th>Deadline</th>");
		    out.println("<th>Title</th>");
		    out.println("<th>Description</th>");
		    out.println("<th>Pick up Location</th>");
		    out.println("<th>Drop off Location</th>");
		    // the out.println for this section is a collection of string outputs that are headers for the table. This is simply a text string output
		
		  
		    Connection conn = null;		// initialization variable
		    Statement stmt = null;		// initialization variable 
		    try {																// Try block used to catch any exceptions that may occur
		      Class.forName("com.mysql.jdbc.Driver");							// Attempts to select an appropriate driver
		      conn = DriverManager.getConnection(								// Attempts to establish a connection to the given database URL
						"jdbc:mysql://localhost:3306/login", "root", "");		// Database
		      stmt = conn.createStatement();									// Output display statement
		      
		      String query = "SELECT requests.id, requests.deadline, requests.title, " + "requests.description, "
		          + "requests.pickuplocation, requests.dropofflocation " + "FROM requests " + " WHERE acceptedby=0 AND complete=0;";
		      ResultSet rs = stmt.executeQuery(query);
		      while (rs.next()) {
		    	int id = rs.getInt("id");
		    	
		        Date deadline = rs.getDate("deadline");
		        String title = rs.getString("title");							// Simple text display using string
		        String description = rs.getString("description");
		        String pickuplocation = rs.getString("pickuplocation");
		        String dropofflocation = rs.getString("dropofflocation");
		        System.out.println("id: " + id);
		        out.println("<tr>");
		        out.print("<td>" + deadline + "</td>");					// Dynamic listing of details for cups of sugar available on database
		        out.print("<td>" + title + "</td>");					
		        out.print("<td>" + description + "</td>");
		        out.print("<td>" + pickuplocation + "</td>");
		        out.print("<td>" + dropofflocation + id + "</td>");
		        out.print("<td>" + "<form accept-charset=\"utf-8\" action=\"acceptrequest\" method=\"post\">" + 
		        			"<input type=\"hidden\" name=\"ider\" value=\"" + id + "\">" + 
		        			"<input type=\"submit\" value=\"Accept Request\" />"
		        		+ "</form>" + "</td>");
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