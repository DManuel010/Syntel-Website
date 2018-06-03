package com.syntinel.model;

import javax.validation.constraints.*;

public class Location 
{
	@Size(min=3, max=15)
	@NotEmpty
	private String country;
	
	@Size(min=2, max=15)
	@NotEmpty
	private String state;
	
	@Size(min=5, max=15)
	@NotEmpty
	private String city;
	
	@Size(min=5, max=100)
	@NotEmpty
	private String street_number;
	
	@Size(min=3, max=5)
	private String room_number;
	
	@Size(min=5, max=10)
	@NotEmpty
	private String zip_code;
	
	//Constructors
	public Location(){}
	
	//Getters
	public String getCountry() 			{return country;}
	public String getState() 			{return state;}
	public String getCity() 			{return city;}
	public String getStreet_number() 	{return street_number;}
	public String getRoom_number()		{return room_number;}
	public String getZip_code() 		{return zip_code;}
	
	
	//Setters
	public void setCountry(String country) {
		this.country = country;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
	@Override
	public String toString() {
		return "Location [country=" + country + ", state=" + state + ", city=" + city + ", street_number="
				+ street_number + ", room_number=" + room_number + ", zip_code=" + zip_code + "]";
	}
	
}
