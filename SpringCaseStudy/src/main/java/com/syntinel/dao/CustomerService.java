package com.syntinel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.mappers.CustomerRowMapper;

import org.syntinel.utilities.Utilities;

import com.syntinel.model.Customer;


@Service
public class CustomerService implements ServiceInterface<Customer>
{
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public CustomerService() {}
	
	
	@Override
	public void create(Customer customer) 
	{
		try 
		{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_INSERT_NEW_CUSTOMER(?,?,?,?,?,?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setString(2, customer.getFirst_name());
			callableStatement.setString(3, customer.getLast_name());
			callableStatement.setString(4, customer.getEmail());
			callableStatement.setString(5, customer.getHome_number());
			callableStatement.setString(6, customer.getDob());
			callableStatement.setString(7, Utilities.encryptPassword(customer.getPassword()));
			callableStatement.setString(8, customer.getMobile_number());
			callableStatement.execute();
					
		} catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		
	}


	public Customer getObject(Customer customer)
	{ 
		try
		{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("SELECT CUSTOMERID, "
					+ " FIRSTNAME, LASTNAME, LASTLOGIN FROM CUSTOMER WHERE EMAIL = ? AND PASS = ?");
			preparedStatement.setString(1, customer.getEmail());
			preparedStatement.setString(2, Utilities.encryptPassword(customer.getPassword()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				customer.setId(resultSet.getInt(1));
				customer.setFirst_name(resultSet.getString(2));
				customer.setLast_name(resultSet.getString(3));
				customer.setLast_login(resultSet.getString(4));
			}
			
		} catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		
		return customer;
		
	}
	
	public List<Customer> viewAll() {
		
		String sql = "SELECT CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL, PHONENUMBER, DATEOFBIRTH, DATEOFREGISTER, LASTLOGIN, PASS, MOBILE_NUMBER FROM CUSTOMER";
		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}
	
}
