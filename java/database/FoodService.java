package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Food;

public class FoodService extends Service {

	public FoodService(Connection conn) {
		super(conn);
	}
	
	
	@Override
	public void insert(Object obj) {
		Food food = (Food) obj;
		
		// get food values
		int foodID = getPK(food.getFoodID());
		food.setFoodID(foodID);

		String name = food.getName();
		String group = food.getGroup();
		double price = food.getPrice();
		String description = food.getDescription();
		int stock = food.getStock();
		
		// format query
		String query = "INSERT INTO Food "
					+ "(foodID, name, group, price, description, stock) " 
					+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, foodID);
			statement.setString(2, name);
			statement.setString(3, group);
			statement.setDouble(4, price);
			statement.setString(5, description);
			statement.setInt(6, stock);
			statement.executeUpdate();
			System.out.println("FoodService:  Food insert successful.");	
		} catch (SQLException e) {
			System.out.println("FoodService:  Failed to insert food.");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete(int foodID) {	
		String query = "DELETE FROM Food WHERE foodID = ?";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, foodID);
			statement.executeUpdate();
			System.out.println("FoodService:  Food deleted.");
		} catch (SQLException e) {
			System.out.println("FoodService:  Failed to delete food.");
			e.printStackTrace();
		}
	}
	
	
	public ResultSet selectAll() {
		String query = "SELECT * FROM Food";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("FoodService:  Failed to select all food");
			e.printStackTrace();
		}
		return null;
	}
	
	
	// increment the primary key for new insertion
	private int getPK(int foodID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(foodID) AS pk " +
						"FROM Food";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(foodID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = foodID;
			}
			
		} catch (SQLException e) {
			System.out.println("FoodService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}
	
	
	public double priceCheck(int foodID) {
		String query = "SELECT Food.price FROM Food WHERE Food.foodID = ?";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, foodID);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				double price = result.getDouble(1);
				return price;
			}
			
		} catch (SQLException e) {
			System.out.println("FoodService:  Failed to price check");
			e.printStackTrace();
		}
		return 0.00;
	}
}
