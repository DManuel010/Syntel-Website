package main;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import database.CustomerService;
import database.DatabaseService;
import database.LoginService;
import entities.Customer;
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
		
		return choice;
	}
	
	public static void displayLoginMenu(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter email:");
		String email = scan.next();
		System.out.println("Enter password:");
		String password = scan.next();
		scan.close();
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
		System.out.println("Enter home address ID:");
		int homeAddressID = scan.nextInt();
		System.out.println("Enter card ID:");
		int cardID = scan.nextInt();
		System.out.println("Enter phone number:");
		String phoneNumber = scan.next();
		scan.close();

		
		Login newUser = new Login();
		newUser.setPassword(password);
		newUser.setUsername(email);
		newUser.setLoginID(0);
		
		LoginService loginService = new LoginService(conn);
		CustomerService customerService = new CustomerService(conn);
		
		loginService.insert(newUser);
		System.out.println("Added to the login table.");
		
		System.out.println("Login ID="+newUser.getLoginID());
		
		Customer customer = new Customer(firstname, lastname, email, newUser.getLoginID(), phoneNumber, homeAddressID, "24-MAY-18");
		
		
		customer.setDateOfBirth(dateOfBirth);
		customer.setDateOfRegister("24-MAY-18");
		customer.setCustomerID(0);
		customer.setCardID(cardID);
		
		
		customerService.insert(customer);
		System.out.println("You have been registered.");
		
	}
	
}
