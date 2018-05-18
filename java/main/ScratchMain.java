package main;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import database.CardService;
import database.DatabaseService;
import database.LocationService;
import entities.Card;
import entities.Location;

public class DrewMain {
	public static void insertLocation(Connection conn, Location location) {
		LocationService locationService = new LocationService(conn);
		locationService.insert(location);
		System.out.println(location.getLocationID());
	}
	
	
	public static Date buildDate(int year, int month, int day) {
		Calendar myCalendar = new GregorianCalendar(year, month, day);
		Date dateObject = myCalendar.getTime();
		return dateObject;
	}
	
	
	public static void insertCard(Connection conn, Card card, Location location, Date date) {
		CardService cardService = new CardService(conn);
		cardService.insert(card);
		System.out.println(card.getCardID());
	}
	
	
	public static void main(String[] args) {
		// database credentials
		String username = "restaurant";
		String password = "mummy";
		String server = "localhost";
		String port = "1521";
		
		// connect to database
		DatabaseService dbService = new DatabaseService(username, password, server, port);
		Connection conn = dbService.getConnection();
		
		// Insert new location
		Location location = new Location("USA", "Arizona", "Phoenix", "20827 N 27th Avenue", "Room 114", "85027");
		insertLocation(conn, location);

		// Insert new card
		Date date = buildDate(2000, 2, 15);
		Card card = new Card("Michael Jackson", "1123223433454456", date, 123, "Visa", location);
		insertCard(conn, card, location, date);
	}
}
