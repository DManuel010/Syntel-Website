package com.syntinel.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Customer;


@Service
public class CustomerService implements CustomerInterface
{
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public CustomerService() {}
	
	
	//TESTING JDBC TEMPLATE, FOR NOW IT WILL NOT WORK ON OTHER COMPUTERS 
	@Override
	public void create(Customer customer) 
	{
		String sql = "INSERT INTO USERS(USERNAME, NAME) "
	              + "VALUES(?,?)";
		jdbcTemplate.update(sql,  new Object[] { customer.getFirst_name(), customer.getLast_name()});
		
	}
	
}
