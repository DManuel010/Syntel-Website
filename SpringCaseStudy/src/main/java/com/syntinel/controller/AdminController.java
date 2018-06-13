package com.syntinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.EmployeeService;
import com.syntinel.model.Employee;

@Controller
@RequestMapping("/admin")
@SessionAttributes("employee")
public class AdminController 
{
	@Autowired
	EmployeeService employeeServ;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView adminIndex(Model model) // should expect employee object from login in the session/model and be blocked by a session
	{
		//Employee employee = new Employee();
		//model.addAttribute("employee", employee);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_index");
		return modelAndView;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView viewAdminLogin(Model model)
	{
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_login");
		return modelAndView;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView adminLogin(@ModelAttribute("employee") Employee employee)
	{
		employee = employeeServ.getObject(employee);
		
		// need to do basic login validation here?
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("admin_index");
		return modelAndView;
	}
	
	

}
