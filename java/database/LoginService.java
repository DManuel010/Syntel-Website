package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import CaseStudy.sqlConnection;
import interfaces.Service;
import entities.Login;

public class LoginService {
	
		
static Connection con;
	
	
	public static void main(String[] args) {
	
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","restaurant","restaurant");
		
		System.out.println("Connection Successful"); 
		
		//VIEW INFO FROM TABLE
		System.out.println("You are viewing a user.");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter login ID to view: ");
		int loginID=scan.nextInt();
		
		PreparedStatement oracleStmt = con.prepareStatement("select * from login where loginID=?");
		
		oracleStmt.setInt(1,loginID); 
		oracleStmt.execute();
		
		ResultSet oracleRs = oracleStmt.getResultSet();
		
		
		while(oracleRs.next())
		{
			System.out.println("Login ID: "+oracleRs.getInt(1)+" Username: "+oracleRs.getString(2)+" Password: "+oracleRs.getString(3));
		}
		
		System.out.println("User Displayed.");
		
		//INSERT INTO TABLE
		System.out.println("You are inserting a new user.");
		System.out.println("Enter login ID: ");
		int newloginID = scan.nextInt();
		System.out.println("Enter username: ");
		String newusername = scan.next();
		System.out.println("Enter password: ");
		String newpassword = scan.next();
		
		PreparedStatement insertStmt = con.prepareStatement("insert into login values (?,?,?)");
		
		insertStmt.setInt(1,newloginID); 
		insertStmt.setString(2,newusername); 
		insertStmt.setString(3,newpassword); 
		insertStmt.execute();
		
		System.out.println("User Added.");
		
		
		//DELETE FROM TABLE
		
		System.out.println("You are deleting a user.");
		System.out.println("Enter login ID: ");
		int delloginID = scan.nextInt();
		scan.close(); 
		
		PreparedStatement deleteStmt = con.prepareStatement("delete from login where loginID=?");
		
		deleteStmt.setInt(1,delloginID); 
		deleteStmt.execute();
		
		System.out.println("User Deleted.");
		
		
		//CLOSE OUT
		insertStmt.close();
		
		con.close();
		
		
	}catch(SQLException e)
	{
		System.out.println("SQL Exception");
	}
	
	
	catch(Exception e)
		{
			e.getMessage();
		}
	}
}
