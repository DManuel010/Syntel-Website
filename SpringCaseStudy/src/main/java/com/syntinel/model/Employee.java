package com.syntinel.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Employee {

	@NotEmpty
	private String employeeId;
	private String firstName;
	private String lastName;
	
	@Email
	@NotEmpty
	private String email;
	private String hireDate;
	private String title;
	private String phoneNumber;
	private int workAddrId;
	private int homeAddrId;
	private String lastLogin;
	
	public Employee() 
	{
		this.setTitle(null);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getWorkAddrId() {
		return workAddrId;
	}

	public void setWorkAddrId(int workAddrId) {
		this.workAddrId = workAddrId;
	}

	public int getHomeAddrId() {
		return homeAddrId;
	}

	public void setHomeAddrId(int homeAddrId) {
		this.homeAddrId = homeAddrId;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", hireDate=" + hireDate + ", title=" + title + ", phoneNumber=" + phoneNumber
				+ ", workAddrId=" + workAddrId + ", homeAddrId=" + homeAddrId + ", lastLogin=" + lastLogin + "]";
	}
	
	
	
}
