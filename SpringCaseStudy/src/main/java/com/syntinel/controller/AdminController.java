package com.syntinel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView adminLogin()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_login");
		return modelAndView;
	}

}
