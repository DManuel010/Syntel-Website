package main;

import java.sql.Connection;
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

	
	
	public static void main(String[] args){
		
		
		int choice = displayMainMenu();
		
		switch (choice){
		
			case 1: 
				displayLoginMenu();
				break;
			case 2:
				displayRegisterMenu();
				break;
			default: System.out.println("Invalid input");
			displayMainMenu();
			
		}
	}
	
	public static int displayMainMenu(){
		
		Scanner scan = new Scanner(System.in);
		System.out.println("-----Menu-----");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. View menu");
		int choice = scan.nextInt();
		scan.close();
		
		return choice;
	}
	
	public static void displayLoginMenu(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter email:");
		String email = scan.next();
		System.out.println("Enter password:");
		String password = scan.next();
		scan.close();
		System.out.println("Login Successful.");
	}
	
	public static void displayRegisterMenu(){
		DatabaseService dbService = new DatabaseService("restaurant", "restaurant", "localhost", "1521");
		Connection conn = dbService.getConnection();
		
		
		Scanner scan = new Scanner(System.in);

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
		
		scan.close();


		//INSERT THEIR ADDRESS IN LOCATION TABLE
		Location address = new Location(0, country, state, city, streetnum, null, zip);
		
		LocationService locationService = new LocationService(conn);
		locationService.insert(address);
		System.out.println();
		
		//INSERT THEIR CARD INFO INTO CARD TABLE
		Card card = new Card(0, cardName, cardNumber, expDate, cvv, type, address);
		
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
