package com.syntinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.syntinel.dao.FoodService;
import com.syntinel.model.Food;

@Controller
@RequestMapping("/admin")
@SessionAttributes("employee")
public class AdminFoodController {
	
	@Autowired
	FoodService foodService;
	
	@RequestMapping(value="/food", method=RequestMethod.GET)
	public String viewFood(Model model) {
		//TODO: make this actually work, DOH!
		Food food = new Food();
		model.addAttribute("food", food);
		return "admin_food";
	}
	
	@RequestMapping(value="/food/add", method=RequestMethod.POST)
	public String addFood(@ModelAttribute("food") Food food) {
		foodService.create(food);
		return "admin_food";
	}
	
	@RequestMapping(value="/food/delete", method=RequestMethod.POST)
	public String deleteFood(@ModelAttribute("food") Food food) {
		int foodID = food.getFoodId();
		foodService.delete(foodID);
		return "admin_food";
	}
}
