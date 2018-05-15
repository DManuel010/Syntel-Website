package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Location;
import interfaces.Service;

public class LocationService implements Service {
	Connection conn;

	public LocationService(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public void insert(Location location) {
		// get location values
		int locationID;
		if(location.getLocationID() == 0) {
			locationID = getPK();
			location.setLocationID(locationID);
		}
		else {
			locationID = location.getLocationID();
		}
		
		String country = location.getCountry();
		String state = location.getState();
		String city = location.getCity();
		String street = location.getStreet();
		String room = location.getRoom();
		String zip = location.getZip();
		
		// format query
		String query = String.format(
					"INSERT INTO Location " + 
					"(locationID, country, state, city, streetNum, roomNum, zip) " +
					"VALUES('%d', '%s', '%s', '%s', '%s', '%s', '%s')",
					locationID, country, state, city, street, room, zip
				);
		
		try {
			Statement statement = this.conn.createStatement();						// build statement
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);	// execute query
			System.out.println("Location inserted.");
		} catch (SQLException e) {
			System.out.println("Failed to insert location.");
			e.printStackTrace();
		}
	}
	
	// increment the primary key for new insertion
	private int getPK() {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(locationID) AS 'pk' FROM Location";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				lastPK = result.getInt("locationID");
			}
			
			newPK = lastPK + 1;
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
		}
		return newPK;
	}
	
	
	// Delete matching primary key
	@Override
	public void delete(int locationID) {	
		String query = String.format(
					"DELETE FROM Location " +
					"WHERE cardID = %d",
					locationID
				);
		
		try {
			Statement statement = this.conn.createStatement();
			statement.executeUpdate(query);
			System.out.println("Location deleted.");
		} catch (SQLException e) {
			System.out.println("Failed to delete location.");
			e.printStackTrace();
		}
	}
}
