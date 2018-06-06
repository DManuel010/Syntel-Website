package com.syntinel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.FoodService;
import com.syntinel.model.Customer;
import com.syntinel.model.Food;

@Controller
@RequestMapping("/order")
@SessionAttributes("selectedItems")
public class ItemController 
{
	@Autowired
	FoodService foodServ;
	
	@RequestMapping(value="/food",method=RequestMethod.GET)
	public ModelAndView getFoodItems()
	{
		List<Food> foodItems = foodServ.viewAll();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("foodItems", foodItems);
		modelAndView.setViewName("food_list");
		return modelAndView;
	}
	
	@RequestMapping(value="/checkout",method=RequestMethod.POST)
	
	public ModelAndView checkout(@SessionAttribute("customer") Customer customer, @RequestParam(value="foodItemChkbx") String[] foodItemChkbx)
	{
//		List<Food> selectedItems = null;
//		selectedItems = foodServ.getSelectedItems(foodItemChkbx);
		
		customer.setItems(foodServ.getSelectedItems(foodItemChkbx));

		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("selectedItems", selectedItems);
		modelAndView.addObject("selectedItems", customer.getItems());
		modelAndView.setViewName("checkout");
		return modelAndView;
	}
	
	
	
}
