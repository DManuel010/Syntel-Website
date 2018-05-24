package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Customer;

public class CustomerService extends Service {

	public CustomerService(Connection conn) {
		super(conn);
	}
	
	private int getPK() {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(customerID) FROM customer";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				lastPK = result.getInt("customerID");
			}
			newPK = lastPK + 1;
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
		}
		return newPK;
	}

	
	public void insert(Object obj) {
		Customer customer = (Customer) obj;
		
		//INSERT INTO TABLE
		
		int customerID;
		if(customer.getCustomerID() == 0) {
			customerID = getPK();
			customer.setCustomerID(customerID);
		}
		else {
			customerID = customer.getCustomerID();
		}
		
		System.out.println("Inserting a new customer...");
		
		try {
			PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO Customer VALUES (?, ?,?,?,?,?,?,?,?,?,?)");
			insertStmt.setString(1,customer.getEmail());
			insertStmt.setString(2,customer.getFirstName()); 
			insertStmt.setString(3,customer.getLastName()); 
			insertStmt.setInt(4,customer.getLoginID());
			insertStmt.setString(5,customer.getPhoneNumber());
			insertStmt.setInt(6,customer.getHomeAddrID());
			insertStmt.setObject(7,customer.getLastLogin());
			insertStmt.setInt(8,customerID); 
			insertStmt.setObject(9,customer.getDateOfBirth());
			insertStmt.setInt(10,customer.getCardID());
			insertStmt.setObject(11,customer.getDateOfRegister());
			insertStmt.execute();
			System.out.println("CustomerService:  Customer Added.");
		} catch (SQLException e) {
			System.out.println("CustomerService:  Failed to insert Customer.");
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
		
		
	
		
	

	

