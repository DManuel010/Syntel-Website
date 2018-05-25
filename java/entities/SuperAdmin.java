package entities;

import java.sql.Connection;
import java.util.Scanner;

public class SuperAdmin extends Admin {
	
	private static int superAdminCount;
	
	
	public SuperAdmin(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, String lastLogin, int empID, String hireDate, String title, int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin, empID, hireDate, title,
				workAddrID);
		superAdminCount++;
	}


	public static int getSuperAdminCount() {
		return superAdminCount;
	}


	public static void setSuperAdminCount(int superAdminCount) {
		SuperAdmin.superAdminCount = superAdminCount;
	}


	@Override
	public String toString() {
		return "SuperAdmin [toString()=" + super.toString() + "]";
	}


	@Override
	public boolean displayMenu(Scanner input, Connection conn) {
		System.out.println("\n---- Super Admin Menu ----\n");
		System.out.println("1)  View Orders");
		System.out.println("2)  Add Orders");
		System.out.println("3)  Edit Orders");
		System.out.println("4)  Complete Orders");
		System.out.println("5)  Delete Orders");
		System.out.println("6)  View Employees");
		System.out.println("7)  Add Employee");
		System.out.println("8)  Remove Employee");
		System.out.println("9)  Edit Employee");
		System.out.println("10) View Customers");
		System.out.println("11) Issue Refund");
		System.out.println("12) Log Out\n");
		
		return false;
	}
}
