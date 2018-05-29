package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Employee;

public class EmployeeService extends Service {


	public EmployeeService(Connection conn) {
		super(conn);
	}

	@Override
	public void insert(Object obj) {
		Employee employee = (Employee) obj;
		
		//INSERT INTO TABLE
		int employeeID;
		if(employee.getEmpID() == 0) {
			employeeID = getPK(employee.getEmpID());
			employee.setEmpID(employeeID);
		}
		else {
			employeeID = employee.getEmpID();
		}
		
		String query = "INSERT INTO Employee "
					+ "(employeeID, firstName, lastName, email, "
					+ "		hireDate, title, loginID, phoneNumber, "
					+ "		workAddrID, homeAddrID, lastLogin) "
					+ "VALUES(?, ?, ?, ?, TO_DATE(?, 'MM/DD/YYYY'), "
					+ "			?, ?, ?, ?, ?, SYSDATE)";
				
		System.out.println("Inserting a new employee...");
		try {
			PreparedStatement insertStmt = this.conn.prepareStatement(query);
			insertStmt.setInt(1,employeeID); 
			insertStmt.setString(2, employee.getFirstName());
			insertStmt.setString(3, employee.getLastName());
			insertStmt.setString(4, employee.getEmail());
			insertStmt.setString(5, employee.getHireDate());
			insertStmt.setString(6, employee.getTitle());
			insertStmt.setInt(7, employee.getLoginID());
			insertStmt.setString(8, employee.getPhoneNumber());
			insertStmt.setInt(9, employee.getWorkAddrID());
			insertStmt.setInt(10, employee.getHomeAddrID());
			insertStmt.execute();
			System.out.println();
			System.out.println("EmployeeService:  Employee added with employee ID "+employeeID);
			insertStmt.close();
		} catch (SQLException e) {
			System.out.println("EmployeeService:  Failed to insert employee");
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int employeeID)
	{
		//DELETE FROM TABLE
		System.out.println("Deleting employee with employee ID "+employeeID+"...");
		
		try{
			PreparedStatement deleteStmt = this.conn.prepareStatement("delete from employee where employeeID=?");
			deleteStmt.setInt(1,employeeID); 
			deleteStmt.execute();
			System.out.println();
			System.out.println("EmployeeService:  Employee deleted.");
			deleteStmt.close();
		}catch (SQLException e) {
			System.out.println("EmployeeService:  Failed to delete employee");
			e.printStackTrace();
		}
		
	}
	
	// increment the primary key for new insertion
	private int getPK(int empID) {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(employeeID) AS pk " +
						"FROM Employee";
		
		try {
			Statement statement = this.conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				lastPK = result.getInt("pk");
			}
			
			if(empID <= lastPK) {
				newPK = lastPK + 1;
			}
			else {
				newPK = empID;
			}
			
		} catch (SQLException e) {
			System.out.println("FoodService:  Failed to get new Primary Key.");
			e.printStackTrace();
		}
		return newPK;
	}

	
	public void display() {
		String query = "SELECT * FROM Employee";
		//DISPLAY FROM TABLE
		System.out.println("Displaying employees...");
		try{
			Statement oracleStmt = this.conn.createStatement();
			ResultSet oracleRs = oracleStmt.executeQuery(query);
			
			System.out.println("EmployeeID\tFirstName\tLastName\t"
							+ "Email\tHireDate\tTitle\tPhoneNumber\t"
							+ "LastLogin");
			
			while(oracleRs.next()) {
				int employeeID = oracleRs.getInt(1);
				String firstName = oracleRs.getString(2);
				String lastName = oracleRs.getString(3);
				String email = oracleRs.getString(4);
				String hireDate = oracleRs.getString(5);
				String title = oracleRs.getString(6);
				String phoneNumber = oracleRs.getString(8);
				String lastLogin = oracleRs.getString(11);
				
				System.out.println(employeeID + "\t" + firstName + "\t" +
									lastName + "\t" + email + "\t" +
									hireDate + "\t" + title + "\t" +
									phoneNumber + "\t" + lastLogin);
			}
		} catch (SQLException e) {
			System.out.println("EmployeeService:  Failed to display employees");
			e.printStackTrace();
		}
	}

	public int getLoginID(int empID) {
		String query = "SELECT Employee.loginID FROM Employee WHERE Employee.employeeID = ?";
		
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, empID);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				int loginID = rs.getInt(1);
				return loginID;
			}
		} catch (SQLException e) {
			System.out.println("EmployeeService:  Failed to get loginID from employee");
			e.printStackTrace();
		}
		return 0;
	}
}
