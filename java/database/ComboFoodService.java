package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.ComboFood;

public class ComboFoodService {
	
		
static Connection con;


public ComboFoodService(Connection con) {
	super();
	this.con=con;
}

private int getPK() {
	int lastPK = 0;
	int newPK = 0;
	String query = "SELECT MAX(comboFoodID) FROM combofood";
	
	try {
		Statement statement = this.con.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			lastPK = result.getInt("comboFoodID");
		}
		newPK = lastPK + 1;
	} catch (SQLException e) {
		System.out.println("Failed to connect to database.");
		e.printStackTrace();
	}
	return newPK;
}


public void insert(ComboFood combofood)
{

	//INSERT INTO TABLE
	
	int comboFoodID;
	if(combofood.getComboFoodID() == 0) {
		comboFoodID = getPK();
		combofood.setComboFoodID(comboFoodID);
	}
	else {
		comboFoodID = combofood.getComboFoodID();
	}
	
	System.out.println("Inserting a new combo food...");
			
	
	try {
		PreparedStatement insertStmt = con.prepareStatement("insert into comboFood values (?,?,?)");
		insertStmt.setInt(1,comboFoodID); 
		insertStmt.setInt(2,combofood.getFoodID()); 
		insertStmt.setInt(3,combofood.getComboID()); 
		insertStmt.execute();
		System.out.println();
		System.out.println("Combo food added with combo food ID "+comboFoodID+"...");
		insertStmt.close();
	} catch (SQLException e) {
		System.out.println("Error: SQL Exception.");
		e.printStackTrace();
	}
	
}

public void delete(int comboFoodID)
{
	//DELETE FROM TABLE
	System.out.println("Deleting combo food with combo food ID "+comboFoodID+"...");
	
	try{
		PreparedStatement deleteStmt = con.prepareStatement("delete from comboFood where comboFoodID=?");
		deleteStmt.setInt(1,comboFoodID); 
		deleteStmt.execute();
		System.out.println();
		System.out.println("Combo food deleted.");
		deleteStmt.close();
	}catch (SQLException e) {
		System.out.println("Error: SQL Exception.");
		e.printStackTrace();
	}
	
}

public void display()

{
	
		//DISPLAY FROM TABLE
		System.out.println("Displaying combo food...");
		System.out.println("Combo Food ID		Food ID		Combo ID");
		
		try{
			PreparedStatement oracleStmt = con.prepareStatement("select * from comboFood");
			oracleStmt.execute();
			ResultSet oracleRs = oracleStmt.getResultSet();
			while(oracleRs.next())
			{
				System.out.println(oracleRs.getInt(1)+"		"+oracleRs.getInt(2)+"		"+oracleRs.getInt(3));
			}
			System.out.println();
			System.out.println("Combo Food Displayed.");
			oracleStmt.close();
		}catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}

	}
}
	
