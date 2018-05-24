package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import database.CustomerService;
import database.EmployeeService;
import database.FoodService;
import database.OrderService;

public class Admin extends Employee {

	private static int adminCount;

	public Admin(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, Date lastLogin, int empID, Date hireDate, String title, int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin, empID, hireDate, title,
				workAddrID);
		adminCount++;
	}

	public static int getAdminCount() {
		return adminCount;
	}

	public static void setAdminCount(int adminCount) {
		Admin.adminCount = adminCount;
	}

	@Override
	public String toString() {
		return "Admin [toString()=" + super.toString() + "]";
	}

	@Override
	public void displayMenu(Scanner input, Connection conn) {
		System.out.println("\n---- Admin Menu ----\n");
		System.out.println("1)  View Orders");
		System.out.println("2)  Add Orders");
		System.out.println("3)  Edit Orders");
		System.out.println("4)  Complete Orders");
		System.out.println("5)  Delete Orders");
		System.out.println("6)  View Employees");
		System.out.println("7)  View Customers");
		System.out.println("8)  Issue Refund");
		System.out.println("9)  Log Out\n");
		
		boolean choosing = true;
		while(choosing) {
			System.out.print("Choose option: ");
			int choice = input.nextInt();
			
			if(choice == 1) {	
				viewOrders(conn);
			}
			else if(choice == 2) {
				addOrders(conn);
			}
			else if(choice == 3) {
				editOrders(conn);
			}
			else if(choice == 4) {
				completeOrders(conn);
			}
			else if(choice == 5) {
				deleteOrders(conn);
			}
			else if(choice == 6) {
				viewEmployees(conn);
			}
			else if(choice == 7) {

				viewCustomers(conn);
			}
			else if(choice == 8){
				issueRefund(conn);
			}
			else if(choice == 9){
				System.exit(0);
			}
			else {
				System.out.println("Not a valid option");
			}
		}
	}
		
		public void addOrders(Connection conn) {
			OrderService orderService = new OrderService(conn);
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Enter employee ID: ");
			int employeeID = scan.nextInt();
			
			System.out.println("Enter customer ID: ");
			int customerID = scan.nextInt();
			
			System.out.println("Enter cost: ");
			double cost = scan.nextDouble();
			
			System.out.println("Enter payment ID: ");
			int paymentID = scan.nextInt();
			
			System.out.println("Enter delivery address ID: ");
			int deliveryAddrID = scan.nextInt();
			
			System.out.println("Enter order date: ");
			String dateString = scan.next();
			LocalDate orderDate = LocalDate.parse(dateString);
			
			System.out.println("Enter expected date: ");
			String dateString2 = scan.next();
			LocalDate expectedDate = LocalDate.parse(dateString2);
			
			scan.close();
			
			Order newOrder = new Order();
			
			newOrder.setOrderID(0);
			newOrder.setCost(cost);
			newOrder.setCustomerID(customerID);
			newOrder.setDeliveryAddrID(deliveryAddrID);
			newOrder.setEmployeeID(employeeID);
			newOrder.setExpectedDate(expectedDate);
			newOrder.setOrderDate(orderDate);
			newOrder.setPaymentID(paymentID);
			
			orderService.insert(newOrder);
			
		}
		
		public void deleteOrders(Connection conn){
			OrderService orderService = new OrderService(conn);
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the order ID to delete: ");
			int orderID = scan.nextInt();
			
			orderService.delete(orderID);
			scan.close();
		}
		
		public void viewEmployees(Connection conn){
			EmployeeService employeeService = new EmployeeService(conn);
			employeeService.display();
		}
		
		public void viewCustomers(Connection conn){
			CustomerService customerService = new CustomerService(conn);
			customerService.display();
			
		}
		
		public void completeOrders(Connection conn){
			OrderService orderService = new OrderService(conn);
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter order ID to complete: ");
			int orderID = scan.nextInt();
			System.out.println("Enter delivery date: ");
			String date = scan.next();
			LocalDate deliveryDate = LocalDate.parse(date);
			System.out.println("Would you like to add a note? y/n");
			String addnote = scan.next();
			String note = "";
			
			if(addnote=="y"||addnote=="Y"){
				System.out.println("Enter note: ");
				note = scan.nextLine();
				
			}else if(addnote=="n"||addnote=="N"){
				
				note=null;
			}else{
				System.out.println("Not a valid option.");
			}
			
			
			orderService.complete(orderID, deliveryDate, note);
			scan.close();
		}
		
		public void viewOrders(Connection conn){
			OrderService orderService = new OrderService(conn);
			orderService.display();
			
		}
		
		public void editOrders(Connection conn){
			
		}
		
		public void issueRefund(Connection conn){
			OrderService orderService = new OrderService(conn);
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter order ID to refund: ");
			int orderID = scan.nextInt();
			orderService.refund(orderID);
			scan.close();
		}
	
}
