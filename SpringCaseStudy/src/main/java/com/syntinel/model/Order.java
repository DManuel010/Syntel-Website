package com.syntinel.model;

import org.springframework.stereotype.Component;

@Component
public class Order {

	private int orderId;
	private int employeeId;
	private int customerId;
	private double cost;
	private int paymentId;
	private int deliveryAddrId;
	private String orderDate;
	private String expectedDate;
	private String deliveryDate;
	private String note;
	
	public Order() {
		
	}
	
	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getDeliveryAddrId() {
		return deliveryAddrId;
	}

	public void setDeliveryAddrId(int deliveryAddrId) {
		this.deliveryAddrId = deliveryAddrId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", employeeId=" + employeeId + ", customerId=" + customerId + ", cost="
				+ cost + ", paymentId=" + paymentId + ", deliveryAddrId=" + deliveryAddrId + ", orderDate=" + orderDate
				+ ", expectedDate=" + expectedDate + ", deliveryDate=" + deliveryDate + ", note=" + note + "]";
	}
	
	
}
