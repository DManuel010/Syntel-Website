package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.ComboFood;

public class ComboFoodService extends Service {

	public ComboFoodService(Connection conn) {
		super(conn);
	}
	
	
	public void insert(Object obj) {
		ComboFood combofood = (ComboFood) obj;
	
		//INSERT INTO TABLE
		System.out.println("Inserting a new combo food...");
				
		try {
			PreparedStatement insertStmt = conn.prepareStatement("insert into comboFood values (?,?,?)");
			insertStmt.setInt(1,combofood.getComboFoodID()); 
			insertStmt.setInt(2,combofood.getFoodID()); 
			insertStmt.setInt(3,combofood.getComboID()); 
			insertStmt.execute();
			System.out.println();
			System.out.println("Combo food added with combo food ID "+combofood.getComboFoodID()+"...");
			insertStmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
	}
	
	public void delete(int comboFoodID) {
		//DELETE FROM TABLE
		System.out.println("Deleting combo food with combo food ID "+comboFoodID+"...");
		
		try {
			PreparedStatement deleteStmt = conn.prepareStatement("delete from comboFood where comboFoodID=?");
			deleteStmt.setInt(1,comboFoodID); 
			deleteStmt.execute();
			System.out.println();
			System.out.println("Combo food deleted.");
			deleteStmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
	}
    

	// increment the primary key for new insertion
	public int getPK(int comboFoodID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(comboFoodID) AS pk " +
						"FROM ComboFood";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(comboFoodID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = comboFoodID;
			}
			
		} catch (SQLException e) {
			System.out.println("ComboFoodService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}


	public void display() {
		//DISPLAY FROM TABLE
		System.out.println("Displaying combo food...");
		System.out.println("Combo Food ID		Food ID		Combo ID");
			
		try {
			PreparedStatement oracleStmt = conn.prepareStatement("select * from comboFood");
			oracleStmt.execute();
			ResultSet oracleRs = oracleStmt.getResultSet();
			while(oracleRs.next()) {
				System.out.println(oracleRs.getInt(1)+"		"+oracleRs.getInt(2)+"		"+oracleRs.getInt(3));
			}
			System.out.println();
			System.out.println("Combo Food Displayed.");
			oracleStmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
	}
}

	
