package com.syntinel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntinel.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController
{
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String loadCustomerForm(ModelMap model)
	{
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "placeholder";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registerCustomer() 
	{
		return "placeholder";
	}

}
