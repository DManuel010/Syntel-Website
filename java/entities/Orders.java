package caseStudy;

import java.sql.Date;

public class Orders {

	String orderName;
	Date orderDate;
	int CusomterID;
	int OrderID;
	int EmployeeID;
	double cost;
	int paymentID;
	String deliveryAddress;
	Date expectedDate;
	Date deliveryDate;
	String note;
	public Orders(String orderName, Date orderDate, int cusomterID, int orderID, int employeeID, double cost,
			int paymentID, String deliveryAddress, Date expectedDate, Date deliveryDate, String note) {
		super();
		this.orderName = orderName;
		this.orderDate = orderDate;
		CusomterID = cusomterID;
		OrderID = orderID;
		EmployeeID = employeeID;
		this.cost = cost;
		this.paymentID = paymentID;
		this.deliveryAddress = deliveryAddress;
		this.expectedDate = expectedDate;
		this.deliveryDate = deliveryDate;
		this.note = note;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getCusomterID() {
		return CusomterID;
	}
	public void setCusomterID(int cusomterID) {
		CusomterID = cusomterID;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
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
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
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
		return "Orders [orderName=" + orderName + ", orderDate=" + orderDate + ", CusomterID=" + CusomterID
				+ ", OrderID=" + OrderID + ", EmployeeID=" + EmployeeID + ", cost=" + cost + ", paymentID=" + paymentID
				+ ", deliveryAddress=" + deliveryAddress + ", expectedDate=" + expectedDate + ", deliveryDate="
				+ deliveryDate + ", note=" + note + "]";
	}
	
	
}
