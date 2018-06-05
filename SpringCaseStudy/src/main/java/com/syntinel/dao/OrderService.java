package com.syntinel.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
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
					+ ",?,?,?,?,?)}");
			callableStatement.setString(1, Utilities.createUniqueId());
			callableStatement.setInt(2, order.getEmployeeId());
			callableStatement.setInt(3, order.getCustomerId());
			callableStatement.setDouble(4, order.getCost());
			callableStatement.setInt(5, order.getPaymentId());
			callableStatement.setInt(6, order.getDeliveryAddrId());
			callableStatement.setString(7, order.getOrderDate());
			callableStatement.setString(8, order.getExpectedDate());
			callableStatement.setString(9, order.getDeliveryDate());
			callableStatement.setString(10, order.getNote());
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

	public List viewAll() {
		String sql = "SELECT * FROM ORDERS";
		return jdbcTemplate.query(sql, new OrderRowMapper());
	}
	
	public List viewMyOrders(int customerId) {
		String sql = "SELECT * FROM ORDERS WHERE CUSTOMERID=?";
		return jdbcTemplate.query(sql, new Object [] {customerId}, new OrderRowMapper());
	}
	
	public void changeDate(int orderId, String newDate) {
		String sql = "UPDATE ORDERS SET EXPECTEDDATE=? WHERE ORDERID=?";
		jdbcTemplate.update(sql, newDate, orderId);
	}
	
}
