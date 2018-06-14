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
import com.syntinel.model.Food;

@Controller
@RequestMapping("/admin")
@SessionAttributes("employee")
public class AdminFoodController {
	
	@Autowired
	FoodService foodService;
	
	@RequestMapping(value="/food", method=RequestMethod.GET)
	public ModelAndView viewFood(Model model) {
		Food food = new Food();
		model.addAttribute("food", food);
		List<Food> foodItems = foodService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("foodItems", foodItems);
		modelAndView.setViewName("admin_food");
		return modelAndView;
	}
	
	@RequestMapping(value="/food/add", method=RequestMethod.POST)
	public ModelAndView addFood(@ModelAttribute("food") Food food, Model model) {
		foodService.create(food);
		model.addAttribute("food", food);
		List<Food> foodItems = foodService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("foodItems", foodItems);
		modelAndView.setViewName("redirect:/admin/food");
		return modelAndView;
	}
	
	@RequestMapping(value="/food/deactivate", method=RequestMethod.POST)
	public ModelAndView deactivateFood(@ModelAttribute("food") Food food, Model model) {
		int foodID = food.getFoodId();
		foodService.deactivate(foodID);
		model.addAttribute("food", food);
		List<Food> foodItems = foodService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("foodItems", foodItems);
		modelAndView.setViewName("redirect:/admin/food");
		return modelAndView;
	}
	
	@RequestMapping(value="/food/activate", method=RequestMethod.POST)
	public ModelAndView activateFood(@ModelAttribute("food") Food food, Model model) {
		int foodID = food.getFoodId();
		foodService.activate(foodID);
		model.addAttribute("food", food);
		List<Food> foodItems = foodService.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("foodItems", foodItems);
		modelAndView.setViewName("redirect:/admin/food");
		return modelAndView;
	}
}
