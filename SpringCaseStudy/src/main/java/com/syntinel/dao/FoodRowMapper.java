package com.syntinel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.syntinel.model.Food;

public class FoodRowMapper  implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		Food food = new Food();
		food.setFoodId(rs.getInt(1));
		food.setName(rs.getString(2));
		food.setFoodGroup(rs.getString(3));
		food.setPrice(rs.getDouble(4));
		food.setDescription(rs.getString(5));
		food.setStock(rs.getInt(6));
		return food;
	}

}
