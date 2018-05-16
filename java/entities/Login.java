package entities;

public class Login {
	
	private int loginID;
	private String username;
	private String password;
	
	public Login() {
		super();
	}


	public Login(int loginID, String username, String password) {
		super();
		this.loginID = loginID;
		this.username = username;
		this.password = password;
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


	@Override
	public String toString() {
		return "Login [loginID=" + loginID + ", username=" + username + ", password=" + password + "]";
	}


}
