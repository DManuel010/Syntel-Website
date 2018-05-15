package entities;

public class Location {
	private int locationID;
	private String country;
	private String state;
	private String city;
	private String street;
	private String room;
	private String zip;
	
	private static int locationCount;

	public Location(String country, String state, String city, String street, String room, String zip) {
		super();
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
		this.room = room;
		this.zip = zip;
		locationCount++;
	}

	public Location() {
		super();
		locationCount++;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public static int getLocationCount() {
		return locationCount;
	}

	public static void setLocationCount(int locationCount) {
		Location.locationCount = locationCount;
	}

	@Override
	public String toString() {
		return "Location [locationID=" + locationID + ", country=" + country + ", state=" + state + ", city=" + city
				+ ", street=" + street + ", room=" + room + ", zip=" + zip + "]";
	}
}
