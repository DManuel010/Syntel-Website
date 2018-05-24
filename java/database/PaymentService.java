package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import entities.Payment;

public class PaymentService extends Service {

	public PaymentService(Connection conn) {
		super(conn);
	}
	
	@Override
	public void insert(Object obj) {
		Payment payment = (Payment) obj;
		
		// get payment values
		int paymentID = getPK(payment.getPaymentID());
		payment.setPaymentID(paymentID);
		
		String type = payment.getType();
		float amount = payment.getAmount();
		Date datePaid = payment.getDatePaid();
		
		// format query
		String query = "INSERT INTO Payment "
					+ "(paymentID, type, amount, datePaid) " 
					+ "VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, paymentID);
			statement.setString(2, type);
			statement.setFloat(3, amount);
			statement.setDate(4, (java.sql.Date) datePaid);
			statement.addBatch();
			statement.executeBatch();
			System.out.println("PaymentService:  Payment insert successful.");	
		} catch (SQLException e) {
			System.out.println("PaymentService:  Failed to insert payment.");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete(int paymentID) {	
		String query = "DELETE FROM Payment WHERE paymentID = ?";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, paymentID);
			statement.addBatch();
			statement.executeBatch();
			System.out.println("PaymentService:  Payment deleted.");
		} catch (SQLException e) {
			System.out.println("PaymentService:  Failed to delete payment.");
			e.printStackTrace();
		}
	}
	
	
	// increment the primary key for new insertion
	private int getPK(int paymentID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(paymentID) AS pk FROM Payment";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(paymentID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = paymentID;
			}
			
		} catch (SQLException e) {
			System.out.println("PaymentService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}

}
