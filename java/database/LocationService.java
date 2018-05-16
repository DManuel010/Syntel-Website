package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Location;

public class LocationService extends Service {

	public LocationService(Connection conn) {
		super();
	}
	
	@Override
	public void insert(Object obj) {
		Location location = (Location) obj;
		
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
					"VALUES(%d, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
					locationID, country, state, city, street, room, zip
				);
		System.out.println(query);
		try {
			Statement statement = this.conn.createStatement();
			statement.execute(query);
			System.out.println("LocationService:  Location insert successful.");	
		} catch (SQLException e) {
			System.out.println("LocationService:  Failed to insert location.");
			e.printStackTrace();
		}
	}
	
	
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
			System.out.println("LocationService:  Location deleted.");
		} catch (SQLException e) {
			System.out.println("LocationService:  Failed to delete location.");
			e.printStackTrace();
		}
	}
	
	
	// increment the primary key for new insertion
	private int getPK() {
		int lastPK = 0;
		String query = "SELECT MAX(locationID) AS pk " +
						"FROM Location";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
				lastPK++;
			}
			
		} catch (SQLException e) {
			System.out.println("LocationService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return lastPK;
	}
}
