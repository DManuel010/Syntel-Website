package com.syntinel.model;

import java.util.List;

import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.syntinel.utilities.Utilities;

public class Customer
{
	private String id = null;
	
	@Size(min=3, max=50)
	@NotEmpty
	private String first_name;
	
	@Size(min=3, max=50)
	@NotEmpty
	private String last_name;
	
	@Email
	@NotEmpty
	private String email;
	
	@Size(min=5, max=10)
	@NotEmpty
	private String password;
	
	@Size(min=5, max=10)
	@NotEmpty
	private String confirm_password;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@NotEmpty
	private String dob;
	
	@Size(min=9, max=25)
	@NotEmpty
	private String home_number;
	
	@Size(min=9, max=25)
	@NotEmpty
	private String mobile_number;
	
	private String last_login;
	private String register_date;
	
	private List<Food> items;
	
	
	public List<Food> getItems() {
		return items;
	}

	public void setItems(List<Food> items) {
		this.items = items;
	}

	//Default constructor
	public Customer() 
	{
		this.setRegister_date(Utilities.getToday());
		this.setLast_login(Utilities.getToday());
	}
	
	//Getters
	public String getId() 				{return id;}
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
	
	public void setId(String id) {
		this.id = id;
	}

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
	
	public void setHome_number(String home_number) {
		this.home_number = home_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	
	public void setLast_login(String last_login) 		{this.last_login = last_login;}
	private void setRegister_date(String register_date) {this.register_date = register_date;}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", confirm_password=" + confirm_password + ", dob=" + dob
				+ ", home_number=" + home_number + ", mobile_number=" + mobile_number + ", last_login=" + last_login
				+ ", register_date=" + register_date + ", items=" + items + "]";
	}

	

	
}
