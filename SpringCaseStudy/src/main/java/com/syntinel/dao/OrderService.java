package com.syntinel.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Customer;
import com.syntinel.model.Order;

@Service
public class OrderService implements ServiceInterface<Order>{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate; 
	
	public OrderService() {
		
	}
	
	@Override
	public void create(Order order) {
		String sql = "INSERT INTO ORDERS VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object [] {order.getEmployeeId(), order.getCustomerId(), order.getCost(), 
				order.getPaymentId(), order.getDeliveryAddrId(), order.getOrderDate(), order.getExpectedDate(),
				order.getDeliveryDate(), order.getNote()});
	}
	
	public void delete(int orderId) {
		String sql = "DELETE FROM ORDERS WHERE ORDERID=?";
		jdbcTemplate.update(sql, orderId);
	}

	
}
