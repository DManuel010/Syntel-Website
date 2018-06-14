package com.syntinel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Order;
import com.syntinel.model.OrderCollection;

@Service
public class OrderCollectionService implements ServiceInterface<OrderCollection>
{
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(OrderCollection obj) {
		// TODO Auto-generated method stub
		
	}
	
	public List<OrderCollection> getAllOrders()
	{
		List<OrderCollection> orderCollection = new ArrayList<OrderCollection>();
		
		try
		{
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("SELECT O.ORDERID, O.COST, O.EXPECTEDDATE, O.DELIVERYDATE, " + 
					"C.FIRSTNAME, C.LASTNAME, C.EMAIL, P.TYPE FROM ORDERS O " + 
					"JOIN CUSTOMER C ON O.CUSTOMERID = C.CUSTOMERID " + 
					"JOIN PAYMENT P ON O.PAYMENTID = P.PAYMENTID ");
			ResultSet result = preparedStatement.executeQuery();
			while(result.next())
			{
				OrderCollection orders = new OrderCollection();
				//for orders class
				orders.getOrder().setOrderId(result.getInt(1));
				orders.getOrder().setCost(result.getDouble(2));
				orders.getOrder().setExpectedDate(result.getString(3));
				orders.getOrder().setDeliveryDate(result.getString(4));
				
				//for customer class
				orders.getCustomer().setFirst_name(result.getString(5));
				orders.getCustomer().setLast_name(result.getString(6));
				orders.getCustomer().setEmail(result.getString(7));
				
				//for payment property
				orders.setPayment_name(result.getString(8));
				
				
				orderCollection.add(orders);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return orderCollection;
	}

}

	