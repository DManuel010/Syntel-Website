package org.syntinel.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.syntinel.model.Food;

public class FoodRowMapper  implements RowMapper<Food>{

	@Override
	public Food mapRow(ResultSet rs, int index) throws SQLException {
		Food food = new Food();
		food.setFoodId(rs.getInt(1));
		food.setName(rs.getString(2));
		food.setFoodGroup(rs.getString(3));
		food.setPrice(rs.getDouble(4));
		food.setDescription(rs.getString(5));
		food.setImage(rs.getString(6));
		return food;
	}

}
