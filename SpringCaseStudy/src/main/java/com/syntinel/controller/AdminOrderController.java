package com.syntinel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.OrderCollectionService;
import com.syntinel.model.OrderCollection;

@Controller
@RequestMapping("/admin")
@SessionAttributes("employee")
public class AdminOrderController 
{
	@Autowired
	OrderCollectionService orderCollectionServ;
	
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public ModelAndView viewOrders(Model model)
	{
		List<OrderCollection> orders = new ArrayList<OrderCollection>();
		orders = orderCollectionServ.getAllOrders();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orders",orders);
		modelAndView.setViewName("admin_orders");
		return modelAndView;
	}

}
