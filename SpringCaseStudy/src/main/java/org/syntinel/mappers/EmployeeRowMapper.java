package org.syntinel.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.syntinel.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int index) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployeeId(rs.getString(1));
		employee.setFirstName(rs.getString(2));
		employee.setLastName(rs.getString(3));
		employee.setEmail(rs.getString(4));
		employee.setHireDate(rs.getString(5));
		employee.setTitle(rs.getString(6));
		employee.setPhoneNumber(rs.getString(7));
		employee.setWorkAddrId(rs.getInt(8));	
		employee.setHomeAddrId(rs.getInt(9));		
		employee.setLastLogin(rs.getString(10));
		employee.setPassword(rs.getString(11));
		
		return employee;
	}

}
