package com.syntinel.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.utilities.Utilities;

import com.syntinel.model.Food;
import com.syntinel.dao.FoodRowMapper;

@Service
public class FoodService implements ServiceInterface<Food>
{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public FoodService() {
		
	}
	
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
		
		String sql = "SELECT * FROM FOOD";
		return jdbcTemplate.query(sql, new FoodRowMapper());
	}
	

//	public ArrayList<Food> getFoodList()
//	{
//	
//		//not the right way of doing this using, but for now it works
//		ArrayList<Food> foodItems = new ArrayList<Food>();
//		Food food = new Food();
//		
//		try
//		{
//			Connection con = jdbcTemplate.getDataSource().getConnection();
//			Statement statement = con.createStatement();
//			ResultSet result = statement.executeQuery("SELECT * FROM FOOD");
//			while(result.next())
//			{
//				food.setName(result.getString(1));
//				food.setFoodGroup(result.getString(2));
//				food.setPrice(result.getDouble(3));
//				food.setDescription(result.getString(4));
//				foodItems.add(food);
//			}
//		
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		return foodItems;
//			
//	}
	

}
