package org.syntinel.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.syntinel.model.Order;

public class OrderRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		Order order = new Order();
		order.setOrderId(rs.getInt(1));
		order.setEmployeeId(rs.getInt(2));
		order.setCustomerId(rs.getInt(3));
		order.setCost(rs.getDouble(4));
		order.setPaymentId(rs.getInt(5));
		order.setDeliveryAddrId(rs.getInt(6));
		order.setOrderDate(rs.getString(7));
		order.setExpectedDate(rs.getString(8));
		order.setDeliveryDate(rs.getString(9));
		order.setNote(rs.getString(10));
		return order;
	}

}
