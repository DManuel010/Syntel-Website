package com.syntinel.model;

public class Customer
{
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String confirm_password;
	private String dob;
	private String last_login;
	private String register_date;
	private String home_number;
	private String mobile_number;
//	private Address address;
	
	//Default constructor
	public Customer() {}
	
	//Getters
	public String getFirst_name() 		{return first_name;}
	public String getLast_name() 	 	{return last_name;}
	public String getEmail() 			{return email;}
	public String getPassword() 		{return password;}
	public String getConfirm_password() {return confirm_password;}
	public String getDob() 				{return dob;}
	public String getLast_login() 		{return last_login;}
	public String getRegister_date() 	{return register_date;}
	public String getHome_number() 		{return home_number;}
	public String getMobile_number() 	{return mobile_number;}
	
	
	//Setters
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public void setHome_number(String home_number) {
		this.home_number = home_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	
	
	
	
	
	
	
	
}
