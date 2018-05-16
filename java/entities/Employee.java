package caseStudy;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {

	
	int employeeID;
	String firstName;
	String LastName;
	Date HireDate;
	String title;
	int LoginID;
	String email;
	int phoneNumber;
	int workAddID;
	int homeAddID;
	Date LastLogin;
	public Employee(int employeeID, String firstName, String lastName, Date hireDate, String title, int loginID,
			String email, int phoneNumber, int workAddID, int homeAddID, Date lastLogin) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		LastName = lastName;
		HireDate = hireDate;
		this.title = title;
		LoginID = loginID;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.workAddID = workAddID;
		this.homeAddID = homeAddID;
		LastLogin = lastLogin;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public Date getHireDate() {
		return HireDate;
	}
	public void setHireDate(Date hireDate) {
		HireDate = hireDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLoginID() {
		return LoginID;
	}
	public void setLoginID(int loginID) {
		LoginID = loginID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getWorkAddID() {
		return workAddID;
	}
	public void setWorkAddID(int workAddID) {
		this.workAddID = workAddID;
	}
	public int getHomeAddID() {
		return homeAddID;
	}
	public void setHomeAddID(int homeAddID) {
		this.homeAddID = homeAddID;
	}
	public Date getLastLogin() {
		return LastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", LastName=" + LastName
				+ ", HireDate=" + HireDate + ", title=" + title + ", LoginID=" + LoginID + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", workAddID=" + workAddID + ", homeAddID=" + homeAddID
				+ ", LastLogin=" + LastLogin + "]";
	}
	public void getCustomerInfo(){
		
		
			
		}
	public void insertCustomerInfo(){
		
			}
		
	public void updateCustomerInfo(){
		
			//THIS METHOD WILL UPDATE EXISTING CUSTOMER DATA
		//WITH NEW DATA THAT HAS BEEN SUBMITTED
		}
	}

	
	

