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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
public class MenuRestController {
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

@RequestMapping(value = "/menu", method = RequestMethod.GET,headers="Accept=application/json")
public @ResponseBody List<Food> listMenu() {
		
		
		List<Food> menu = new ArrayList<Food>();
		
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
