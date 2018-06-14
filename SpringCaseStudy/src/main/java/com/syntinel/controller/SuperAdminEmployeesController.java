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

import com.syntinel.dao.EmployeeService;
import com.syntinel.model.Employee;

@Controller
@RequestMapping("/superAdmin")
@SessionAttributes("employee")
public class SuperAdminEmployeesController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public ModelAndView viewEmployees(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		List<Employee> employees = employeeService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employees", employees);
		modelAndView.setViewName("super_admin_employees");
		return modelAndView;
	}
	
	@RequestMapping(value="/employees/add", method=RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		employeeService.create(employee);
		model.addAttribute("employee", employee);
		List<Employee> employees = employeeService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employees", employees);
		modelAndView.setViewName("redirect:/superAdmin/employees");
		return modelAndView;
	}
	
	@RequestMapping(value="/employees/delete", method=RequestMethod.POST)
	public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		String employeeID = employee.getEmployeeId();
		employeeService.delete(employeeID);
		model.addAttribute("employee", employee);
		List<Employee> employees = employeeService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employees", employees);
		modelAndView.setViewName("redirect:/superAdmin/employees");
		return modelAndView;
	}
	
	/*
	@RequestMapping(value="/employees/update", method=RequestMethod.POST)
	public ModelAndView updateFood(@ModelAttribute("employee") Employee employee, Model model) {
		String employeeID = employee.getEmployeeId();
		employeeService.update(employeeID);
		model.addAttribute("employee", employee);
		List<Employee> employees = employeeService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employees", employees);
		modelAndView.setViewName("redirect:/admin/food");
		return modelAndView;
	}
	*/
}
