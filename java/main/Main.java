package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import database.DatabaseService;
import entities.Admin;
import entities.Customer;
import entities.Driver;
import entities.SuperAdmin;
import entities.User;
import menues.LoginMenu;

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
					Date hireDate = rs.getDate(5);
					String title = rs.getString(6);
					String phoneNumber = rs.getString(8);
					int workAddrID = rs.getInt(9);
					int homeAddrID = rs.getInt(10);
					Date lastLogin = rs.getDate(11);
					
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
					Date dateOfBirth = rs.getDate(7);
					int homeAddrID = rs.getInt(8);
					int cardID = rs.getInt(9);
					Date dateOfRegister = rs.getDate(10);
					Date lastLogin = rs.getDate(11);
					
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
	
	
	public static void main(String[] args) {
		
		// Application objects
		Scanner input = new Scanner(System.in);		// user input
		LoginMenu loginMenu = new LoginMenu();		// initialize login menu
		DatabaseService dbService;					// initialize database service to establish connection
		Connection conn;							// initialize connection to database
		
		// User variables
		boolean loggingIn;							// flag used for input validation while logging in
		String email = "";							// user login email
		String password = "";						// user login password
		int loginOption = 0;							// flag for type of user login (Employee, Customer)
		String userType;							// user type (Admin, SuperAdmin, Driver, Customer)
		int loginID;								// primaryKey for user login table
		
		// Database variables
		final String DB_USERNAME = "restaurant";	// database user
		final String DB_PASSWORD = "mummy";			// database password
		final String DB_SERVER = "localhost";		// database server
		final String DB_PORT = "1521";				// database port number
		
		
		// establish connection to database
		dbService = new DatabaseService(DB_USERNAME, DB_PASSWORD, DB_SERVER, DB_PORT);
		conn = dbService.getConnection();
		
		
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
		System.out.println(user);
		user.displayMenu();

		input.close();
		System.exit(1);
	}
}
