package entities;

import java.sql.Connection;
import java.util.Scanner;

public abstract class User {
	
	private String firstName;
	private String lastName;
	private String email;
	private int loginID;
	private String phoneNumber;
	private int homeAddrID;
	private String lastLogin;
	
	private static int userCount;
	
	public User(String firstName, String lastName, String email, int loginID, String phoneNumber,
			int homeAddrID, String lastLogin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.loginID = loginID;
		this.phoneNumber = phoneNumber;
		this.homeAddrID = homeAddrID;
		this.lastLogin = lastLogin;
		userCount++;
	}
	
	public User() {
		super();
		userCount++;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getLoginID() {
		return loginID;
	}

	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getHomeAddrID() {
		return homeAddrID;
	}

	public void setHomeAddrID(int homeAddrID) {
		this.homeAddrID = homeAddrID;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public static int getUserCount() {
		return userCount;
	}

	public static void setUserCount(int userCount) {
		User.userCount = userCount;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", loginID=" + loginID + ", phoneNumber=" + phoneNumber + ", homeAddrID=" + homeAddrID
				+ ", lastLogin=" + lastLogin + "]";
	}

	public abstract boolean displayMenu(Scanner input, Connection conn);
}
