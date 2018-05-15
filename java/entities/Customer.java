
package entities;
import java.util.Date;


public class Customer {
	
	private int customerID;
	private String firstName;
	private String lastName;
	private String email;
	private int loginID;
	private Date dateOfBirth;
	private int homeAddrID;
	private int cardID;
	private Date dateOfRegister;
	private Date lastLogin;
	
	public Customer() {
		super();
	}

	public Customer(int customerID, String firstName, String lastName, String email, int loginID, Date dateOfBirth,
			int homeAddrID, int cardID, Date dateOfRegister, Date lastLogin) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.loginID = loginID;
		this.dateOfBirth = dateOfBirth;
		this.homeAddrID = homeAddrID;
		this.cardID = cardID;
		this.dateOfRegister = dateOfRegister;
		this.lastLogin = lastLogin;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLoginID() {
		return loginID;
	}

	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getHomeAddrID() {
		return homeAddrID;
	}

	public void setHomeAddrID(int homeAddrID) {
		this.homeAddrID = homeAddrID;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public Date getDateOfRegister() {
		return dateOfRegister;
	}

	public void setDateOfRegister(Date dateOfRegister) {
		this.dateOfRegister = dateOfRegister;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", loginID=" + loginID + ", dateOfBirth=" + dateOfBirth + ", homeAddrID=" + homeAddrID
				+ ", cardID=" + cardID + ", dateOfRegister=" + dateOfRegister + ", lastLogin=" + lastLogin + "]";
	}
	
	
	

}
