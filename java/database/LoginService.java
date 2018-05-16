package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CaseStudy.sqlConnection;
import interfaces.Service;
import entities.Login;

public class LoginService implements Service{
	
		
		Connection con;
		
		public LoginService(Connection con) {
			super();
			this.con = con;
		}

		
		@Override
		public void insert(Login user) {
			try {
				CallableStatement oracleCallableStmt = con.prepareCall("{call SP_INS_LOGIN(?,?,?)}");
				oracleCallableStmt.setInt(1, user.getLoginID());
				oracleCallableStmt.setString(2, user.getUsername());
				oracleCallableStmt.setString(3, user.getPassword());
				oracleCallableStmt.execute();
				System.out.println("User Added"); 
				oracleCallableStmt.close();
			} catch (SQLException e) {
				System.out.println("Failed to add user.");
			}
			
		}

		@Override
		public void delete(int loginID) {
			try {
				CallableStatement oracleCallableStmt;
				oracleCallableStmt = con.prepareCall("{call SP_DEL_LOGIN(?)}");
				oracleCallableStmt.setInt(1, loginID);
				oracleCallableStmt.execute();
				System.out.println("User Deleted"); 
				oracleCallableStmt.close();
			} catch (SQLException e) {
				System.out.println("Failed to delete user.");
			}
			
		}	
}
