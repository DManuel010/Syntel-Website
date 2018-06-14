package com.syntinel.model;

public class OrderCollection 
{
	private Order order;
	private Customer customer;
	private String payment_name;
	
	public OrderCollection()
	{
		this.setCustomer(new Customer());
		this.setOrder(new Order());
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getPayment_name() {
		return payment_name;
	}
	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}
	
	
}
