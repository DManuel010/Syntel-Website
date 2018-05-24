package caseStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import exceptions.MyException;

public class orderServices {

	/*private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "hr";
	*/
	orderServices os = new orderServices();
	int oid= 0;
	
	String email = null;
	String country = null;
	String state = null;
	String city = null;
	String streetnum = null;
	int roomnum = 0;
	int zip = 0;
	Scanner sc = new Scanner(System.in);
	int year = 0;
	int month = 0;
	int day = 0;
	int hour =0;
	Employee em = new Employee();
	Connection con = getCon();
	PreparedStatement pp = null;
	
	private  final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	
//	public   void completeOrder(){
//		//this method will sned updates
//		//to the database of complete orders. Will recieving this informatino 
//		// from employees who submitted completed order forms
//		//String complete = "Insert into Employees(Hire_Date)";
//		
//		
//	
//		
//		
//		
//		String insertTableSQL = "INSERT INTO ORDERS"
//				+ "(ORDERDATE,DELIVERYDATE) VALUES"
//				+ "(?,?,?)";
//		
//		try{
//			
//			getCurrentTimeStamp();
//			pp  = con.prepareStatement(insertTableSQL);
//			pp.setTimestamp(1,getCurrentTimeStamp());// order first nam
//			pp.setTimestamp(2, getCurrentTimeStamp());
//			
//			
//			
//	
//			//preparedstatement.setString(2, "Carlos");// order first name
//			
//			
//			
//		//	preparedstatement.setTimestamp(4,getCurrentTimeStamp());//time delivered
//			
//		         
//			//step 4 execute a query and get results
//			//create the result set variable set it to the statement varaible. execute the query 
//			pp.addBatch();
//			pp.executeBatch();
//			con.setAutoCommit(true);
//			System.out.println("Order completed");
//			
//			
//			
//		
//			
//			}catch(Exception e){
//				
//			}
//	}
	public void cancelOrder(){
		
		int oid = 0;
		
//		String cancel ="DELETE * FROM ORDERS"
//				+"WHERE CUSTOMERID =?";
//		
//		String find = "SELECT ORDERID,ORDERDATE,DELIVERYDATE,CUSTOMERID"
//				+"FROM ORDERS"
//				+"WHERE ORDERID =?";
//		
//		pp = con.prepareStatement(find);
//		
//		System.out.println("Whats your customer id?");
//		cid = sc.nextInt();
//		
//		pp.setInt(1, cid);
//		System.out.println("-------------------------CHOOSE AN ORDER TO CANCEL--------------------------");
//		oid = sc.nextInt();
//		pp = con.prepareStatement(cancel);
//		pp.setInt(2, oid);
//		pp.addBatch();
//		pp.executeBatch();
//		con.setAutoCommit(true);
		
		
		try{
			String query = String.format(
					"DELETE FROM ORDERS " +
					"WHERE ORDERID = %d",
					oid
				);
		
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
			System.out.println("order deleted.");
		} catch (SQLException e) {
			System.out.println("Failed to delete.");
			e.printStackTrace();
		}
		}catch(Exception ex){
			
		}
		
	}
	
	public void changeOrder() throws SQLException{
		//WILL CHANGE TO ORDERS
		String change ="UPDATE LOCATION"
				+"SET COUNTRY=?,STATE=?,CITY=?,STREETNUM=?,ROOMNUM=?,ZIP=?,EXPECTEDDATE"
				+"WHERE EMAIL=?"
				+"(?,?,?,?,?,?,?,?)";
				
		//this method will allow the customer to change their order 
	
		expectedDate();
		
		try{//by querying and updating existing database
		
		
		PreparedStatement pp = con.prepareStatement(change);
		
		pp.setString(1,country);
		pp.setString(2,state);
		pp.setString(3,city);
		pp.setString(4,streetnum);
		pp.setInt(5,roomnum);
		pp.setInt(6,zip);
		pp.setObject(7, month,day,year);
		pp.setString(8, email);
		pp.addBatch();
		
		pp.executeBatch();
		con.setAutoCommit(true);
		System.out.println("UPDATED");
		}catch(Exception e){
			System.out.println("ERROR NOT UPDATED");
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
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","deus","1234");
		} catch (Exception ex) {
			System.out.print("error");
		
		
	}

		return con;

	}
	public static Date buildDate(int year, int month, int day) {
		Calendar myCalendar = new GregorianCalendar(year, month, day);
		Date dateObject = myCalendar.getTime();
		return dateObject;
	}
	public void expectedDate(){
		
		System.out.println("What month did you want your delivery?");
		month = sc.nextInt();
		
		
		System.out.println("What day did you want your delivery?");
		day = sc.nextInt();
		
		
		System.out.println("What hour did you want your delivery?");
		hour = sc.nextInt();
		
		
		
	}
	public void addOrder(){
		
		
		if(os.getOrderID()==0){
			oid = getPK();
			
		}
		else {
			oid = os.getOrderID();
		}
		double cost =0;
		String insert = "INSERT INTO ORDERS"
				+ "(ORDERID,COST) VALUES"
				+ "(?,?)";
		
	try {
		pp = con.prepareStatement(insert);
		pp.setInt(1, oid);
		pp.setDouble(2, cost);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
//	public void changeExten(){
//		
//		System.out.println("Whats your new country?");
//		country = sc.nextLine();
//		
//		System.out.println("state?");
//		state = sc.nextLine();
//		
//		System.out.println("city?");
//		city = sc.nextLine();
//		
//		System.out.println("Street Number?");
//		streetnum = sc.nextLine();
//		
//		System.out.println("room number?");
//		roomnum = sc.nextInt();
//		
//		System.out.println("zipcode?");
//		zip = sc.nextInt();
//		
//		System.out.println("To update your info, please enter your email address");
//		email = sc.nextLine();
//	}
	
	private int getPK() {
		int lastPK = 0;
		int newPK = 0;
		String query = "SELECT MAX(ORDERID) FROM ORDERS";
		
		try {
			Statement statement = this.con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				lastPK = result.getInt("ORDERID");
			}
			newPK = lastPK + 1;
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
		}
		return newPK;
	}
	public void setOrderID(int oid){
		this.oid = oid;
	}
	public int getOrderID(){
		return oid;
	}
}
	

