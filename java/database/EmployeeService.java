package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Employee;

public class EmployeeService {

	static Connection con;


	public EmployeeService(Connection con) {
		super();
		this.con=con;
	}

	private int getPK() {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(employeeID) FROM employee";
		
		try {
			Statement statement = this.con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				lastPK = result.getInt("employeeID");
			}
			newPK = lastPK + 1;
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
		}
		return newPK;
	}


	public void insert(Employee employee)
	{

		//INSERT INTO TABLE
		int employeeID;
		if(employee.getEmpID() == 0) {
			employeeID = getPK();
			employee.setEmpID(employeeID);
		}
		else {
			employeeID = employee.getEmpID();
		}
		
		System.out.println("Inserting a new employee...");
				
		
		try {
			PreparedStatement insertStmt = con.prepareStatement("insert into employee values (?,?,?,?)");
			insertStmt.setInt(1,employeeID); 
			insertStmt.setObject(2,employee.getHireDate()); 
			insertStmt.setString(3,employee.getTitle()); 
			insertStmt.setInt(4,employee.getWorkAddrID()); 
			insertStmt.execute();
			System.out.println();
			System.out.println("User added with employee ID "+employeeID);
			insertStmt.close();
		} catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
		
	}

	public void delete(int employeeID)
	{
		//DELETE FROM TABLE
		System.out.println("Deleting employee with employee ID "+employeeID+"...");
		
		try{
			PreparedStatement deleteStmt = con.prepareStatement("delete from employee where employeeID=?");
			deleteStmt.setInt(1,employeeID); 
			deleteStmt.execute();
			System.out.println();
			System.out.println("Employee deleted.");
			deleteStmt.close();
		}catch (SQLException e) {
			System.out.println("Error: SQL Exception.");
			e.printStackTrace();
		}
		
	}

	public void display()

	{
		
		//DISPLAY FROM TABLE
			System.out.println("Displaying employees...");
			
			
			try{
				PreparedStatement oracleStmt = con.prepareStatement("select * from employee");
				oracleStmt.execute();
				ResultSet oracleRs = oracleStmt.getResultSet();
				
				while(oracleRs.next())
				{
					System.out.println(oracleRs.getInt(1)+"		"+oracleRs.getObject(2)+"		"+oracleRs.getString(3)+"		"+oracleRs.getInt(4));
				}
				System.out.println();
				System.out.println("Employees Displayed.");
				oracleStmt.close();
			}catch (SQLException e) {
				System.out.println("Error: SQL Exception.");
				e.printStackTrace();
			}

		}
	
}
