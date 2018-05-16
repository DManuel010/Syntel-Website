package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CaseStudy.sqlConnection;
import interfaces.Service;

public class CustomerService implements Service{
	
		
		Connection con;

		@Override
		public void insert(Customer customer) {
			try {
				CallableStatement oracleCallableStmt = con.prepareCall("{call SP_INS_CUSTOMER(?,?,?,?,?,?,?,?,?,?)}");
				oracleCallableStmt.setInt(1, customer.getCustomerID());
				oracleCallableStmt.setString(2, customer.getFirstName());
				oracleCallableStmt.setString(3, customer.getLastName());
				oracleCallableStmt.setString(4, customer.getEmail());
				oracleCallableStmt.setInt(5, customer.getLoginID());
				oracleCallableStmt.setDate(6, customer.getDateOfBirth());
				oracleCallableStmt.setInt(7, customer.getHomeAddrID());
				oracleCallableStmt.setInt(8, customer.getCardID());
				oracleCallableStmt.setDate(9, customer.getDateOfRegister());
				oracleCallableStmt.setDate(10, customer.getLastLogin());
				oracleCallableStmt.execute();
				System.out.println("Customer Added."); 
				oracleCallableStmt.close();
			} catch (SQLException e) {
				System.out.println("Failed to add customer.");
			}
			
		}

		@Override
		public void delete(int customerID) {
			try {
				CallableStatement oracleCallableStmt;
				oracleCallableStmt = con.prepareCall("{call SP_DEL_CUSTOMER(?)}");
				oracleCallableStmt.setInt(1, customerID);
				oracleCallableStmt.execute();
				System.out.println("Customer Deleted."); 
				oracleCallableStmt.close();
			} catch (SQLException e) {
				System.out.println("Failed to delete customer.");
			}
			
		}
	
}

	

