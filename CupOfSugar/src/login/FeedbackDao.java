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

public class FeedbackDao extends HttpServlet{
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println("<html>");
		    out.println("<head><title>Cup of Sugar - Requests</title></head>");
		    out.println("<body>");
		    out.println("<center><h1>Your Cups of Sugar In Progress</h1>");
		    out.println("<table styel=\"width:100%\">");
		    out.println("<tr>");
		    out.println("<th>Title</th>");
		    out.println("<th>Description</th>");
		    out.println("<th>Pick up Location</th>");
		    out.println("<th>Completed?</th>");
		    out.println("<th>Failed?</th></tr>");

		    
		    Connection conn = null;
		    Statement stmt = null;
		    try {
		      
		    	int userID = ((Integer)request.getSession().getAttribute("userID")).intValue();	
		    	Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/login", "root", "");
		      stmt = conn.createStatement();
		      String query = "SELECT requests.id, requests.userid, requests.acceptedby, requests.complete, requests.title, " + "requests.description, "
			          + "requests.pickuplocation" + " FROM requests WHERE acceptedby <> 0 AND complete = 0 AND userid =" + userID + ";";// pull logic
			      ResultSet rs = stmt.executeQuery(query);
			      while (rs.next()) {
			    	int id = rs.getInt("id");
			    	
			        String title = rs.getString("title");
			        String description = rs.getString("description");
			        String pickuplocation = rs.getString("pickuplocation");
			        out.println("<tr>");
			        out.print("<td>" + title + "</td>");
			        out.print("<td>" + description + "</td>");
			        out.print("<td>" + pickuplocation + "</td>");
			        
			        
			        out.print("<td>" + "<form accept-charset=\"utf-8\" action=\"sugarcubes\" method=\"post\">" +   // do 
		        			"<input type=\"hidden\" name=\"requestID\" value=\"" + id + "\">" + 
		        			"<input type=\"hidden\" name=\"outcome\" value=\"success\">" +
		        			"<input type=\"submit\" value=\"Success\" />"
		        			+ "</form>" + "</td>");
			        out.print("<td>" + "<form accept-charset=\"utf-8\" action=\"sugarcubes\" method=\"post\">" +   // do 
		        			"<input type=\"hidden\" name=\"requestID\" value=\"" + id + "\">" +
		        			"<input type=\"hidden\" name=\"outcome\" value=\"failed\">" +
		        			"<input type=\"submit\" value=\"Failed\" />"
		        			+ "</form>" + "</td>");
			        out.print("</tr>");
		        			}
			      out.println("</table>");
		      
		    } catch (SQLException e) {
			      out.println("An error occured while retrieving the list." 
				          + e.toString());
	  } catch (ClassNotFoundException e) {
	      throw (new ServletException(e.toString()));
}  finally {
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

