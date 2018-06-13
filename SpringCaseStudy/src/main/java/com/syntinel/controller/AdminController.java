package com.syntinel.controller;

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

import com.syntinel.dao.EmployeeService;
import com.syntinel.model.Employee;
import com.syntinel.validator.AdminValidator;

@Controller
@RequestMapping("/admin")
@SessionAttributes("employee")
public class AdminController 
{
	@Autowired
	EmployeeService employeeServ;
	
	// technically shouldn't be able to get here but adding it anyway for admin landing page
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView adminIndex(Model model) // should expect employee object from login in the session/model and be blocked by a session
	{
		//Employee employee = new Employee();
		//model.addAttribute("employee", employee);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_index");
		return modelAndView;
	}

	@Autowired
	AdminValidator adminValidator;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		 binder.addValidators(adminValidator);
		 
    }
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String viewAdminLogin(Model model)
	{
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "admin_login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView adminLogin(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		employee = employeeServ.getObject(employee);
		
		// need to do basic login validation here?
	


		adminValidator.validate(employee, result);
		
		if(result.hasErrors())
		{
			return new ModelAndView("admin_login");
		}
		else
		{
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("employee", employee);
			modelAndView.setViewName("admin_index");
			return modelAndView;
		}
	}
}
