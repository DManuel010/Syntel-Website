package com.syntinel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.syntinel.mappers.OrderRowMapper;
import org.syntinel.utilities.Utilities;

import com.syntinel.model.Order;

@Service
public class OrderService implements ServiceInterface<Order>{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate; 
	
	public OrderService() {
		
	}
	
	@Override
	public void create(Order order) {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_INSERT_NEW_ORDER(?,?,?,?,?"
					+ ",?,?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setInt(2, order.getCustomerId());
			callableStatement.setDouble(3, order.getCost());
			callableStatement.setInt(4, order.getPaymentId());
			callableStatement.setInt(5, order.getDeliveryAddrId());
			callableStatement.setString(6, order.getOrderDate());
			callableStatement.setString(7, order.getExpectedDate());
			callableStatement.setString(8, order.getDeliveryDate());
			callableStatement.setString(9, order.getNote());
			callableStatement.execute();
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(int orderId) {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableStatement = con.prepareCall("{call SP_DELETE_ORDER(?)}");
			callableStatement.setInt(1, orderId);
			callableStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Order> viewAll() {
		String sql = "SELECT * FROM ORDERS";
		return jdbcTemplate.query(sql, new OrderRowMapper());
	}
	
	
	public void changeDate(int orderId, String newDate) {
		String sql = "UPDATE ORDERS SET EXPECTEDDATE=? WHERE ORDERID=?";
		jdbcTemplate.update(sql, newDate, orderId);
	}
	
	//TODO: Will attempt to change this method to only use jdbcTemplate
	
	public int getLatestOrder(int id)
	{
		try 
		{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("SELECT TEMP.ORDERID FROM (SELECT O.*, ROW_NUMBER() " + 
					"OVER (ORDER BY O.ORDERID DESC) R FROM ORDERS O) TEMP WHERE R=1 AND CUSTOMERID = ?");
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next())
				return result.getInt(1);

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
