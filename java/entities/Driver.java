package entities;

import java.sql.Connection;
import java.util.Scanner;

public class Driver extends Employee {
	
	private static int driverCount;

	public Driver(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, String lastLogin, int empID, String hireDate, String title, int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin, empID, hireDate, title,
				workAddrID);
		driverCount++;
	}

	public static int getDriverCount() {
		return driverCount;
	}

	public static void setDriverCount(int driverCount) {
		Driver.driverCount = driverCount;
	}

	@Override
	public String toString() {
		return "Driver [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean displayMenu(Scanner input, Connection conn) {
		System.out.println("\n---- Driver Menu ----\n");
		System.out.println("1) View Orders");
		System.out.println("2) Complete Orders");
		System.out.println("3) Log Out\n");
		
		return false;
	}
}
