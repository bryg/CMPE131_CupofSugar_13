package login;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;

public class AcceptRequestDao extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6074048617638962860L;

	public static boolean save(int requestID, int userID) {

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
			PreparedStatement oPrStmt = con
					.prepareStatement("UPDATE requests SET acceptedby=? WHERE id=?");// ? represents some parameter to include
			
			oPrStmt.setInt(1, userID);
			oPrStmt.setInt(2, requestID);// parameter index start from 1

			
			int nInsertedRecords = oPrStmt.executeUpdate(); // executing the query and getting the updated/inserted row counts from databse
			
			if(nInsertedRecords>0){ // check that the data is inserted successfully or not
				isRecordInserted = true;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return isRecordInserted;
	}
}
