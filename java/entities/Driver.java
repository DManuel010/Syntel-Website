package entities;

import java.util.Date;

public class Driver extends Employee {
	
	private static int driverCount;
	
	

	public Driver(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, Date lastLogin, int empID, Date hireDate, String title, int workAddrID) {
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
	public void displayMenu() {
		System.out.println("\n---- Driver Menu ----\n");
		System.out.println("1) View Orders");
		System.out.println("2) Complete Orders");
		System.out.println("3) Log Out\n");
	}
}
