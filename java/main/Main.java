package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import database.CardService;
import database.CustomerService;
import database.DatabaseService;
import database.LocationService;
import database.LoginService;
import entities.Admin;
import entities.Card;
import entities.Customer;
import entities.Driver;
import entities.Location;
import entities.Login;
import entities.SuperAdmin;
import entities.User;
import menus.LoginMenu;

public class Main {
	
	/*
	 * Gets the user's loginID based on the email and password.
	 */
	public static int getUserLoginID(Connection conn, String email, String password) {
		String query = "SELECT Login.loginID "
					+ "FROM Login "
					+ "WHERE Login.username = ? "
					+ "AND Login.password = ?";
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				int loginID = rs.getInt(1);
				return loginID;
			}
			
		} catch (SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/*
	 * Gets the user's title based on the loginID.
	 */
	public static String getUserTitle(Connection conn, int loginOption, int loginID) {
		if(loginOption == 1) {
			String query = "SELECT Employee.title "
						+ "FROM Employee "
						+ "WHERE Employee.loginID = ?";
			
			try {
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, loginID);
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					String title = rs.getString(1);
					return title;
				}
				
			} catch (SQLException e) {
				System.out.println("Database connection failed");
				e.printStackTrace();
			}
		}
		return "Customer";
	}
	
	
	/*
	 * Queries database for user info depending on the type of user and loginID.
	 * Returns the built object of the appropriate type.
	 */
	public static User getUser(Connection conn, int loginID, String userType) {
		if(userType.equals("Admin") ||
			userType.equals("SuperAdmin") ||
			userType.equals("Driver")) {

			String query = "SELECT * FROM Employee WHERE Employee.loginID = ?";
		
			try {
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, loginID);
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					int employeeID = rs.getInt(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);
					String email = rs.getString(4);
					String hireDate = rs.getString(5);
					String title = rs.getString(6);
					String phoneNumber = rs.getString(8);
					int workAddrID = rs.getInt(9);
					int homeAddrID = rs.getInt(10);
					String lastLogin = rs.getString(11);
					
					if(userType.equals("Admin")) {
						Admin admin = new Admin(email, firstName, lastName, 
												loginID, phoneNumber, homeAddrID,
												lastLogin, employeeID, hireDate,
												title, workAddrID);
						return admin;
					}
					else if(userType.equals("SuperAdmin")) {
						SuperAdmin superAdmin = new SuperAdmin(email, firstName, lastName,
																loginID, phoneNumber, homeAddrID,
																lastLogin, employeeID, hireDate,
																title, workAddrID);
						return superAdmin;
					}
					else if(userType.equals("Driver")) {
						Driver driver = new Driver(email, firstName, lastName,
													loginID, phoneNumber, homeAddrID,
													lastLogin, employeeID, hireDate,
													title, workAddrID);
						return driver;
					}
				}
				
			} catch (SQLException e) {
				System.out.println("Database connection failed");
				e.printStackTrace();
			}
		}
		
		else if(userType.equals("Customer")) {
			String query = "SELECT * FROM Customer WHERE Customer.loginID = ?";
			
			try {
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, loginID);
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					int customerID = rs.getInt(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);
					String email = rs.getString(4);
					String phoneNumber = rs.getString(5);
					String dateOfBirth = rs.getString(7);
					int homeAddrID = rs.getInt(8);
					int cardID = rs.getInt(9);
					String dateOfRegister = rs.getString(10);
					String lastLogin = rs.getString(11);
					
					Customer customer = new Customer(email, firstName, lastName,
													loginID, phoneNumber, homeAddrID,
													lastLogin, customerID, dateOfBirth,
													cardID, dateOfRegister);
					return customer;
				}
				
			} catch (SQLException e) {
				System.out.println("Database connection failed");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/*
	 * Register a new customer
	 */
	public static void register(Connection conn, Scanner input) {
		// Registration variables
		boolean registering;						// flag used for input validation while registering
		String userType;							// user type (Admin, SuperAdmin, Driver, Customer)
		String email = "";							// user login email
		String password = "";						// user login password
		String confirmPassword = null;				// used for password matching confirmation
		String country;								// user home country
		String state;								// user home state
		String city;								// user home city/town
		String streetNum;							// user home street address
		String roomNum;								// user home room number if applicable
		String zipCode;								// user home zip code
		String cardName;							// user full name on card
		String expirationDate;						// user expiration date on card
		String cardType;							// user type of card
		String cardNumber;							// user card number
		int cvv;									// user security (CVV) number on card
		int billingAddrID;							// pk of billing address for customer insertion
		int cardID;									// pk of card for customer insertion
		int loginID;								// pk of login for customer insertion
		int homeAddrID;								// pk of home address for customer insertion
		String firstName;							// user first name
		String lastName;							// user last name
		String phoneNumber;							// user phone number
		String dateOfBirth;							// user date of birth
		String dateOfRegister;						// user date of registration (current date)
		String lastLogin;							// user date of last login (current date)
		
		// Database Services
		LoginService loginService = new LoginService(conn);
		LocationService locationService = new LocationService(conn);
		CardService cardService = new CardService(conn);
		CustomerService customerService = new CustomerService(conn);
		
		registering = true;
		while(registering) {
			//Populate Login Table
			System.out.print("Enter your email: ");
			email = input.next();
			
			while(!password.equals(confirmPassword)) {
				System.out.print("Enter your password: ");
				password = input.next();
			
				System.out.print("Confirm password: ");
				confirmPassword = input.next();
			
				if(!password.equals(confirmPassword)) {
					System.out.println("Passwords do not match");
				}
			}
			userType = "customer";
			
			Login login = new Login(0, email, password, userType);
			loginService.insert(login);
			loginID = login.getLoginID();
			
			//Populate Location Table
			System.out.print("Enter your country: ");
			country = input.next();
			
			System.out.print("Enter your state: ");
			state = input.next();
			
			System.out.print("Enter your city/town: ");
			city = input.next();
			
			input.nextLine();
			System.out.print("Enter your street address: ");
			streetNum = input.nextLine();
			
			System.out.print("Enter your Apt/room # (or 'n/a'): ");
			roomNum = input.next();
			
			if(roomNum.equals("n/a")) {
				roomNum = null;
			}
			
			System.out.print("Enter your zip code: ");
			zipCode = input.next();
			
			Location homeAddr = new Location(0, country, state, city, streetNum, roomNum, zipCode);
			locationService.insert(homeAddr);
			homeAddrID = homeAddr.getLocationID();
			
			// Populate Card Table
			input.nextLine();
			System.out.print("Enter full name on credit card: ");
			cardName = input.nextLine();
			
			System.out.print("Enter card number (no spaces): ");
			cardNumber = input.next();
			
			System.out.print("Enter expiration date (MM/DD/YYYY): ");
			expirationDate = input.next();
			
			System.out.print("Enter the CVV: ");
			cvv = input.nextInt();
			
			System.out.print("Enter card type: ");
			cardType = input.next();
			
			System.out.print("Would you like to use your home address as your billing address? (Y/N): ");
			String addrChoice = input.next().toUpperCase();
			
			if(!addrChoice.equals("Y")) {
				System.out.print("Enter your country: ");
				country = input.next();
				
				System.out.print("Enter your state: ");
				state = input.next();
				
				System.out.print("Enter your city/town: ");
				city = input.next();
				
				input.nextLine();
				System.out.print("Enter your street address: ");
				streetNum = input.nextLine();
				
				System.out.print("Enter your Apt/room # (or 'n/a'): ");
				roomNum = input.next();
				
				if(roomNum.equals("n/a")) {
					roomNum = null;
				}
				
				System.out.print("Enter your zip code: ");
				zipCode = input.next();
				
				Location billingAddr = new Location(0, country, state, city, streetNum, roomNum, zipCode);
				locationService.insert(billingAddr);
				billingAddrID = billingAddr.getLocationID();
			}
			else {
				billingAddrID = homeAddr.getLocationID();
			}
			
			Card card = new Card(0, cardName, cardNumber, expirationDate, cvv, cardType, billingAddrID);
			cardService.insert(card);
			cardID = card.getCardID();
			
			// Populate Customer Table
			System.out.print("Enter your first name: ");
			firstName = input.next();
			
			System.out.print("Enter your last name: ");
			lastName = input.next();
			
			System.out.print("Enter your phone number: ");
			phoneNumber = input.next();
			
			System.out.print("Enter your date of birth (MM/DD/YYYY): ");
			dateOfBirth = input.next();
			
			dateOfRegister = "05-MAY-2018";
			
			lastLogin = dateOfRegister;
			
			Customer customer = new Customer(email, firstName, lastName, loginID,
											phoneNumber, homeAddrID, lastLogin, 0, dateOfBirth,
											cardID, dateOfRegister);
			customerService.insert(customer);
			System.out.println("Registration Successful!\n");
			
			registering = false;
		}
	}
	
	
	/*
	 * Login to existing employee or customer account
	 */
	public static void login(Connection conn, Scanner input) {
		boolean loggingIn;							// flag used for input validation while logging in
		boolean stillWorking;						// flag used for menu display
		int loginOption = 0;						// flag for type of user login (Employee, Customer)
		LoginMenu loginMenu = new LoginMenu();		// initialize login menu
		String email = "";							// user email for login
		String password = "";						// user password for login
		int loginID;								// pk for user login table
		String userType;							// type of user (Admin, SuperAdmin, Driver, Customer)
		
		// choose initial login option (Employee or Customer)
		loggingIn = true;
		while(loggingIn) {
			loginMenu.display();
			loginOption = input.nextInt();
			
			if(loginOption > 2 || loginOption < 1) {
				System.out.println("Not a valid login option.");
				System.out.println("Please select a choice from the following menu...");
			}
			else {
				loggingIn = false;
			}
		}
		
		// Enter login credentials (email and password)
		loggingIn = true;
		while(loggingIn) {

			System.out.print("E-Mail: ");
			email = input.next();
		
			System.out.print("Password: ");
			password = input.next();
			
			// check for blank email
			if(email.trim().length() > 0) {
				
				// check for blank password
				if(password.trim().length() > 0) { 
					loggingIn = false;
				}
				else {
					System.out.println("Please enter a password");
				}
			}
			else {
				System.out.println("Please enter an email");
			}
		}
		
		// determine user type and ID
		loginID = getUserLoginID(conn, email, password);
		userType = getUserTitle(conn, loginOption, loginID);
		
		// display appropriate menu
		User user = getUser(conn, loginID, userType);
		
		stillWorking = true;
		while(stillWorking) {
			stillWorking = user.displayMenu(input, conn);
		}
	}
	
	
	public static void main(String[] args) {
		// Application objects
		Scanner input = new Scanner(System.in);		// user input
		DatabaseService dbService;					// initialize database service to establish connection
		Connection conn;							// initialize connection to database
		
		// Database variables
		final String DB_USERNAME = "restaurant";	// database user
		final String DB_PASSWORD = "mummy";			// database password
		final String DB_SERVER = "localhost";		// database server
		final String DB_PORT = "1521";				// database port number
		
		
		// establish connection to database
		dbService = new DatabaseService(DB_USERNAME, DB_PASSWORD, DB_SERVER, DB_PORT);
		conn = dbService.getConnection();
		
		boolean stillWorking = true;
		while(stillWorking) {
			System.out.println("1)  Login");
			System.out.println("2)  Register");
			System.out.println("3)  Exit");
			System.out.print("Enter your choice: ");
			int choice = input.nextInt();
			
			if(choice == 1) {
				login(conn, input);
			}
			else if(choice == 2) {
				register(conn, input);
			}
			else if(choice == 3) {
				stillWorking = false;
			}
			else {
				System.out.println("Not a valid option");
			}
		}

		System.out.println("Quitting...");
		input.close();
		System.exit(0);
	}
}
