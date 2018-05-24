package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import database.FoodOrderService;
import database.FoodService;
import database.OrderService;

public class Customer extends User {

	private int customerID;
	private Date dateOfBirth;
	private int cardID;
	private Date dateOfRegister;
	
	private static int customerCount;
	
	public Customer(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, Date lastLogin, int customerID, Date dateOfBirth, int cardID, Date dateOfRegister) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin);
		this.customerID = customerID;
		this.dateOfBirth = dateOfBirth;
		this.cardID = cardID;
		this.dateOfRegister = dateOfRegister;
		customerCount++;
	}

	public Customer(String firstName, String lastName, String email, int loginID, String phoneNumber, int homeAddrID,
			Date lastLogin) {
		super(firstName, lastName, email, loginID, phoneNumber, homeAddrID, lastLogin);
		customerCount++;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public Date getDateOfRegister() {
		return dateOfRegister;
	}

	public void setDateOfRegister(Date dateOfRegister) {
		this.dateOfRegister = dateOfRegister;
	}

	public static int getCustomerCount() {
		return customerCount;
	}

	public static void setCustomerCount(int customerCount) {
		Customer.customerCount = customerCount;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", dateOfBirth=" + dateOfBirth + ", cardID=" + cardID
				+ ", dateOfRegister=" + dateOfRegister + ", toString()=" + super.toString() + "]";
	}

	@Override
	public void displayMenu(Scanner input, Connection conn) {
		System.out.println("\n---- Customer Menu ----\n");
		System.out.println("1)  Make Order");
		System.out.println("2)  Edit Order");
		System.out.println("3)  Delete Order");
		System.out.println("4)  View Order");
		System.out.println("5)  Print Order");
		System.out.println("6)  View Menu");
		System.out.println("7)  Log Out\n");
		
		boolean choosing = true;
		while(choosing) {
			System.out.print("Choose option: ");
			int choice = input.nextInt();
			
			if(choice == 1) {
				viewMenu(conn);
				makeOrder(conn, input);
			}
			else if(choice == 2) {
			}
			else if(choice == 3) {
			}
			else if(choice == 4) {
				
			}
			else if(choice == 5) {
				
			}
			else if(choice == 6) {
				viewMenu(conn);
			}
			else if(choice == 7) {
				System.exit(0);
			}
			else {
				System.out.println("Not a valid option");
			}
		}
		
	}
	
	
	public void viewMenu(Connection conn) {
		FoodService foodService = new FoodService(conn);
		ResultSet rs = foodService.selectAll();
		
		try {
			System.out.println("ID\tName\tPrice");
			while(rs.next()) {
				int foodID = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(4);
				System.out.println(foodID + "\t" + name + "\t$" + price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void makeOrder(Connection conn, Scanner input) {
		OrderService orderService = new OrderService(conn);
		FoodOrderService foodOrderService = new FoodOrderService(conn);
		FoodService foodService = new FoodService(conn);
		
		int orderID = orderService.getPK(0);
		int total = 0;
		
		boolean shopping = true;
		while(shopping) {
			System.out.print("Enter food item ID: ");
			int foodID = input.nextInt();
			
			System.out.print("Quantity: ");
			int quantity = input.nextInt();
			
			int foodOrderID = foodOrderService.getPK(0);
			FoodOrder foodOrder = new FoodOrder(foodOrderID, orderID, foodID, quantity);
			double price = foodService.priceCheck(foodID);
			total += price * quantity;
			
			foodOrderService.insert(foodOrder);
			
			boolean completing = true;
			while(completing) {
				System.out.print("Complete Order (Y/N): ");
				String completeOrder = input.next().toUpperCase();
			
				if(completeOrder.equals("Y")) {
					System.out.println("Completing order");
					
					//create order here
					Order order = new Order();
					//orderService.insert(order);
					shopping = false;
					completing = false;
				}
				else if(!completeOrder.equals("N")) {
					System.out.println("Not a valid input, please input Y or N");
				}
			}
		}
	}
}
