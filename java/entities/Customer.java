package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import database.FoodOrderService;
import database.FoodService;
import database.LocationService;
import database.OrderService;
import database.PaymentService;

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
				choosing = false;
			}
			else if(choice == 2) {
				choosing = false;
			}
			else if(choice == 3) {
				choosing = false;
			}
			else if(choice == 4) {
				choosing = false;
			}
			else if(choice == 5) {
				choosing = false;
			}
			else if(choice == 6) {
				viewMenu(conn);
				choosing = false;
			}
			else if(choice == 7) {
				choosing = false;
				System.exit(0);
			}
			else {
				System.out.println("Not a valid option");
			}
		}
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
	
	
	private void makeOrder(Connection conn, Scanner input) {
		OrderService orderService = new OrderService(conn);
		FoodOrderService foodOrderService = new FoodOrderService(conn);
		FoodService foodService = new FoodService(conn);
		
		int orderID = orderService.getPK(0);
		ArrayList<Integer> foodIDs = new ArrayList<Integer>();
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		int total = 0;
		
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
		LocalDate orderDate = currentDate();
		LocalDate expectedDate = deliveryDate(orderDate);
		
		
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
	private int makePayment(Connection conn, Scanner input, int amount) {
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
			
		LocalDate datePaid = currentDate();
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
		
		System.out.print("Street Address: ");
		String streetNum = input.next();
		
		System.out.print("Apt/Room # (can leave blank): ");
		String roomNum = input.next();
		
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
		return 1;
	}
	
	
	/*
	 * Get current date object
	 */
	private LocalDate currentDate() {
		LocalDate today = LocalDate.now();
		return today;
	}
	
	
	/*
	 * Get a delivery date within 2 days of orderDate
	 */
	private LocalDate deliveryDate(LocalDate orderDate) {
		LocalDate deliveryDate = orderDate.plusDays(2);
		return deliveryDate;
	}
}
