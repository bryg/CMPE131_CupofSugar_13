/*
@author: Divyang Soni
@date : 10/18/2017
@ This class is having database related methods for login application
*/
package login;
import java.sql.*;

public class LoginDao {

	public static boolean validate(String name, String pass) {
		boolean validLogin = false;
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
					.prepareStatement("select * from users where username=? and password=?");// ? represents some parameter to include
																							
			oPrStmt.setString(1, name);// parameter index start from 1
			oPrStmt.setString(2, pass);
			ResultSet rs = oPrStmt.executeQuery(); // executing the query and getting the resultset from databse
			
			//rs.next() shows that the resultset contains nect value or not
			// for retriving multiple results, you can use while(rs.next)
			
			if (rs.next()) { //checking if the result next has any value?   
				validLogin = true;
			}
			
			con.close();
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return validLogin;
	}
	
	
	public static int retrieveUserID(String name) {
		int userID = -1;
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
					.prepareStatement("select * from users where username=?");// ? represents some parameter to include
																							
			oPrStmt.setString(1, name);// parameter index start from 1
			
			ResultSet rs = oPrStmt.executeQuery(); // executing the query and getting the resultset from databse
			
			//rs.next() shows that the resultset contains nect value or not
			// for retriving multiple results, you can use while(rs.next)
			
			if (rs.next()) { //checking if the result next has any value?   
				userID = rs.getInt(1);
			}
			
			con.close();
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return userID;
	}
	
	
	// Makes new user
	public static boolean registerNewUser(String name, String pass, String email, String homeaddr, String stateid, int cellphone, int zipcode) {
		boolean didRegister = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/login", "root", "");

			PreparedStatement oPrStmt = con
					.prepareStatement("INSERT INTO users (username, password, email, homeaddr, stateid, cellphone, zipcode) VALUES (?,?,?,?,?,?,?)");
			
			oPrStmt.setString(1, name);
			oPrStmt.setString(2, pass);
			oPrStmt.setString(3, email);
			oPrStmt.setString(4, homeaddr);
			oPrStmt.setString(5, stateid);
			oPrStmt.setInt(6, cellphone);
			oPrStmt.setInt(7, zipcode);



			
			
			int nInsertedRec = oPrStmt.executeUpdate();
			
			if(nInsertedRec > 0)
				didRegister = true;
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return didRegister;
	}
	
}
