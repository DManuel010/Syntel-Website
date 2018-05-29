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
	
	@Override
	public void insert(Object obj) {
		Customer customer = (Customer) obj;
		
		//INSERT INTO TABLE
		int customerID;
		if(customer.getCustomerID() == 0) {
			customerID = getPK(customer.getCustomerID());
			customer.setCustomerID(customerID);
		}
		else {
			customerID = customer.getCustomerID();
		}
		
		
		
		System.out.println("Inserting a new customer...");
		
		try {
			PreparedStatement insertStmt = this.conn.prepareStatement("INSERT INTO Customer"
																	+ "(customerID, firstName, lastName, email, phoneNumber, "
																	+ " loginID, dateOfBirth, homeAddrID, cardID, dateOfRegister, lastLogin) "
																	+ " VALUES(?, ?, ?, ?, ?, ?, TO_DATE(?, 'MM/DD/YYYY'), "
																	+ "			?, ?, SYSDATE, SYSDATE)");
			
			insertStmt.setInt(1,customerID);
			insertStmt.setString(2, customer.getFirstName());
			insertStmt.setString(3, customer.getLastName());
			insertStmt.setString(4, customer.getEmail());
			insertStmt.setString(5, customer.getPhoneNumber());
			insertStmt.setInt(6, customer.getLoginID());
			insertStmt.setString(7, customer.getDateOfBirth());
			insertStmt.setInt(8, customer.getHomeAddrID());
			insertStmt.setInt(9, customer.getCardID());
			insertStmt.executeUpdate();
			System.out.println("CustomerService:  Customer Added.");
		} catch (SQLException e) {
			System.out.println("CustomerService:  Failed to insert customer");
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int customerID)
	{
		//DELETE FROM TABLE
		System.out.println("Deleting customer with customer ID "+customerID+"...");
		
		
		try{
			//change to check for matching customer id in orders table
			PreparedStatement testStmt = this.conn.prepareStatement("select * from login where loginID= (select loginID from customer where customerID=?)");
			testStmt.setInt(1,customerID);
			testStmt.execute();
			ResultSet testresult = testStmt.getResultSet();
			
			if(testresult.next()){
				System.out.println("Cannot delete this customer - matching loginID found in login table.");
			}else{
				try{
					PreparedStatement deleteStmt = this.conn.prepareStatement("delete from customer where customerID=?");
					deleteStmt.setInt(1,customerID); 
					deleteStmt.execute();
					System.out.println();
					System.out.println("Customer deleted.");
					deleteStmt.close();
				}catch (SQLException e) {
					System.out.println("Error: SQL Exception.");
					e.printStackTrace();
				}
			}
			
		}catch (Exception e){
			e.getMessage();
		}
		
	}

	// increment the primary key for new insertion
	public int getPK(int customerID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(customerID) AS pk " +
						"FROM Customer";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(customerID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = customerID;
			}
			
		} catch (SQLException e) {
			System.out.println("CustomerService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}


	public void display() {
		//DISPLAY FROM TABLE
		System.out.println("Displaying customer...");
		System.out.println("Customer ID		First Name		Last Name");
		
			//DISPLAY FROM TABLE
			System.out.println("Displaying customer...");
			System.out.println("Customer ID		First Name		Last Name");
			
			try{
				PreparedStatement oracleStmt = this.conn.prepareStatement("select customerID, firstName, lastName from customer");
				oracleStmt.execute();
				ResultSet oracleRs = oracleStmt.getResultSet();
				
				while(oracleRs.next())
				{
					System.out.println(oracleRs.getInt(1)+"		"+oracleRs.getString(2)+"		"+oracleRs.getString(3));
				}
				System.out.println();
				System.out.println("Customers Displayed.");
				oracleStmt.close();
			}catch (SQLException e) {
				System.out.println("Error: SQL Exception.");
				e.printStackTrace();
			}
	}
}