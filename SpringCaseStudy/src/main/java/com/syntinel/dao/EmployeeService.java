package com.syntinel.dao;


import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Employee;

@Service
public class EmployeeService{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public EmployeeService() {
		
	}


	public void create(Employee employee) {
		
		String sql = "INSERT INTO EMPLOYEE(FIRSTNAME, LASTNAME) VALUES(?,?)";
		jdbcTemplate.update(sql,  new Object[] { employee.getFirstName(), employee.getLastName()});
	
	}
	
	public void delete(int employeeID) {
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		jdbcTemplate.update(sql, employeeID);
	}
}
