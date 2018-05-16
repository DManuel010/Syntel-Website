package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CaseStudy.sqlConnection;
import interfaces.Service;

public class ComboFoodService implements Service {
	
	Connection con;
	

	@Override
	public void insert(ComboFood combo) {
		try {
			CallableStatement oracleCallableStmt = con.prepareCall("{call SP_INS_COMBO(?,?,?)}");
			oracleCallableStmt.setInt(1, combo.getComboFoodID());
			oracleCallableStmt.setInt(1, combo.getFoodID());
			oracleCallableStmt.setInt(2, combo.getComboID());
			oracleCallableStmt.execute();
			System.out.println("Combo Added."); 
			oracleCallableStmt.close();
		} catch (SQLException e) {
			System.out.println("Failed to add combo.");
		}
		
	}

	@Override
	public void delete(int comboFoodID) {
		try {
			CallableStatement oracleCallableStmt;
			oracleCallableStmt = con.prepareCall("{call SP_DEL_COMBO(?)}");
			oracleCallableStmt.setInt(1, comboFoodID);
			oracleCallableStmt.execute();
			System.out.println("Combo Deleted."); 
			oracleCallableStmt.close();
		} catch (SQLException e) {
			System.out.println("Failed to delete combo.");
		}
		
	}
}
	
