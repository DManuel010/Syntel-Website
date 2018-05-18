package entities;

import java.util.Date;

public abstract class User {
	
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int loginID;
	private String phoneNumber;
	private int homeAddrID;
	private Date lastLogin;
	private static int userCount;
	
	public User(String email, String password, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, Date lastLogin) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginID = loginID;
		this.phoneNumber = phoneNumber;
		this.homeAddrID = homeAddrID;
		this.lastLogin = lastLogin;
		userCount++;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public static int getUserCount() {
		return userCount;
	}

	public static void setUserCount(int userCount) {
		User.userCount = userCount;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", loginID=" + loginID + ", phoneNumber=" + phoneNumber + ", homeAddrID=" + homeAddrID
				+ ", lastLogin=" + lastLogin + "]";
	}

	public abstract void displayMenu();
}
