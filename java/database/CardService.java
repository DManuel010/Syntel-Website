package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import entities.Card;

public class CardService extends Service {
	
	public CardService(Connection conn) {
		super(conn);
	}
	
	
	@Override
	public void insert(Object obj) {
		Card card = (Card) obj;
		
		// get card values
		int cardID = getPK(card.getCardID());
		card.setCardID(cardID);
		
		String cardName = card.getName();
		String cardNumber = card.getNumber();
		Date expirationDate = card.getExpirationDate();
		int cvv = card.getcvv();
		String type = card.getType();
		int billingAddrID = card.getBillingAddr().getLocationID();
		
		// format query
		String query = "INSERT INTO Card "
					+ "(cardID, name, cardNumber, expirationDate, cvv, type, billingAddrID) " 
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);	// build statement
			statement.setInt(1, cardID);
			statement.setString(2, cardName);
			statement.setString(3, cardNumber);
			statement.setDate(4, (java.sql.Date) expirationDate);
			statement.setInt(5, cvv);
			statement.setString(6, type);
			statement.setInt(7, billingAddrID);
			statement.execute(query);
			System.out.println("Card inserted.");
		} catch (SQLException e) {
			System.out.println("Failed to insert card.");
			e.printStackTrace();
		}
	}
	
	
	// increment the primary key for new insertion
		private int getPK(int cardID) {
			int lastPK = 0;
			int newPK = 0;
			String query = "SELECT MAX(cardID) AS pk " +
							"FROM Card";
			
			try {
				Statement statement = this.conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				
				if(result.next()) {
					lastPK = result.getInt("pk");
				}
				
				if(cardID < lastPK) {
					newPK = lastPK + 1;
				}
				else {
					newPK = cardID;
				}
				
			} catch (SQLException e) {
				System.out.println("CardService:  Failed to get new Primary Key.");
				e.printStackTrace();
			}
			return newPK;
		}
	
	
	@Override
	public void delete(int cardID) {	
		// format query
		String query = String.format(
					"DELETE FROM Card " +
					"WHERE cardID = %d",
					cardID
				);
		
		try {
			Statement statement = this.conn.createStatement();
			statement.executeUpdate(query);
			System.out.println("Card deleted.");
		} catch (SQLException e) {
			System.out.println("Failed to delete card.");
			e.printStackTrace();
		}
	}
}
