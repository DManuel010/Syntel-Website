package com.syntinel.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.syntinel.model.Customer;

@Component
public class RegistrationValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		Customer customer = (Customer) target;
		
		
		if(!customer.getPassword().isEmpty() && !customer.getConfirm_password().isEmpty())
		{
			if(!customer.getPassword().equals(customer.getConfirm_password()))
				error.rejectValue("password", "customer.password.mismatch");
		}
		
	}
	 
	    
}
