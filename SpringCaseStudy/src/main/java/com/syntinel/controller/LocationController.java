package com.syntinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.syntinel.dao.LocationService;
import com.syntinel.model.Customer;
import com.syntinel.model.Location;

@Controller
@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationService locationServ;
	
	@RequestMapping(value="/addnew", method=RequestMethod.GET)
	public String loadAddressForm(ModelMap model) {
		Location location = new Location();
		model.addAttribute("location", location);
		return "location";
	}
	
	@RequestMapping(value="/added", method=RequestMethod.POST)
	public String addLocation(@ModelAttribute("location") @Validated Location location, BindingResult result, Model model) {
		
		locationServ.create(location);
		return "location";
	}
	
	@RequestMapping(value="/delivery", method=RequestMethod.POST)
	public ModelAndView delivery(@SessionAttribute("customer") Customer customer,
			@RequestParam(value="instructions") String instructions, @RequestParam(value="paymentType") String paymentType,
			@RequestParam(value="expectedDate") String expectedDate)
	{
		customer.setExpectedDate(expectedDate);
		customer.setPaymentType(paymentType);
		customer.setInstructions(instructions);
		ModelAndView modelAndView = new ModelAndView();
		Location location = new Location();
		modelAndView.addObject("location", location);
		modelAndView.setViewName("delivery_location");
		return modelAndView;
	}
		
}
