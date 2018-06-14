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
	
	/**
	 * Adds a food item to the database.
	 * The id is automatically created by this method
	 * @param food The food item you would like to add
	 */
	@Override
	public void create(Food food) {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_INSERT_NEW_FOOD(?,?,?,?,?,?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setString(2, food.getName());
			callableStatement.setString(3, food.getFoodGroup());
			callableStatement.setDouble(4, food.getPrice());
			callableStatement.setString(5, food.getDescription());
			callableStatement.setInt(6, food.getStock());
			callableStatement.setString(7, food.getImage());
			callableStatement.setInt(8, 1);
			callableStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Deletes the food item in the database with the given id
	 * @param foodId Id of item to be deleted
	 */
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
	
	public Food fetchByID(int foodId) {
		Food food = null;
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM FOOD"
					+ " WHERE FOODID = ?");
			preparedStatement.setInt(1, foodId);
			ResultSet result = preparedStatement.executeQuery();
			
			result.next();
			
			food = new Food();
			food.setFoodId(result.getInt("FOODID"));
			food.setName(result.getString("NAME"));
			food.setFoodGroup(result.getString("FOODGROUP"));
			food.setPrice(result.getDouble("PRICE"));
			food.setDescription(result.getString("DESCRIPTION"));
			food.setStock(result.getInt("STOCK"));
			food.setImage(result.getString("IMAGE"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return food;
		
	}
	
	/**
	 * 
	 * @return a list of all food items in the database given as food objects
	 */
	public List<Food> viewAll() {
		String sql = "SELECT * FROM Food";
		return jdbcTemplate.query(sql, new FoodRowMapper());
	}

	
	public List<Food> getSelectedItems(ArrayList<String> counts)
	{
		List<Food> foodList = new ArrayList<Food>();
		
		//for(String id : ids)
		for (int i = 0; i < counts.size(); ++i)
		{			
			if (!counts.get(i).equals("0"))
			{
				try {
					Connection con = jdbcTemplate.getDataSource().getConnection();
					PreparedStatement preparedStatement = con.prepareStatement("SELECT FOODID, NAME, "
							+ "FOODGROUP, PRICE, DESCRIPTION, IMAGE FROM FOOD WHERE FOODID = ?");
					preparedStatement.setInt(1, i);
					
					ResultSet result = preparedStatement.executeQuery();
					while(result.next())
					{
						Food food = new Food();
						food.setFoodId(result.getInt("FOODID"));
						food.setName(result.getString("NAME"));
						food.setFoodGroup(result.getString("FOODGROUP"));
						food.setPrice(result.getDouble("PRICE"));
						food.setDescription(result.getString("DESCRIPTION"));
						food.setImage(result.getString("IMAGE"));
						foodList.add(food);
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
				
		return foodList;
	}	
	
	
	/**
	 * Returns a list of all the active (menu displayed) food items
	 */
	public List<Food> viewAllActive() {
		String sql = "SELECT * FROM Food WHERE active = 1";
		return jdbcTemplate.query(sql, new FoodRowMapper());
	}
	
	
	/**
	 * Toggles the "Active" flag for a given food item to "active" (1)
	 * Used to mark a food item to be displayed on the customer menu
	 * @param foodID Id of food to be activated
	 */
	public void activate(int foodID) {
		String sql = "UPDATE Food SET active = 1 WHERE foodID = ?";
		jdbcTemplate.update(sql, foodID);
	}
	
	
	/**
	 * Toggles the "Active" flag for a given food item to "deactivate" (0)
	 * Used to mark a food item to be hidden from the customer menu
	 * @param foodID Id of food to be deactivated
	 */
	public void deactivate(int foodID) {
		String sql = "UPDATE Food SET active = 0 WHERE foodID = ?";
		jdbcTemplate.update(sql, foodID);
	}
}
