package com.syntinel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.FoodService;
import com.syntinel.model.Food;

@Controller
@RequestMapping("/order")
public class ItemController 
{
	@Autowired
	FoodService foodServ;
	
	@RequestMapping(value="/food",method=RequestMethod.GET)
	public ModelAndView getFoodItems(Model model)
	{
		List<Food> foodItems = foodServ.viewAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("foodItems", foodItems);
		modelAndView.setViewName("food_list");
		return modelAndView;
	}
}
