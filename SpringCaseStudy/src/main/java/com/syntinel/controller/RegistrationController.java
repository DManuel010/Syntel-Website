package com.syntinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntinel.dao.CustomerService;
import com.syntinel.model.Customer;
import com.syntinel.validator.RegistrationValidator;

@Controller
@RequestMapping("/customer")
public class RegistrationController
{
	
	@Autowired
	CustomerService customerServ;
	
	@Autowired
	RegistrationValidator customerValidator;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		 binder.addValidators(customerValidator);
		 
    }
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String loadCustomerForm(ModelMap model)
	{
		Customer customer = new Customer();	
		model.addAttribute("customer", customer);
		return "customer_registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerCustomer(@ModelAttribute("customer") @Validated Customer customer, BindingResult result, Model model)
	{
		
		if(result.hasErrors())
			return "customer_registration";
		else
		{
			customerServ.create(customer);
			
			return "redirect:login";
		}
	}
	
	
	

}
