package com.syntinel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.mappers.FoodRowMapper;
import org.syntinel.mappers.LocationRowMapper;
import org.syntinel.utilities.Utilities;

import com.syntinel.model.Food;
import com.syntinel.model.Location;

@Service
public class LocationService implements ServiceInterface<Location>
{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public LocationService() {
		
	}

	@Override
	public void create(Location location) 
	{
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_INSERT_NEW_LOCATION(?,?,?,?,?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setString(2, location.getCountry());
			callableStatement.setString(3, location.getState());
			callableStatement.setString(4, location.getCity());
			callableStatement.setString(5, location.getStreet_number());
			callableStatement.setString(6, location.getRoom_number());
			callableStatement.setString(7, location.getZip_code());
			callableStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(int locationid) {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_DELETE_LOCATION(?)}");
			callableStatement.setInt(1, locationid);
			callableStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Location> viewAll() {
		
		String sql = "SELECT LOCATIONID, COUNTRY, STATE, CITY, STREETNUM, ROOMNUM, ZIP FROM FOOD";
		return jdbcTemplate.query(sql, new LocationRowMapper());
	}	

	
}

