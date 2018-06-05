package com.syntinel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntinel.dao.FoodService;
import com.syntinel.model.Food;

@Controller
@RequestMapping("/order")
public class ItemController 
{
	@Autowired
	FoodService foodServ;
	
	@RequestMapping(value="/food",method=RequestMethod.GET)
	public String getFoodItems(Model model)
	{
		List<Food> foodItems = foodServ.viewAll();
		model.addAttribute(foodItems);
		return "food_list";
	}
}
