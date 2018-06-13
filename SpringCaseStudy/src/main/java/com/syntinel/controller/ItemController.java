package com.syntinel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.syntinel.utilities.Utilities;

import com.syntinel.dao.FoodOrderService;
import com.syntinel.dao.FoodService;
import com.syntinel.dao.LocationService;
import com.syntinel.dao.OrderService;
import com.syntinel.dto.MenuOrder;
import com.syntinel.model.Customer;
import com.syntinel.model.Food;
import com.syntinel.model.FoodOrder;
import com.syntinel.model.Location;
import com.syntinel.model.Order;

@Controller
@RequestMapping("/order")
@SessionAttributes("selectedItems")
public class ItemController 
{
	@Autowired
	FoodService foodServ;
	
	@Autowired
	LocationService locationServ;
	
	@Autowired
	OrderService orderServ;
	
	@Autowired
	FoodOrderService foodOrderServ;
	
	@Autowired
	Order order;
	
	@Autowired
	FoodOrder foodOrder;
	
	@RequestMapping(value="/food",method=RequestMethod.GET)
	public ModelAndView getFoodItems(@ModelAttribute ("menuOrder") MenuOrder menuOrder)
	{
		List<Food> foodItems = foodServ.viewAll();
		
		//MenuOrder menuOrder = new MenuOrder();
		//model.addAttribute("menuOrder", menuOrder);
		
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.addObject("foodItems", foodItems);
		modelAndView.setViewName("food_list");
		return modelAndView;
	}
	
	@RequestMapping(value="/summary", method=RequestMethod.POST)
	public ModelAndView checkout(@SessionAttribute("customer") Customer customer,
			@ModelAttribute ("menuOrder") MenuOrder menuOrder)
	{
		ModelAndView modelAndView = new ModelAndView();
				
		customer.setItems(foodServ.getSelectedItems(menuOrder.getItemCounts()));
		customer.setItemCounts(menuOrder.getNonZeroItemCounts());
		modelAndView.addObject("selectedItems", customer.getItems());
		modelAndView.addObject("itemCounts", customer.getItemCounts());
		modelAndView.setViewName("summary");
		return modelAndView;
	}
	
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public ModelAndView submitOrder(@SessionAttribute("customer") Customer customer,  @Validated Location location)
	{
		ModelAndView modelAndView = new ModelAndView();
		location.setCustomerId(customer.getId());
		customer.setLocation(location);
	
		
		//insert a new location
		locationServ.create(location);
		
		//get the id of the newly inserted location
		customer.getLocation().setLocationId(locationServ.getLatestLocation());
		
		
		//calculate total price
		double runningTotal=0.0;
		for(Food item : customer.getItems())
			runningTotal += item.getPrice();
		
		//saving running total to customer
		customer.setRunning_total(runningTotal);
		
		//instantiating a new order
		order.setCustomerId(customer.getId());
		order.setDeliveryAddrId(customer.getLocation().getLocationId());
		order.setCost(runningTotal);
		order.setOrderDate(Utilities.getToday());
		order.setExpectedDate(customer.getExpectedDate());
		order.setPaymentId(Integer.parseInt(customer.getPaymentType()));
		order.setNote(customer.getInstructions());
		
		//inserting a new order
		orderServ.create(order);
		
		//get the id of the newly inserted order
		customer.setOrderId(orderServ.getLatestOrder(customer.getId()));
		
		//insert order ids and meal ids into foodorder
		int index = 0;
		for(Food item : customer.getItems())
		{
			foodOrder.setFoodId(item.getFoodId());
			foodOrder.setOrderId(customer.getOrderId());
			foodOrder.setQuantity(Integer.parseInt(customer.getItemCounts().get(index++)));
			foodOrderServ.create(foodOrder);
		}
		
		Utilities.sendMail(customer);
		
		
		modelAndView.setViewName("order_placed");
		return modelAndView;
	}
	
	
	
}
