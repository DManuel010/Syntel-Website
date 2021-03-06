package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import database.FoodOrderService;
import database.FoodService;
import database.LocationService;
import database.OrderService;
import database.PaymentService;

public class Customer extends User {

	private int customerID;
	private String dateOfBirth;
	private int cardID;
	private String dateOfRegister;
	
	private static int customerCount;
	
	public Customer(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, String lastLogin, int customerID, String dateOfBirth, int cardID, String dateOfRegister) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin);
		this.customerID = customerID;
		this.dateOfBirth = dateOfBirth;
		this.cardID = cardID;
		this.dateOfRegister = dateOfRegister;
		customerCount++;
	}

	public Customer(String firstName, String lastName, String email, int loginID, String phoneNumber, int homeAddrID,
			String lastLogin) {
		super(firstName, lastName, email, loginID, phoneNumber, homeAddrID, lastLogin);
		customerCount++;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public String getDateOfRegister() {
		return dateOfRegister;
	}

	public void setDateOfRegister(String dateOfRegister) {
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
	public boolean displayMenu(Scanner input, Connection conn) {
		boolean choosing = true;
		while(choosing) {
			System.out.println("\n---- Customer Menu ----\n");
			System.out.println("1)  Make Order");
			System.out.println("2)  Cancel Order");
			System.out.println("3)  View Orders");
			System.out.println("4)  Print Order");
			System.out.println("5)  View Menu");
			System.out.println("6)  Log Out\n");
		
			System.out.print("\nChoose option: ");
			int choice = input.nextInt();
			
			if(choice == 1) {
				viewMenu(conn);
				makeOrder(conn, input);
			}
			else if(choice == 2) {
				viewOrders(conn);
				cancelOrder(conn, input);
			}
			else if(choice == 3) {
				viewOrders(conn);
			}
			else if(choice == 4) {
				viewOrders(conn);
				printOrder(conn, input);
			}
			else if(choice == 5) {
				viewMenu(conn);
			}
			else if(choice == 6) {
				choosing = false;
			}
			else {
				System.out.println("Not a valid option");
			}
		}
		return choosing;
	}
	
	
	private void viewMenu(Connection conn) {
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
	
	
	private void viewOrders(Connection conn) {
		OrderService orderService = new OrderService(conn);
		orderService.viewCustomerOrders(this.customerID);
	}
	
	private void cancelOrder(Connection conn, Scanner input) {
		OrderService orderService = new OrderService(conn);
		PaymentService paymentService = new PaymentService(conn);
		FoodOrderService foodOrderService = new FoodOrderService(conn);
		
		System.out.print("Select the ID of order to cancel: ");
		int orderID = input.nextInt();
		int paymentID = orderService.getPaymentID(orderID);
		foodOrderService.deleteOrder(orderID);
		orderService.delete(orderID);
		paymentService.delete(paymentID);
	}
	
	private void printOrder(Connection conn, Scanner input) {
		OrderService orderService = new OrderService(conn);
		System.out.println("Select the ID of order to print: ");
		int orderID = input.nextInt();
		orderService.print(orderID);
	}
	
	private void makeOrder(Connection conn, Scanner input) {
		OrderService orderService = new OrderService(conn);
		FoodOrderService foodOrderService = new FoodOrderService(conn);
		FoodService foodService = new FoodService(conn);
		
		int orderID = orderService.getPK(0);
		ArrayList<Integer> foodIDs = new ArrayList<Integer>();
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		double total = 0;
		
		boolean shopping = true;
		while(shopping) {
			System.out.print("Enter food item ID: ");
			int foodID = input.nextInt();
			
			System.out.print("Quantity: ");
			int quantity = input.nextInt();
			
			double price = foodService.priceCheck(foodID);
			total += price * quantity;
			
			// add to arrays
			foodIDs.add(foodID);
			quantities.add(quantity);
			
			System.out.print("Complete Order (Y/N): ");
			String completeOrder = input.next().toUpperCase();
			
			if(!completeOrder.equals("Y")) {
				if(!completeOrder.equals("N")) {
					System.out.println("Not a valid input, please input Y or N");
				}
			}
			else {
				shopping = false;
			}
		}
			
		int paymentID = makePayment(conn, input, total);
		int deliveryAddrID = getDeliveryAddress(conn, input);
		int employeeID = randomEmployee(conn);
		String orderDate = currentDate();
		String expectedDate = expectedDate();
		
		
		Order order = new Order(orderID, employeeID, this.customerID, 
								total, paymentID, deliveryAddrID, 
								orderDate, expectedDate, null, null);
		
		orderService.insert(order);
		
		// populate FoodOrder table
		for(int i = 0; i < foodIDs.size(); i++) {
			int foodID = foodIDs.get(i);
			int quantity = quantities.get(i);
			
			FoodOrder foodOrder = new FoodOrder(0, orderID, foodID, quantity);
			foodOrderService.insert(foodOrder);
		}
	}
	
	
	/*
	 * Make a payment for the current working order
	 */
	private int makePayment(Connection conn, Scanner input, double amount) {
		PaymentService paymentService = new PaymentService(conn);
		int paymentID = paymentService.getPK(0);
		String paymentType = "";
		
		boolean stillPaying = true;
		while(stillPaying) {
			System.out.println("Choose a pyament method:");
			System.out.println("1)  Cash");
			System.out.println("2)  Credit");
			int paymentChoice = input.nextInt();
			
			if(paymentChoice == 1) {
				paymentType = "Cash";
				stillPaying = false;
			}
			else if(paymentChoice == 2) {
				paymentType = "Credit";
				stillPaying = false;
			}
			else {
				System.out.println("Not a valid payment option.");
			}
		}
			
		String datePaid = currentDate();
		Payment payment = new Payment(paymentID, paymentType, amount, datePaid);
			
		paymentService.insert(payment);
		System.out.println("Payment completed");
			
		return paymentID;
	}
	
	
	/*
	 * Get's the user's delivery address and inserts into database
	 * returns the locationID of that new address
	 */
	private int getDeliveryAddress(Connection conn, Scanner input) {
		LocationService locationService = new LocationService(conn);
		int locationID = locationService.getPK(0);
		
		System.out.print("Country: ");
		String country = input.next();
		
		System.out.print("State: ");
		String state = input.next();
		
		System.out.print("City: ");
		String city = input.next();
		
		input.nextLine();
		System.out.print("Street Address: ");
		String streetNum = input.nextLine();
		
		System.out.print("Apt/Room # (or 'n/a'): ");
		String roomNum = input.next();
		
		if(roomNum.equals("n/a")) {
			roomNum = null;
		}
		
		System.out.print("Zip: ");
		String zip = input.next();
		
		Location location = new Location(locationID, country, state, city, streetNum, roomNum, zip);
		locationService.insert(location);
		
		return locationID;
	}
	
	
	/*
	 * TODO: Query database for an employeeID to return to fulfill order
	 */
	private int randomEmployee(Connection conn) {
		return 3;
	}
	
	
	/*
	 * Get current date object
	 */
	private String currentDate() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String today = localDate.format(formatter);
		return today;
	}
	
	
	/*
	 * Get a delivery date within 2 days of orderDate
	 */
	private String expectedDate() {
		LocalDate localDate = LocalDate.now().plusDays(2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String deliveryDate = localDate.format(formatter);
		return deliveryDate;
	}
}
