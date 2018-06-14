package com.syntinel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.mappers.EmployeeRowMapper;
import org.syntinel.utilities.Utilities;

import com.syntinel.model.Employee;

@Service
public class EmployeeService implements ServiceInterface<Employee>{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public EmployeeService() {
		super();
	}
	
	
	/**
	 * Adds an employee to the database.
	 * The id is automatically created by this method
	 * @param employee employee you to add to the database
	 */
	@Override
	public void create(Employee employee) {
		String sql = "INSERT INTO Employee VALUES (?,?,?,?,TO_DATE(?,'dd-MM-yyyy'),?,?,?,?,TO_DATE(?,'dd-MM-yyyy'),?)";
		jdbcTemplate.update(sql, new Object [] {Utilities.createUniqueId(), employee.getFirstName(),
				employee.getLastName(), employee.getEmail(), Utilities.getToday(), employee.getTitle(),
				employee.getPhoneNumber(), employee.getWorkAddrId(),
				employee.getHomeAddrId(), Utilities.getToday(), Utilities.encryptPassword(employee.getPassword())});
	}
	
	
	/**
	 * Get list of all existing employees
	 */
	public List<Employee> viewAll() {
		String sql = "SELECT * FROM Employee";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}
	
	
	/**
	 * Delete existing employee by employee id
	 */
	public void delete(String employeeId) {
		String sql = "DELETE FROM Employee WHERE employeeID = ?";
		jdbcTemplate.update(sql, employeeId);
	}

	
	/**
	 * Return specific employee object
	 */
	public Employee getObject(Employee employee) 
	{
		employee.setTitle("N");
		try
		{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("SELECT FIRSTNAME, LASTNAME, HIREDATE, TITLE,"
					+ " PHONENUMBER, WORKADDRID, HOMEADDRID, LASTLOGIN FROM EMPLOYEE WHERE EMAIL = ? AND PASSWORD = ?");
			preparedStatement.setString(1, employee.getEmail());
			preparedStatement.setString(2, Utilities.encryptPassword(employee.getPassword()));
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				employee.setFirstName(resultSet.getString(1));
				employee.setLastName(resultSet.getString(2));
				employee.setHireDate(resultSet.getString(3));
				employee.setTitle(resultSet.getString(4));
				employee.setPhoneNumber(resultSet.getString(5));
				employee.setWorkAddrId(resultSet.getInt(6));
				employee.setHomeAddrId(resultSet.getInt(7));
				employee.setLastLogin(resultSet.getString(8));
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return employee;
	}
	
	
}
