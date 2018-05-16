package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import entities.Card;

public class CardService extends Service {
	
	public CardService(Connection conn) {
		super();
	}
	
	public void insert(Object obj) {
		Card card = (Card) obj;
		
		// get card values
		int cardID;
		if(card.getCardID() == 0) {
			cardID = getPK();
			card.setCardID(cardID);
		}
		else {
			cardID = card.getCardID();
		}
		
		String cardName = card.getName();
		String cardNumber = card.getNumber();
		Date expirationDate = card.getExpirationDate();
		int cvv = card.getcvv();
		String type = card.getType();
		int billingAddrID = card.getBillingAddr().getLocationID();
		
		// format query
		String query = String.format(
					"INSERT INTO Card " +
					"(name, cardNumber, expirationDate, cvv, type, billingAddrID) " +
					"VALUES('%d', '%s', '%s', '%s', %d, '%s', %d)",
					cardID, cardName, cardNumber, expirationDate, cvv, type, billingAddrID
				);
		
		try {
			Statement statement = this.conn.createStatement();						// build statement
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);	// execute query
			System.out.println("Card inserted.");
		} catch (SQLException e) {
			System.out.println("Failed to insert card.");
			e.printStackTrace();
		}
	}
	
	
	private int getPK() {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(cardID) FROM Card";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				lastPK = result.getInt("cardID");
			}
			newPK = lastPK + 1;
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
		}
		return newPK;
	}
	
	
	// Delete matching primary key
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
