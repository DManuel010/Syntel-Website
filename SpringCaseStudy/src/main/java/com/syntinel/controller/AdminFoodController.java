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

import com.syntinel.dao.FoodService;
import com.syntinel.model.Employee;
import com.syntinel.model.Food;

@Controller
@RequestMapping("/admin/food")
@SessionAttributes("employee")
public class AdminFoodController 
{
	@Autowired
	FoodService foodService;
	
	@RequestMapping(value="/admin/food", method=RequestMethod.GET)
	public ModelAndView viewFood(Model model)
	{
		List<Food> foodItems = foodService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_login");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/food", method=RequestMethod.POST)
	public ModelAndView addFood(@ModelAttribute("food") Food food)
	{
		foodService.create(food);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("food", food);
		modelAndView.setViewName("admin_food");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/food", method=RequestMethod.POST)
	public ModelAndView deleteFood(@ModelAttribute("food") Food food)
	{
		int foodID = food.getFoodId();
		foodService.delete(foodID);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("food", food);
		modelAndView.setViewName("admin_food");
		return modelAndView;
	}
}
