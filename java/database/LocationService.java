package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Location;

public class LocationService extends Service {

	public LocationService(Connection conn) {
		super(conn);
	}
	
	
	@Override
	public void insert(Object obj) {
		Location location = (Location) obj;
		
		// get location values
		int locationID = getPK(location.getLocationID());
		location.setLocationID(locationID);

		String country = location.getCountry();
		String state = location.getState();
		String city = location.getCity();
		String street = location.getStreet();
		String room = location.getRoom();
		String zip = location.getZip();
		
		// format query
		String query = "INSERT INTO Location "
					+ "(locationID, country, state, city, streetNum, roomNum, zip) " 
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, locationID);
			statement.setString(2, country);
			statement.setString(3, state);
			statement.setString(4, city);
			statement.setString(5, street);
			statement.setString(6, room);
			statement.setString(7, zip);
			statement.addBatch();
			statement.executeBatch();
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
	private int getPK(int locationID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(locationID) AS pk " +
						"FROM Location";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(locationID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = locationID;
			}
			
		} catch (SQLException e) {
			System.out.println("LocationService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}
}
