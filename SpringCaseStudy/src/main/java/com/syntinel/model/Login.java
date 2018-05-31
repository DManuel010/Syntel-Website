package com.syntinel.model;

public class Login {
	
	private int loginID;
	private String username;
	private String password;
	private String type;
	
	private static int loginCount;
	
	public Login() {
		super();
		loginCount++;
	}


	public Login(int loginID, String username, String password, String type) {
		super();
		this.loginID = loginID;
		this.username = username;
		this.password = password;
		this.type = type;
		loginCount++;
	}


	public int getLoginID() {
		return loginID;
	}


	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static int getLoginCount() {
		return loginCount;
	}

	public static void setLoginCount(int loginCount) {
		Login.loginCount = loginCount;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Login [loginID=" + loginID + ", username=" + username + ", password=" + password + ", type=" + type
				+ "]";
	}
}
