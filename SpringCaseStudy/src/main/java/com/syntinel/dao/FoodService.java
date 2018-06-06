package com.syntinel.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.mappers.FoodRowMapper;
import org.syntinel.utilities.Utilities;

import com.syntinel.model.Food;

@Service
public class FoodService implements ServiceInterface<Food>
{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public FoodService() {}
	
	@Override
	public void create(Food food) {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_INSERT_NEW_FOOD(?,?,?,"
					+ "?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setString(2, food.getName());
			callableStatement.setString(3, food.getFoodGroup());
			callableStatement.setDouble(4, food.getPrice());
			callableStatement.setString(5, food.getDescription());
			callableStatement.setInt(6, food.getStock());
			callableStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(int foodId) {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_DELETE_FOOD(?)}");
			callableStatement.setInt(1, foodId);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Food> viewAll() {
		
		String sql = "SELECT FOODID, NAME, FOODGROUP, PRICE, DESCRIPTION FROM FOOD";
		return jdbcTemplate.query(sql, new FoodRowMapper());
	}

	public List<Food> getSelectedItems(String[] ids)
	{
		List<Food> foodList = new ArrayList<Food>();
		
		for(String id : ids)
		{
			try {
				Connection con = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement preparedStatement = con.prepareStatement("SELECT FOODID, NAME, "
						+ "FOODGROUP, PRICE, DESCRIPTION FROM FOOD WHERE FOODID = ?");
				preparedStatement.setString(1, id);
				
				ResultSet result = preparedStatement.executeQuery();
				while(result.next())
				{
					Food food = new Food();
					food.setFoodId(result.getInt("FOODID"));
					food.setName(result.getString("NAME"));
					food.setFoodGroup(result.getString("FOODGROUP"));
					food.setPrice(result.getDouble("PRICE"));
					food.setDescription(result.getString("DESCRIPTION"));
					foodList.add(food);
				}
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return foodList;
	}	

}
