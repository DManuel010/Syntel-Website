package com.syntinel.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
public class MenuRestController {
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

@RequestMapping("/menu")
public @ResponseBody List listMenu() {
		
		
		List menu = new ArrayList();
		
		Connection con;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("select * from food");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				menu.add(resultSet.getString(2));
				menu.add(resultSet.getString(3));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return menu;
		
	}
	

}
