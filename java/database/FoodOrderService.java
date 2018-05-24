package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.FoodOrder;

public class FoodOrderService extends Service {

	public FoodOrderService(Connection conn) {
		super(conn);
	}

	
	@Override
	public void insert(Object obj) {
		FoodOrder foodOrder = (FoodOrder) obj;
		
		// get food values
		int foodOrderID = getPK(foodOrder.getFoodOrderID());
		foodOrder.setFoodID(foodOrderID);
		
		int orderID = foodOrder.getOrderID();
		int foodID = foodOrder.getFoodID();
		int quantity = foodOrder.getQuantity();
		
		// format query
		String query = "INSERT INTO FoodOrder "
					+ "(foodOrderID, orderID, foodID, quantity) " 
					+ "VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, foodOrderID);
			statement.setInt(2, orderID);
			statement.setInt(3, foodID);
			statement.setInt(4, quantity);
			statement.executeUpdate();
			System.out.println("FoodOrderService:  FoodOrder insert successful.");	
		} catch (SQLException e) {
			System.out.println("FoodOrderService:  Failed to insert FoodOrder.");
			e.printStackTrace();
		}
	}

	
	@Override
	public void delete(int foodOrderID) {
		String query = "DELETE FROM FoodOrder WHERE foodOrderID = ?";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, foodOrderID);
			statement.executeUpdate();
			System.out.println("FoodService:  Food deleted.");
		} catch (SQLException e) {
			System.out.println("FoodService:  Failed to delete food.");
			e.printStackTrace();
		}
	}
	
	
	// increment the primary key for new insertion
	public int getPK(int foodOrderID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(foodOrderID) AS pk " +
						"FROM FoodOrder";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(foodOrderID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = foodOrderID;
			}
			
		} catch (SQLException e) {
			System.out.println("FoodOrderService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}
}
