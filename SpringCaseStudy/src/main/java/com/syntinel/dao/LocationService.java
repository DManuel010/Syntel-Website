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
			CallableStatement callableStatement = con.prepareCall("{call SP_INSERT_NEW_LOCATION(?,?,?,?,?,?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setInt(2, location.getCustomerId());
			callableStatement.setString(3, location.getCountry());
			callableStatement.setString(4, location.getState());
			callableStatement.setString(5, location.getCity());
			callableStatement.setString(6, location.getStreet_number());
			callableStatement.setString(7, location.getRoom_number());
			callableStatement.setString(8, location.getZip_code());
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

	public int getLatestLocation() 
	{
		//query will be changed once the location 
		//table has a customer id associated with the address
		try 
		{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("SELECT TEMP.LOCATIONID FROM (SELECT L.*, ROW_NUMBER() " + 
					"OVER (ORDER BY L.LOCATIONID DESC) R FROM LOCATION L) TEMP WHERE R=1");
			ResultSet result = preparedStatement.executeQuery();
			while(result.next())
				return result.getInt(1);

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return 0;
	}	

	
}

