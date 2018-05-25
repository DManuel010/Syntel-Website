package main;

import java.sql.Connection;
import java.sql.SQLException;

import database.CardService;
import database.DatabaseService;
import database.LocationService;
import entities.Card;
import entities.Location;

public class ScratchMain {
	public static void insertLocation(Connection conn, Location location) {
		LocationService locationService = new LocationService(conn);
		locationService.insert(location);
	}
	
	public static void insertCard(Connection conn, Card card, Location location, String date) {
		CardService cardService = new CardService(conn);
		cardService.insert(card);
		System.out.println(card.getCardID());
	}
	
	
	public static void main(String[] args) {
		// database credentials
		//String username = "restaurant";
		//String password = "mummy";
		String username = "deus";
		String password = "1234";
		String server = "172.17.33.12";
		String port = "1521";
		
		// connect to database
		DatabaseService dbService = new DatabaseService(username, password, server, port);
		Connection conn = dbService.getConnection();
		
		// Insert new location
		Location location = new Location(0, "USA", "Arizona", "Phoenix", "20827 N 27th Avenue", "Room 111", "85027");
		insertLocation(conn, location);

		// Insert new card
		String date = "04/02/2020";
		Card card = new Card(0, "Michael Jackson", "1123223433454456", date, 123, "Visa", 0);
		insertCard(conn, card, location, date);
		
		try {
			conn.close();
			System.out.println("Connection terminated.");
		} catch (SQLException e) {
			System.out.println("Could not close connection.");
			e.printStackTrace();
		}
	}
}
