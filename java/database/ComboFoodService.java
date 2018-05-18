package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import CaseStudy.sqlConnection;
import entities.ComboFood;

public class ComboFoodService {
	
	static Connection con;
	
	
	public static void main(String[] args) {
	
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","restaurant","restaurant");
		
		System.out.println("Connection Successful"); 
		
		//VIEW INFO FROM TABLE
		System.out.println("You are viewing a combo food.");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter combo food ID to view: ");
		int comboFoodID=scan.nextInt();
		
		
		PreparedStatement oracleStmt = con.prepareStatement("select * from comboFood where comboFoodID=?");
		
		oracleStmt.setInt(1,comboFoodID); 
		oracleStmt.execute();
		
		ResultSet oracleRs = oracleStmt.getResultSet();
		
		
		while(oracleRs.next())
		{
			System.out.println("ComboFood ID: "+oracleRs.getInt(1)+" Food ID: "+oracleRs.getInt(2)+" Combo ID: "+oracleRs.getInt(3));
		}
		
		System.out.println("ComboFood Displayed."); 
		
		//INSERT INTO TABLE
		System.out.println("You are inserting a new combo food.");
		
		System.out.println("Enter combo food ID: ");
		int newcomboFoodID=scan.nextInt();
		System.out.println("Enter food ID: ");
		int newfoodID =scan.nextInt();
		System.out.println("Enter combo ID: ");
		int newcomboID =scan.nextInt();
		
		
		oracleStmt = con.prepareStatement("insert into comboFood values (?,?,?)");
		
		oracleStmt.setInt(1,newcomboFoodID); 
		oracleStmt.setInt(2,newfoodID); 
		oracleStmt.setInt(3,newcomboID); 
		oracleStmt.execute();
		
		System.out.println("Combo Food Added.");
		
		//DELETE FROM TABLE
		System.out.println("You are deleting a combo food.");
		System.out.println("Enter combo food ID: ");
		int delcomboFoodID = scan.nextInt();
		scan.close(); 
		
		PreparedStatement deleteStmt = con.prepareStatement("delete from comboFood where comboFoodID=?");
		
		deleteStmt.setInt(1,delcomboFoodID); 
		deleteStmt.execute();
		
		System.out.println("Combo Food Deleted.");
		
		
		//CLOSE OUT
		oracleStmt.close();
		
		con.close();
		
		
	}catch(SQLException e)
	{
		System.out.println("SQL Exception");
	}
	
	
	catch(Exception e)
		{
			System.out.println("Unable to connect");
		}
	}
}
	
