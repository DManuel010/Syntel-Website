package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Login;

public class LoginService extends Service {
	
		
static Connection con;


	public LoginService(Connection conn) {
		super(conn);
	}

	
	@Override
	public void insert(Object obj) {
		
		Login login = (Login) obj;
		
		//INSERT INTO TABLE
		int loginID;
		if(login.getLoginID() == 0) {
			loginID = getPK(0);
			login.setLoginID(loginID);
		}
		else {
			loginID = login.getLoginID();
		}
		
		
		System.out.println("Inserting a new user...");
				
		
		try {
			PreparedStatement insertStmt = con.prepareStatement("insert into login values (?,?,?)");
			insertStmt.setInt(1,loginID); 
			insertStmt.setString(2,login.getUsername()); 
			insertStmt.setString(3,login.getPassword()); 
			insertStmt.execute();
			System.out.println();
			System.out.println("User added with login ID "+loginID);
			insertStmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
		
	}

	public void delete(int loginID)
	{
		//DELETE FROM TABLE
		System.out.println("Deleting user with login ID "+loginID+"...");
		
		try{
			PreparedStatement deleteStmt = con.prepareStatement("delete from login where loginID=?");
			deleteStmt.setInt(1,loginID); 
			deleteStmt.execute();
			System.out.println();
			System.out.println("User deleted.");
			deleteStmt.close();
		}catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
		
	}

	public void display()
	{
		
		//DISPLAY FROM TABLE
		System.out.println("Displaying user...");
		System.out.println("Login ID		Username		Password");
			
		try{
			PreparedStatement oracleStmt = con.prepareStatement("select * from login");
			oracleStmt.execute();
			ResultSet oracleRs = oracleStmt.getResultSet();
				
			while(oracleRs.next())
			{
				System.out.println(oracleRs.getInt(1)+"		"+oracleRs.getString(2)+"		"+oracleRs.getString(3));
			}
			System.out.println();
			System.out.println("Users Displayed.");
			oracleStmt.close();
		}catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
	}

	// increment the primary key for new insertion
	public int getPK(int loginID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(loginID) AS pk " +
						"FROM Login";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(loginID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = loginID;
			}
			
		} catch (SQLException e) {
			System.out.println("LoginService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}
}