package org.syntinel.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.syntinel.model.Customer;
import com.syntinel.model.Employee;
import com.syntinel.model.Location;

public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int index) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getInt(1));
		customer.setFirst_name(rs.getString(2));
		customer.setLast_name(rs.getString(3));
		customer.setEmail(rs.getString(4));
		customer.setHome_number(rs.getString(5));
		customer.setDob(rs.getString(6));
		customer.setRegister_date(rs.getString(7));
		customer.setLast_login(rs.getString(8));	
		customer.setPassword(rs.getString(9));		
		customer.setMobile_number(rs.getString(10));
		
		return customer;
		
		
	}

}
