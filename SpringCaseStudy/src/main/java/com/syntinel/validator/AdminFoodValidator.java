package com.syntinel.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.syntinel.model.Customer;
import com.syntinel.model.Food;

@Component
public class AdminFoodValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Customer.class.isAssignableFrom(clazz);
	}
	//a comment
	@Override
	public void validate(Object target, Errors error) {
		Food food = (Food) target;
		
		if(food.getName().isEmpty()) {
			error.rejectValue("email", "customer.email.empty");
		}
		/*
		if(food.getPrice().isEmpty()) {
			error.rejectValue("email", "customer.email.bad");
		}
		
		if(customer.getPassword().isEmpty())
			error.rejectValue("password", "customer.password.empty");
		
		if(!customer.getEmail().isEmpty() && !customer.getPassword().isEmpty())
		{
			if(customer.getId() == 0)
				error.rejectValue("email", "customer.id.empty");
		}
		*/
	}

}
