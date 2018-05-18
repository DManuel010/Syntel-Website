package caseStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import exceptions.MyException;

public class orderServices {

	/*private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "hr";
	*/
	Employee em = new Employee();
	
	private  final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	
	public   void completeOrder()throws MyException{
		//this method will sned updates
		//to the database of complete orders. Will recieving this informatino 
		// from employees who submitted completed order forms
		//String complete = "Insert into Employees(Hire_Date)";
		PreparedStatement preparedstatement = null;
		String insertTableSQL = "INSERT INTO EMPLOYEE"
				+ "(EMPLOYEE_ID, FIRST_NAME,LAST_NAME, HIRE_DATE) VALUES"
				+ "(?,?,?,?)";
		try{
			Connection con = getCon();
			getCurrentTimeStamp();
			preparedstatement  = con.prepareStatement(insertTableSQL);
			preparedstatement.setInt(1,115);//this will become order_id
			preparedstatement.setString(2, "Carlos");// order first name
			preparedstatement.setString(3,"Henderson");//order last name
			preparedstatement.setTimestamp(4,getCurrentTimeStamp());//time delivered
			
		         
			//step 4 execute a query and get results
			//create the result set variable set it to the statement varaible. execute the query 
			
			preparedstatement.executeBatch();
			getCon().setAutoCommit(true);
			System.out.println("Time stamp successful");
			
			
			
			throw new MyException("TimeStamp not successful");
			
			}catch(Exception e){
				
			}
	}
	public void showOrders() throws MyException{
		//This method will show orders to employees
		
		try{
		Connection con = getCon();
	
		
		Statement oracleStmt = con.createStatement();
		
		//step 4 execute a query and get results
		//THIS WILL CHANGE TO ALL FROM ORDERS
		ResultSet oracleRs = oracleStmt.executeQuery("SELECT * FROM EMPLOYEES");
		
		
		while(oracleRs.next()){
			System.out.println(oracleRs.getString("first_name")+" "+oracleRs.getString(2));
		}
		throw new MyException("Query error, orders not show");
		}catch(Exception ex){
			
		}
	}
	
	public void changeOrder() throws SQLException{
		//WILL CHANGE TO ORDERS
		String change ="UPDATE EMPLOYEES SET EMAIL = 'chenderson6191@gmail.com' WHERE EMPLOYEE_ID = 101";
		//this method will allow the customer to change their order 
		
		
		try{//by querying and updating existing database
		Connection con = getCon();
		PreparedStatement st = con.prepareStatement(change);
		st.addBatch();
		
		st.executeBatch();
		con.setAutoCommit(true);
		System.out.println("UPDATED");
		}catch(Exception e){
			System.out.println("ERROR NOT UPDATED");
		}
	}
	public void deleteOrder()throws MyException{
		String deleteSQL = "DELETE EMPLOYEE WHERE EMPLOYEE_ID = ?";
		
		try{
		Connection con = getCon();
		PreparedStatement st = con.prepareStatement(deleteSQL);
		st.setInt(1, 110);
		st.addBatch();
		st.executeBatch();
		con.setAutoCommit(true);
		System.out.println("RECORD DELTED");

		
		}catch(Exception ex){
			System.out.println("ERROR");
		}
	}
	private  java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	public static Connection getCon(){
		Connection con = null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
		} catch (Exception ex) {
			System.out.print("error");
		
		
	}

		return con;

	}

}
	

