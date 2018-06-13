package com.syntinel.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.syntinel.model.Employee;

@Component
public class AdminValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Employee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		Employee employee = (Employee) target;
		
		if(employee.getEmail().isEmpty())
			error.rejectValue("email", "employee.email.empty");
		
		if(employee.getEmployeeId().isEmpty())
			error.rejectValue("employeeId", "employee.employeeId.empty");
		
		if(!employee.getEmail().isEmpty() && !employee.getEmployeeId().isEmpty())
		{
		   if(employee.getTitle().isEmpty())
				error.rejectValue("employeeId", "employee.employeeId.wrong");
		}
		
		
	}

}
