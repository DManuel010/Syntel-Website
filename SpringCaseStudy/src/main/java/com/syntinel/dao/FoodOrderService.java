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
	
	
	public List<FoodOrder> viewMyOrder(int customerId) {
	
		List<FoodOrder> foodOrders = new ArrayList<FoodOrder>();
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			
			PreparedStatement preparedStatement = con.prepareStatement("SELECT F.NAME, FO.QUANTITY, O.COST, O.EXPECTEDDATE, "
					+ "O.ORDERDATE FROM FOOD F, FOODORDER FO, ORDERS O WHERE "
					+ "O.ORDERID=FO.ORDERID AND F.FOODID=FO.FOODID AND O.CUSTOMERID = ?");
			preparedStatement.setInt(1, customerId);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				FoodOrder myOrder = new FoodOrder();
				myOrder.setName(result.getString(1));
				myOrder.setQuantity(result.getInt(2));
				myOrder.setCost(result.getDouble(3));
				myOrder.setExpecteddate(result.getString(4));
				myOrder.setOrderdate(result.getString(5));
				myOrder.setStatus("Pending");
				foodOrders.add(myOrder);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foodOrders;
		
		
	}
	
	
}
