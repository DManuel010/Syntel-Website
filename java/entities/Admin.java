package entities;

import java.sql.Connection;
import java.util.Scanner;

public class Admin extends Employee {

	private static int adminCount;

	public Admin(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, String lastLogin, int empID, String hireDate, String title, int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin, empID, hireDate, title,
				workAddrID);
		adminCount++;
	}

	public static int getAdminCount() {
		return adminCount;
	}

	public static void setAdminCount(int adminCount) {
		Admin.adminCount = adminCount;
	}

	@Override
	public String toString() {
		return "Admin [toString()=" + super.toString() + "]";
	}

	@Override
	public boolean displayMenu(Scanner input, Connection conn) {
		System.out.println("\n---- Admin Menu ----\n");
		System.out.println("1)  View Orders");
		System.out.println("2)  Add Orders");
		System.out.println("3)  Edit Orders");
		System.out.println("4)  Complete Orders");
		System.out.println("5)  Delete Orders");
		System.out.println("6)  View Employees");
		System.out.println("7)  View Customers");
		System.out.println("8)  Issue Refund");
		System.out.println("9)  Log Out\n");
		
		return false;
	}
}
