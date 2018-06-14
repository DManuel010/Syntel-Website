package com.syntinel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.CustomerService;
import com.syntinel.model.Customer;

@Controller
@RequestMapping("/admin")
@SessionAttributes("employee")
public class AdminCustomerController 
{
	@Autowired
	CustomerService custService;
	
	// should be blocked by session (this is true for all admin pages)
	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public ModelAndView getFoodItems()
	{
		List<Customer> customers = custService.viewAll();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customers", customers);
		modelAndView.setViewName("admin_customers");
		return modelAndView;
	}
	
}
