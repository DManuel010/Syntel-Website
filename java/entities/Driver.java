package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

public class Driver extends Employee {
	Connection con = getCon();
	PreparedStatement pp = null;
	
	
	Driver dr = new Driver();
	private static int driverCount;

	public Driver(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, Date lastLogin, int empID, Date hireDate, String title, int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin, empID, hireDate, title,
				workAddrID);
		driverCount++;
	}

	public static int getDriverCount() {
		return driverCount;
	}

	public static void setDriverCount(int driverCount) {
		Driver.driverCount = driverCount;
	}

	@Override
	public String toString() {
		return "Driver [toString()=" + super.toString() + "]";
	}

	@Override
	public void displayMenu(Scanner input, Connection conn) {
		System.out.println("\n---- Driver Menu ----\n");
		System.out.println("1) View Orders");
		System.out.println("2) Complete Orders");
		System.out.println("3) Log Out\n");
		
		boolean choosing = true;
		while(choosing) {
			System.out.print("Choose option: ");
			int choice = input.nextInt();
			
			if(choice == 1) {
				dr.viewOrder(conn);
				
			}
			else if(choice == 2) {
				dr.completeOrder();
			}
			else if(choice == 3) {
				dr.logout();
			}
			
			
			else {
				System.out.println("Not a valid option");
			}
		}
	}
	
	public void viewOrder(Connection conn) {
		

		int eid;
		String elgibleOrders = "SELECT ORDERID,ORDERDATE FROM ORDERS"
				+ "WHERE EMPLOYEEID =?";
		try {
			pp = con.prepareCall(elgibleOrders);
			pp.setInt(1, eid);
		
			pp.addBatch();
			
			
			pp.executeBatch();
			
			
			
			}catch (SQLException e) {
				e.printStackTrace();
		} 
		}
	
	public void logout(){
		System.exit(0);
	}
	private  java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	public void completeOrder(){
		
		orderServers os = new orderServices();
		Employee em = new Employee();
		Customer c = new Customer();
		Food foo = new Food();
		Payment pay = new Payment();
		Order or = new Order();

		Scanner sc = new Scanner(System.in);
		String insert = "INSERT INTO ORDERS(ORDERID,EMPLOYEEID,CUSTOMERID,COST,PAYMENTID,DELIVERYADDRID,ORDERDATE,EXPECTEDDATE,DELIVERYDATE,NOTE)"
				+"VALUES('?','?','?','?','?','?','?','?','?','?')";
			
		System.out.println("Any notes you would like to add?");
		notes = sc.nextLine();
		pp = con.prepareCall(insert);
		
		pp.setInt(1,os.getOrderID());
		pp.setInt(2, em.getEmployeeID());
		pp.setInt(3, c.getCustomerID());
		pp.setDouble(4,foo.getPrice());
		pp.setInt(5,pay.getPaymentID());
		pp.setInt(6,or.getDeliveryAddrID());
		pp.setTimestamp(7,getCurrentTimeStamp());
		pp.setDate(8, dr.buildDate(date));
		pp.setDate(9, dr.buildDate(date));
		pp.setString(10,notes);
		pp.addBatch();
		
		
		pp.executeBatch();
		con.setAutoCommit(true);
		
				
	}
	public static LocalDate buildDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		System.out.println(localDate);
		return localDate;
	}
	
	
}
