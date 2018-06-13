package com.syntinel.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.web.bind.annotation.RestController;

import com.syntinel.model.Food;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//the rest controller declares this is a webservice controller
@RestController
public class MenuRestController {
	
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

@RequestMapping("/menu")
public @ResponseBody List<Food> listMenu() {
		
		//prints the menu in array list form
		List<Food> menu = new ArrayList<Food>();
		
		//getting the connection and selecting all of the food items from the database
		Connection con;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("select * from food");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Food item = new Food();
				
				item.setFoodId(resultSet.getInt(1));
				item.setName(resultSet.getString(2));
				item.setFoodGroup(resultSet.getString(3));
				item.setPrice(resultSet.getDouble(4));
				item.setDescription(resultSet.getString(5));
				item.setStock(resultSet.getInt(6));
				
				menu.add(item);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return menu;
		
	}
	

}
