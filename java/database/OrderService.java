package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Order;


public class OrderService extends Service {

	public OrderService(Connection conn) {
		super(conn);
	}

	@Override
	public void insert(Object obj) {
		Order order = (Order) obj;

		int orderID = getPK(order.getOrderID());
		order.setOrderID(orderID);
		
		int employeeID = order.getEmployeeID();
		int customerID = order.getCustomerID();
		double cost = order.getCost();
		int paymentID = order.getPaymentID();
		int deliveryAddrID = order.getDeliveryAddrID();
		String orderDate = order.getOrderDate();
		String expectedDate = order.getExpectedDate();
		String deliveryDate = order.getDeliveryDate();
		String note = order.getNote();
		
		// format query
		String query = "INSERT INTO Orders ("
						+ "orderID, employeeID, customerID, cost, "
						+ "paymentID, deliveryAddrID, orderDate, "
						+ "expectedDate, deliveryDate, note) "
					+ "VALUES(?, ?, ?, ?, ?, ?, "
							+ "TO_DATE(?, 'MM/DD/YYYY'), "
							+ "TO_DATE(?, 'MM/DD/YYYY'), "
							+ "TO_DATE(?, 'MM/DD/YYYY'),"
							+ " ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, orderID);
			statement.setInt(2, employeeID);
			statement.setInt(3, customerID);
			statement.setDouble(4, cost);
			statement.setInt(5, paymentID);
			statement.setInt(6, deliveryAddrID);
			statement.setString(7, orderDate);
			statement.setString(8, expectedDate);
			statement.setString(9, deliveryDate);
			statement.setString(10, note);
			statement.executeUpdate();
			System.out.println("OrderService:  Order inserted.");
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to insert order.");
			e.printStackTrace();
		}
	}
	
	public void insertNew(Order order) {
	

		int orderID = getPK(order.getOrderID());
		order.setOrderID(orderID);
		
		int employeeID = order.getOrderID();
		int customerID = order.getCustomerID();
		double cost = order.getCost();
		int paymentID = order.getPaymentID();
		int deliveryAddrID = order.getDeliveryAddrID();
		String orderDate = order.getOrderDate();
		String expectedDate = order.getExpectedDate();
		String deliveryDate = order.getDeliveryDate();
		String note = order.getNote();
		
		// format query
		String query = "INSERT INTO Orders ("
						+ "orderID, employeeID, customerID, cost, "
						+ "paymentID, deliveryAddrID, orderDate, "
						+ "expectedDate, deliveryDate, note) "
					+ "VALUES(?, ?, ?, ?, ?, ?, "
							+ "TO_DATE(?, 'MM/DD/YYYY'), "
							+ "TO_DATE(?, 'MM/DD/YYYY'), "
							+ "TO_DATE(?, 'MM/DD/YYYY'),"
							+ " ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, orderID);
			statement.setInt(2, employeeID);
			statement.setInt(3, customerID);
			statement.setDouble(4, cost);
			statement.setInt(5, paymentID);
			statement.setInt(6, deliveryAddrID);
			statement.setString(7, orderDate);
			statement.setString(8, expectedDate);
			statement.setString(9, deliveryDate);
			statement.setString(10, note);
			statement.execute();
			System.out.println("OrderService:  Order inserted.");
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to insert order.");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int orderID) {
		String query = "DELETE FROM Orders WHERE orderID = ?";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, orderID);
			statement.executeUpdate();
			System.out.println("OrderService:  Order deleted.");
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to delete order.");
			e.printStackTrace();
		}
	}
	
	
	// increment the primary key for new insertion
	public int getPK(int orderID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(orderID) AS pk " +
						"FROM Orders";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(orderID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = orderID;
			}
			
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}

	
	public void complete(int orderID, String deliveryDate, String note) {
		String query = "UPDATE Orders "
					+ "SET deliveryDate = TO_DATE(?, 'MM/DD/YYYY'), note = ? "
					+ "WHERE Orders.orderID = ?";
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setString(1, deliveryDate);
			statement.setString(2, note);
			statement.setInt(3, orderID);
			statement.executeUpdate();
			System.out.println("OrderService:  Order completed");
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to complete order");
			e.printStackTrace();
		}
		
		
	}

	public void display() {
		String query = "SELECT * FROM Orders";
		try {
			Statement statement = this.conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			System.out.println("OrderID\tEmployeeID\tCustomerID\t"
							+ "Cost\tPaymentID\tDeliveryAddrID\tOrderDate\t"
							+ "ExpectedDate\tDeliveryDate\tNote");
			
			while(rs.next()) {
				int orderID = rs.getInt(1);
				int empID = rs.getInt(2);
				int customerID = rs.getInt(3);
				double cost = rs.getInt(4);
				int paymentID = rs.getInt(5);
				int deliveryAddrID = rs.getInt(6);
				Date orderDate = rs.getDate(7);
				Date expectedDate = rs.getDate(8);
				Date deliveryDate = rs.getDate(9);
				String note = rs.getString(10);
				
				System.out.println(orderID +"\t" + empID + "\t" + customerID + "\t" +
									cost + "\t" + paymentID + "\t" + deliveryAddrID + "\t" +
									orderDate + "\t" + expectedDate + "\t" + deliveryDate + "\t" + note);
				
			}
			
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to display orders");
			e.printStackTrace();
		}
		
	}


	public void refund(int orderID) {
		System.out.println("OrderService:  Refund issued for Order "+orderID);
		//TODO: Give this actual functionality to refund the amount of the order to the customer
	}


	public void viewCustomerOrders(int customerID) {
		String query = "SELECT * FROM Orders WHERE Orders.customerID = ?";
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, customerID);
			ResultSet rs = statement.executeQuery();
			
			System.out.println("OrderID\tCost\tPaymentID\tDeliveryAddrID\tOrderDate\t"
							+ "ExpectedDate\tDeliveryDate\tNote");
			
			while(rs.next()) {
				int orderID = rs.getInt(1);
				double cost = rs.getInt(4);
				int paymentID = rs.getInt(5);
				int deliveryAddrID = rs.getInt(6);
				Date orderDate = rs.getDate(7);
				Date expectedDate = rs.getDate(8);
				Date deliveryDate = rs.getDate(9);
				String note = rs.getString(10);
				
				System.out.println(orderID + "\t" + cost + "\t" + paymentID + "\t" + 
									deliveryAddrID + "\t" + orderDate + "\t" + 
									expectedDate + "\t" + deliveryDate + "\t" + note);
			}
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to display orders");
			e.printStackTrace();
		}
	}

	
	public void print(int orderID) {
		System.out.println("Your order is being printed...");
		//TODO: make some print function to print a customer's order
	}

	public int getPaymentID(int orderID) {
		int paymentID = 0;
		String query = "SELECT Orders.paymentID "
					+ "FROM Orders "
					+ "WHERE Orders.paymentID = ?";
		
		PreparedStatement statement;
		try {
			statement = this.conn.prepareStatement(query);
			statement.setInt(1, orderID);
			ResultSet rs = statement.executeQuery();
		
			if(rs.next()) {
				paymentID = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("OrderService:  Failed to find paymentID");
			e.printStackTrace();
		}
		return paymentID;
	}
}
	


