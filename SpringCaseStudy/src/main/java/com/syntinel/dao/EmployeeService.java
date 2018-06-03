package com.syntinel.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Employee;

@Service
public class EmployeeService implements ServiceInterface<Employee>{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public EmployeeService() {
		
	}
	
	@Override
	public void create(Employee employee) {
		String sql = "INSERT INTO EMPLOYEE VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object [] {employee.getEmployeeId(), employee.getFirstName(),
				employee.getLastName(), employee.getEmail(), employee.getHireDate(), employee.getTitle(),
				employee.getLoginId(), employee.getPhoneNumber(), employee.getWorkAddrId(),
				employee.getHomeAddrId(), employee.getLastLogin()});
		}
	
	
	public void delete(int employeeId) {
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		jdbcTemplate.update(sql, employeeId);
	}
}
