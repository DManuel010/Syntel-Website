package com.syntinel.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;

import com.syntinel.model.Customer;

@Component
public class LoginValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		Customer customer = (Customer) target;
		
		if(customer.getEmail().isEmpty())
			error.rejectValue("email", "customer.email.empty");
		
		if (Pattern.matches(".+@.+\\..+",customer.getEmail()))
			error.rejectValue("email", "customer.email.bad");
		
		if(customer.getEmail().isEmpty())
			error.rejectValue("email", "customer.password.empty");
		
		if(!customer.getEmail().isEmpty() && !customer.getEmail().isEmpty())
		{
			if(customer.getId().length() <= 0)
				error.rejectValue("email", "customer.id.empty");
		}
	}

}
