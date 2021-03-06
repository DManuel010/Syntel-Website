package entities;


public class Order {
	
	private int orderID;
	private int employeeID;
	private int customerID;
	private double cost;
	private int paymentID;
	private int deliveryAddrID;
	private String orderDate;
	private String expectedDate;
	private String deliveryDate;
	private String note;
	
	private static int orderCount;
	
	public Order(int orderID, int employeeID, int customerID, double cost, int paymentID, int deliveryAddrID,
			String orderDate, String expectedDate, String deliveryDate, String note) {
		super();
		this.orderID = orderID;
		this.employeeID = employeeID;
		this.customerID = customerID;
		this.cost = cost;
		this.paymentID = paymentID;
		this.deliveryAddrID = deliveryAddrID;
		this.orderDate = orderDate;
		this.expectedDate = expectedDate;
		this.deliveryDate = deliveryDate;
		this.note = note;
		orderCount++;
	}

	public Order() {
		super();
		orderCount++;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getDeliveryAddrID() {
		return deliveryAddrID;
	}

	public void setDeliveryAddrID(int deliveryAddrID) {
		this.deliveryAddrID = deliveryAddrID;
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

	public static int getOrderCount() {
		return orderCount;
	}

	public static void setOrderCount(int orderCount) {
		Order.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", employeeID=" + employeeID + ", customerID=" + customerID + ", cost="
				+ cost + ", paymentID=" + paymentID + ", deliveryAddrID=" + deliveryAddrID + ", orderDate=" + orderDate
				+ ", expectedDate=" + expectedDate + ", deliveryDate=" + deliveryDate + ", note=" + note + "]";
	}
	
}
