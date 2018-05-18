package main;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import entities.Admin;

public class Main {
	
	public static Date buildDate(int year, int month, int day) {
		Calendar myCalendar = new GregorianCalendar(year, month, day);
		Date dateObject = myCalendar.getTime();
		return dateObject;
	}
	
	
	// Validates user login credentials and returns employee type
	public static boolean validateLogin(int loginOption, String email, String password) {
		System.out.println("\nLogin Successful\n");
		return true;
	}
	
	
	// Displays the main menu and collects user input
	public static void displayMainMenu(String userType) {
		if(userType.equals("Customer")) {

		}
		
		else if(userType.equals("Driver")) {

		}
		
		else if(userType.equals("Admin")) {
			Date hireDate = buildDate(2018, 05, 02);
			
			Admin admin = new Admin("drew@manuel.com", "drew123", "Drew", "Manuel", 1, "413-505-3397", 1, null, 1, hireDate, "Admin", 1);
			admin.displayMenu();
		}
		
		else if(userType.equals("Super Admin")) {

		}
		
		else {
			System.out.println("\nNo menu found.");
		}
	}
	
	
	public static String getUserType(String email, String password) {
		return "Admin";
		//return "driver";
		//return "SuperAdmin";
		//return "customer";
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n---- Restaurant Utility Login ----\n");
		System.out.println("1) Employee Login");
		System.out.println("2) Customer Login");
		System.out.print("\nLogin option: ");
		int loginOption = input.nextInt();
		
		System.out.print("E-Mail: ");
		String email = input.next();
		
		System.out.print("Password: ");
		String password = input.next();
		
		boolean login = validateLogin(loginOption, email, password);
		
		if(login) {
			String userType = getUserType(email, password);
			displayMainMenu(userType);
			System.out.print("Menu Option: ");
			int mainMenuOption = input.nextInt();
		}
		input.close();
	}
}
