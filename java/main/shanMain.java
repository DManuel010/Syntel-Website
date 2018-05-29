package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import database.CardService;
import database.CustomerService;
import database.DatabaseService;
import database.LocationService;
import database.LoginService;
import entities.Card;
import entities.Customer;
import entities.Location;
import entities.Login;
import entities.User;

public class shanMain {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args){
		displayMainMenu();
	}
	
	public static void displayMainMenu(){
		boolean choosing = true;
		int choice=0;
		
		while(choosing){
			System.out.println("-----Menu-----");
			System.out.println("1. Login");
			System.out.println("2. Register");
			choice = scan.nextInt();
			
			if(choice==1||choice==2){
				choosing=false;
			}else{
				System.out.println("Invalid input. Please choose from the menu.");
			}
		}

		switch (choice){
		
		case 1: 
			displayLoginMenu();
			break;
		case 2:
			displayRegisterMenu();
			break;
		}
	}
	
	public static void displayLoginMenu(){
		DatabaseService dbService = new DatabaseService("restaurant", "restaurant", "localhost", "1521");
		Connection conn = dbService.getConnection();
		
		System.out.println("Enter email:");
		String email = scan.next();
		System.out.println("Enter password:");
		String password = scan.next();
		
		
		LoginService loginService = new LoginService(conn);
		
		boolean exists = loginService.view(email);
		
		if(exists){
			System.out.println("Login successful");
			
		}else{
			System.out.println("Email not found in database. Try again or register.");
			displayMainMenu();
		}
	}
	
	public static void displayRegisterMenu(){
		DatabaseService dbService = new DatabaseService("restaurant", "restaurant", "localhost", "1521");
		Connection conn = dbService.getConnection();


		System.out.println("Enter email:");
		String email = scan.next();
		System.out.println("Enter password:");
		String password = scan.next();
		System.out.println("Enter first name:");
		String firstname = scan.next();
		System.out.println("Enter lastname:");
		String lastname = scan.next();
		System.out.println("Enter date of birth dd-MMM-yy:");
		String dateOfBirth = scan.next();
		System.out.println("Enter country:");
		String country = scan.next();
		System.out.println("Enter state:");
		String state = scan.next();
		System.out.println("Enter city:");
		String city = scan.next();
		System.out.println("Enter street number:");
		String streetnum = scan.next();
		System.out.println("Enter zip code:");
		String zip = scan.next();
		System.out.println("Enter phone number:");
		String phoneNumber = scan.next();
		System.out.println("Enter name on card:");
		String cardName = scan.next();
		System.out.println("Enter card number:");
		String cardNumber = scan.next();
		System.out.println("Enter expiration date:");
		String expDate = scan.next();
		System.out.println("Enter CVV:");
		int cvv = scan.nextInt();
		System.out.println("Enter card type:");
		String type = scan.next();


		//INSERT THEIR ADDRESS IN LOCATION TABLE
		Location address = new Location(0, country, state, city, streetnum, null, zip);
		int addrID = address.getLocationID();
		
		LocationService locationService = new LocationService(conn);
		locationService.insert(address);
		System.out.println();
		
		//INSERT THEIR CARD INFO INTO CARD TABLE
		Card card = new Card(0, cardName, cardNumber, expDate, cvv, type, addrID);
		
		CardService cardService = new CardService(conn);
		cardService.insert(card);
		System.out.println();

		Login newUser = new Login();
		
		newUser.setPassword(password);
		newUser.setUsername(email);
		newUser.setLoginID(0);
		
		LoginService loginService = new LoginService(conn);
		CustomerService customerService = new CustomerService(conn);
		
		loginService.insert(newUser);
		System.out.println();
		
		Customer customer = new Customer(firstname, lastname, email, newUser.getLoginID(), phoneNumber, address.getLocationID(), "24-MAY-18");
		
		customer.setDateOfBirth(dateOfBirth);
		customer.setDateOfRegister("24-MAY-18");
		customer.setCustomerID(0);
		customer.setCardID(card.getCardID());
		
		
		customerService.insert(customer);
		System.out.println("You have been registered. Username: "+newUser.getUsername());
		System.out.println("Password: "+newUser.getPassword());
		System.out.println("Please login.");
		System.out.println();
		displayMainMenu();
		
	}
	
}
