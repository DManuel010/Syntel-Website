package com.syntinel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.CustomerService;
import com.syntinel.dao.FoodService;
import com.syntinel.dao.OrderService;
import com.syntinel.model.Customer;
import com.syntinel.model.Food;
import com.syntinel.model.Order;
import com.syntinel.validator.LoginValidator;

@Controller
@RequestMapping("/customer")
@SessionAttributes("customer")
public class LoginController 
{
	@Autowired
	CustomerService customerServ;
	
	@Autowired
	OrderService orderServ;
	
	@Autowired
	LoginValidator loginValidator;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		 binder.addValidators(loginValidator);
		 
    }
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loadCustomerLoginForm(Model model)
	{
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer_login";
	}
	
	@RequestMapping(value="/Dashboard", method=RequestMethod.POST)
	public ModelAndView customerDashboard()
	{
		
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("temp");
		 return modelAndView;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView customerLogin(@ModelAttribute("customer") Customer customer, BindingResult result, Model model)
	{
		customer = customerServ.getObject(customer);
		loginValidator.validate(customer, result);
		
		
		List<Order> orders = orderServ.viewMyOrders(customer.getId());
		
		
		if(result.hasErrors())
			return new ModelAndView("customer_login");
		else
		{
			 ModelAndView modelAndView = new ModelAndView();
			 modelAndView.addObject("customer", customer);
			 modelAndView.addObject("orders", orders);
			 modelAndView.setViewName("temp");
			 return modelAndView;
		}
	}
}
