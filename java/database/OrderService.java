package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
		
		int employeeID = order.getOrderID();
		int customerID = order.getCustomerID();
		double cost = order.getCost();
		int paymentID = order.getPaymentID();
		int deliveryAddrID = order.getDeliveryAddrID();
		LocalDate orderDate = order.getOrderDate();
		LocalDate expectedDate = order.getExpectedDate();
		LocalDate deliveryDate = order.getDeliveryDate();
		String note = order.getNote();
		
		// format query
		String query = "INSERT INTO Orders ("
						+ "orderID, employeeID, customerID, cost, "
						+ "paymentID, deliveryAddrID, orderDate, "
						+ "expectedDate, deliveryDate, note) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, orderID);
			statement.setInt(2, employeeID);
			statement.setInt(3, customerID);
			statement.setDouble(4, cost);
			statement.setInt(5, paymentID);
			statement.setInt(6, deliveryAddrID);
			statement.setObject(7, orderDate);
			statement.setObject(8, expectedDate);
			statement.setObject(9, deliveryDate);
			statement.setString(10, note);
			statement.executeUpdate();
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
	private int getPK(int orderID) {
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
}
	

