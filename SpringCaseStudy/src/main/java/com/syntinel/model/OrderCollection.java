package com.syntinel.model;

public class OrderCollection 
{
	private Order order;
	private Customer customer;
	private Food food;
	private FoodOrder foodOrder;
	public FoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	private String payment_name;
	
	public OrderCollection()
	{
		this.setCustomer(new Customer());
		this.setOrder(new Order());
		this.setFood(new Food());
		this.setFoodOrder(new FoodOrder());
		
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

	@Override
	public String toString() {
		return "OrderCollection [order=" + order + ", customer=" + customer + ", food=" + food + ", foodOrder="
				+ foodOrder + ", payment_name=" + payment_name + "]";
	}


	
}
