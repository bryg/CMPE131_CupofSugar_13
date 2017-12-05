package login;
import java.sql.*;
import java.text.DateFormat;

public class RequestDao {

	public static boolean save(int userID, String deadline, String title, String description, String pickuplocation, String dropofflocation) {
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
					.prepareStatement("INSERT INTO requests (userid, deadline, title, description, pickuplocation, dropofflocation) VALUES(?,?,?,?,?,?)");// ? represents some parameter to include
			
			oPrStmt.setInt(1, userID);
			oPrStmt.setDate(2, Date.valueOf(deadline));// parameter index start from 1
			oPrStmt.setString(3, title);
			oPrStmt.setString(4, description);
			oPrStmt.setString(5, pickuplocation);
			oPrStmt.setString(6, dropofflocation);
			
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
