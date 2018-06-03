package com.syntinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntinel.dao.CustomerService;
import com.syntinel.dao.LoginService;
import com.syntinel.model.Customer;
import com.syntinel.model.Login;

@Controller
@RequestMapping("/customer")
public class CustomerController
{
	
	@Autowired
	CustomerService customerServ;
	
	@Autowired
	LoginService loginServ;
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String loadCustomerForm(ModelMap model)
	{
		
		Customer customer = new Customer();
	
		model.addAttribute("customer",customer);
		
		return "customer_registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerCustomer(Customer customer, Login login, BindingResult result) 	{

		customerServ.create(customer);
		login.setLoginId(1);
		login.setType("customer");
		login.setUsername(customer.getEmail());
		login.setPassword(customer.getPassword());
		loginServ.create(login);
		return "customer_registration"; //will be changed to some other jsp file
	}

}
