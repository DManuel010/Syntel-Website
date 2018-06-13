package com.syntinel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.mappers.FoodRowMapper;
import org.syntinel.utilities.Utilities;

import com.syntinel.model.Employee;
import com.syntinel.model.Food;

@Service
public class EmployeeService implements ServiceInterface<Employee>{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public EmployeeService() {
		
	}
	
	@Override
	public void create(Employee employee) {
		String sql = "INSERT INTO EMPLOYEE VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object [] {Utilities.createUniqueId(), employee.getFirstName(),
				employee.getLastName(), employee.getEmail(), employee.getHireDate(), employee.getTitle(),
				employee.getPhoneNumber(), employee.getWorkAddrId(),
				employee.getHomeAddrId(), employee.getLastLogin()});
		}
	
	
	public void delete(int employeeId) {
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		jdbcTemplate.update(sql, employeeId);
	}

	public Employee getObject(Employee employee) 
	{
		try
		{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("SELECT FIRSTNAME, LASTNAME, HIREDATE, TITLE,"
					+ " PHONENUMBER, WORKADDRID, HOMEADDRID, LASTLOGIN FROM EMPLOYEE WHERE EMAIL = ? AND EMPLOYEEID = ?");
			preparedStatement.setString(1, employee.getEmail());
			preparedStatement.setString(2, employee.getEmployeeId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				employee.setFirstName(resultSet.getString(1));
				employee.setLastName(resultSet.getString(2));
				employee.setHireDate(resultSet.getString(3));
				employee.setTitle(resultSet.getString(4));
				employee.setPhoneNumber(resultSet.getString(5));
				employee.setWorkAddrId(resultSet.getInt(6));
				employee.setHomeAddrId(resultSet.getInt(7));
				employee.setLastLogin(resultSet.getString(8));
			}
			
		} catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		
		return employee;
	}
	
	
}
