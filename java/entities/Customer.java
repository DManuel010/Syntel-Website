package entities;

import java.util.Date;

public class Customer extends User {

	private int customerID;
	private Date dateOfBirth;
	private int cardID;
	private Date dateOfRegister;
	private static int customerCount;
	
	
	public Customer(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, Date lastLogin, int customerID, Date dateOfBirth, int cardID, Date dateOfRegister) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin);
		this.customerID = customerID;
		this.dateOfBirth = dateOfBirth;
		this.cardID = cardID;
		this.dateOfRegister = dateOfRegister;
		customerCount++;
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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


	public static int getCustomerCount() {
		return customerCount;
	}


	public static void setCustomerCount(int customerCount) {
		Customer.customerCount = customerCount;
	}


	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", dateOfBirth=" + dateOfBirth + ", cardID=" + cardID
				+ ", dateOfRegister=" + dateOfRegister + ", toString()=" + super.toString() + "]";
	}


	@Override
	public void displayMenu() {
		System.out.println("\n---- Customer Menu ----\n");
		System.out.println("1)  Make Order");
		System.out.println("2)  Edit Order");
		System.out.println("3)  Delete Order");
		System.out.println("4)  View Order");
		System.out.println("5)  Print Order");
		System.out.println("6)  View Menu");
		System.out.println("7)  Log Out\n");
	}
}
