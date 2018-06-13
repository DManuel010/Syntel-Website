package com.syntinel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntinel.model.Customer;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String loadCustomerForm(ModelMap model)
	{
		
	}
}