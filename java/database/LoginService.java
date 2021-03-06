package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Login;

public class LoginService extends Service{
	
	public LoginService(Connection conn) {
		super(conn);
	}


	private int getPK(int loginID) {
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


	@Override
	public void insert(Object obj) {
		Login login = (Login) obj;
	
		//INSERT INTO TABLE
		int loginID = getPK(login.getLoginID());
		login.setLoginID(loginID);
		
		try {
			PreparedStatement insertStmt = conn.prepareStatement("insert into login values (?,?,?,?)");
			insertStmt.setInt(1, loginID); 
			insertStmt.setString(2, login.getUsername()); 
			insertStmt.setString(3, login.getPassword()); 
			insertStmt.setString(4, login.getType());
			insertStmt.execute();
			System.out.println("LoginService:  Login added with login ID "+loginID);
			insertStmt.close();
		} catch (SQLException e) {
			System.out.println("LoginService:  Failed to insert login");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete(int loginID) {
		//DELETE FROM TABLE
		System.out.println("Deleting user with login ID "+loginID+"...");
		try{
			PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM Login WHERE loginID = ?");
			deleteStmt.setInt(1,loginID); 
			deleteStmt.executeUpdate();
			System.out.println("LoginService:  Login deleted");
			deleteStmt.close();
		} catch (SQLException e) {
			System.out.println("LoginService:  Failed to delete login");
			e.printStackTrace();
		}
	}
	
	
	public void display() {
		//DISPLAY FROM TABLE
		System.out.println("Displaying user...");
		System.out.println("Login ID		Username		Password");
			
		try{
			PreparedStatement oracleStmt = conn.prepareStatement("select * from login");
			oracleStmt.execute();
			ResultSet oracleRs = oracleStmt.getResultSet();
				
			while(oracleRs.next()) {
				System.out.println(oracleRs.getInt(1)+"		"+oracleRs.getString(2)+"		"+oracleRs.getString(3));
			}
			System.out.println();
			System.out.println("Users Displayed.");
			oracleStmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
	}

	
	public boolean view(String username) {
		String query = "SELECT * FROM Login WHERE Login.username = ?";
		
		//DISPLAY FROM TABLE
		System.out.println("Displaying user...");
		boolean exists = false;
		
		try{
			PreparedStatement oracleStmt = this.conn.prepareStatement(query);
			oracleStmt.setString(1, username);
			ResultSet oracleRs = oracleStmt.executeQuery();
			
			if(oracleRs.next()) {
				exists=true;
			}
			oracleStmt.close();
		} catch (SQLException e) {
			System.out.println("LoginService:  Failed to view login");
			e.printStackTrace();
		}
		return exists;
	}
}