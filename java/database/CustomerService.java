package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Customer;

public class CustomerService extends Service {

	public CustomerService(Connection conn) {
		super(conn);
	}

	
	public void insert(Object obj) {
		Customer customer = (Customer) obj;
		
		//INSERT INTO TABLE
		System.out.println("Inserting a new customer...");
		
		try {
			PreparedStatement insertStmt = conn.prepareStatement("insert into customer values (?,?,?,?,?,?,?,?,?,?)");
			
			insertStmt.setInt(1,customer.getCustomerID()); 
			insertStmt.setString(2,customer.getFirstName()); 
			insertStmt.setString(3,customer.getLastName()); 
			insertStmt.setString(4,customer.getEmail()); 
			insertStmt.setInt(5,customer.getLoginID());
			insertStmt.setObject(6, customer.getDateOfBirth()); 
			insertStmt.setInt(7,customer.getHomeAddrID());
			insertStmt.setInt(8,customer.getCardID()); 
			insertStmt.setObject(9,customer.getDateOfRegister()); 
			insertStmt.setObject(10,customer.getLastLogin()); 
			insertStmt.execute();
			System.out.println("Customer Added.");
			
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
	}

	
	public void delete(int customerID)
	{
		//DELETE FROM TABLE
		System.out.println("Deleting customer with customer ID "+customerID+"...");
		
		try{
			//change to check for matching customer id in orders table
			PreparedStatement testStmt = conn.prepareStatement("select * from login where loginID= (select loginID from customer where customerID=?)");
			testStmt.setInt(1,customerID);
			testStmt.execute();
			ResultSet testresult = testStmt.getResultSet();
			
			if(testresult.next()) {
				System.out.println("Cannot delete this customer - matching loginID found in login table.");
			} else {
				try {
					PreparedStatement deleteStmt = conn.prepareStatement("delete from customer where customerID=?");
					deleteStmt.setInt(1,customerID); 
					deleteStmt.execute();
					System.out.println();
					System.out.println("Customer deleted.");
					deleteStmt.close();
				} catch (SQLException e) {
					System.out.println("Error: SQL Exception.");
					e.printStackTrace();
				}
			}
			
		}catch (Exception e){
			e.getMessage();
		}
		
	}

	public void display() {
		//DISPLAY FROM TABLE
		System.out.println("Displaying customer...");
		System.out.println("Customer ID		First Name		Last Name");
		
		try {
			PreparedStatement oracleStmt = conn.prepareStatement("select customerID, firstName, lastName from customer");
			oracleStmt.execute();
			ResultSet oracleRs = oracleStmt.getResultSet();
			
			while(oracleRs.next()) {
				System.out.println(oracleRs.getInt(1)+"		"+oracleRs.getString(2)+"		"+oracleRs.getString(3));
			}
			System.out.println();
			System.out.println("Customers Displayed.");
			oracleStmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
	}
}
		
		
	
		
	

	

