package com.syntinel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.utilities.Utilities;

import com.syntinel.model.FoodOrder;

@Service
public class FoodOrderService implements ServiceInterface<FoodOrder>
{
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(FoodOrder foodOrder) 
	{
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_INSERT_NEW_FOOD_ORDER(?,?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setInt(2, foodOrder.getOrderId());
			callableStatement.setInt(3, foodOrder.getFoodId());
			callableStatement.setInt(4, foodOrder.getQuantity());
			callableStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
