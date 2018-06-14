package com.syntinel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController 
{
	
	@RequestMapping(value="/about")
	public String loadAbout(Model model)
	{
		return "about";
	}
	
	@RequestMapping(value="/contact")
	public String loadContact(Model model)
	{
		return "contact";
	}
	
	
}