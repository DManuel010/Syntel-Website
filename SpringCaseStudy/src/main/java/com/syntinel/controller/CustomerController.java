package com.syntinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntinel.dao.CustomerService;
import com.syntinel.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController
{
	
	@Autowired
	CustomerService customerServ;
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String loadCustomerForm(ModelMap model)
	{
		
		Customer customer = new Customer();
	
		model.addAttribute("customer",customer);
		return "customer_registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerCustomer(Customer customer, BindingResult result) 	{

		customerServ.create(customer);
		return "customer_registration"; //will be changed to some other jsp file
	}

}
