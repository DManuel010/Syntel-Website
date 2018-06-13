package com.syntinel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.CustomerService;
import com.syntinel.dao.FoodService;
import com.syntinel.model.Customer;
import com.syntinel.model.Food;

@Controller
@RequestMapping("/admin")
@SessionAttributes("employee")
public class AdminCustomerController 
{
	@Autowired
	CustomerService custService;
	
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
