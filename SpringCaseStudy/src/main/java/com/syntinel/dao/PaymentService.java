package com.syntinel.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.syntinel.model.Payment;

@Service
public class PaymentService implements ServiceInterface<Payment>{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public PaymentService() {
		
	}
	
	@Override
	public void create(Payment payment) {
		String sql = "INSERT INTO PAYMENT VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, new Object [] {payment.getPaymentId(), payment.getType(),
				payment.getAmount(), payment.getDatePaid()});
	}
	
	public void delete(int paymentId) {
		String sql = "DELETE FROM PAYMENT WHERE PAYMENTID=?";
		jdbcTemplate.update(sql, paymentId);
	}
	
}
