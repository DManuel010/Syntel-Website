package entities;

import java.sql.Connection;
import java.util.Scanner;

import database.CustomerService;
import database.EmployeeService;
import database.OrderService;

public class Admin extends Employee {

	private static int adminCount;

	public Admin(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, String lastLogin, int empID, String hireDate, String title, int workAddrID) {
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
	public boolean displayMenu(Scanner input, Connection conn) {
		boolean choosing = true;
		while(choosing) {
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
		
			System.out.print("Choose option: ");
			int choice = input.nextInt();
			
			if(choice == 1) {	
				viewOrders(conn);
			}
			else if(choice == 2) {
				addOrders(conn, input);
			}
			else if(choice == 3) {
				viewOrders(conn);
				editOrders(conn);
			}
			else if(choice == 4) {
				viewOrders(conn);
				completeOrders(conn, input);
			}
			else if(choice == 5) {
				viewOrders(conn);
				deleteOrders(conn, input);
			}
			else if(choice == 6) {
				viewEmployees(conn);
			}
			else if(choice == 7) {
				viewCustomers(conn);
			}
			else if(choice == 8){
				viewOrders(conn);
				issueRefund(conn, input);
			}
			else if(choice == 9){
				choosing = false;
			}
			else {
				System.out.println("Not a valid option");
			}
		}
		return choosing;
	}
		
	public void addOrders(Connection conn, Scanner scan) {
		OrderService orderService = new OrderService(conn);
		
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
		String orderDate = scan.next();
		
		System.out.println("Enter expected date: ");
		String expectedDate = scan.next();
		
		Order newOrder = new Order(0, employeeID, customerID,
									cost, paymentID, deliveryAddrID, 
									orderDate, expectedDate, null, null);
		
		orderService.insert(newOrder);
	}
		
	public void deleteOrders(Connection conn, Scanner scan){
		OrderService orderService = new OrderService(conn);
		System.out.println("Enter the order ID to delete: ");
		int orderID = scan.nextInt();
		
		orderService.delete(orderID);
	}
	
	public void viewEmployees(Connection conn){
		EmployeeService employeeService = new EmployeeService(conn);
		employeeService.display();
	}
	
	public void viewCustomers(Connection conn){
		CustomerService customerService = new CustomerService(conn);
		customerService.display();
		
	}
	
	public void completeOrders(Connection conn, Scanner scan){
		OrderService orderService = new OrderService(conn);
		System.out.println("Enter order ID to complete: ");
		int orderID = scan.nextInt();
		System.out.println("Enter delivery date (MM/DD/YYY): ");
		String deliveryDate = scan.next();
		System.out.println("Would you like to add a note? (Y/N): ");
		String addnote = scan.next().toUpperCase();
		String note = "";
		
		if(addnote.equals("Y")){
			scan.nextLine();
			System.out.println("Enter note: ");
			note = scan.nextLine();
			
		}else if(addnote.equals("N")){
			note=null;
		}else{
			System.out.println("Not a valid option.");
		}
		
		
		orderService.complete(orderID, deliveryDate, note);
	}
	
	public void viewOrders(Connection conn){
		OrderService orderService = new OrderService(conn);
		orderService.display();
		
	}
	
	public void editOrders(Connection conn){
		System.out.println("Coming soon...");
	}
	
	public void issueRefund(Connection conn, Scanner scan){
		OrderService orderService = new OrderService(conn);
		System.out.println("Enter order ID to refund: ");
		int orderID = scan.nextInt();
		orderService.refund(orderID);
	}
}
