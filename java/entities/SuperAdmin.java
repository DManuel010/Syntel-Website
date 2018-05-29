package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import database.EmployeeService;
import database.LocationService;
import database.LoginService;

public class SuperAdmin extends Admin {
	
	private static int superAdminCount;
	
	
	public SuperAdmin(String email, String firstName, String lastName, int loginID, String phoneNumber,
			int homeAddrID, String lastLogin, int empID, String hireDate, String title, int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin, empID, hireDate, title,
				workAddrID);
		superAdminCount++;
	}


	public static int getSuperAdminCount() {
		return superAdminCount;
	}


	public static void setSuperAdminCount(int superAdminCount) {
		SuperAdmin.superAdminCount = superAdminCount;
	}


	@Override
	public String toString() {
		return "SuperAdmin [toString()=" + super.toString() + "]";
	}


	@Override
	public boolean displayMenu(Scanner input, Connection conn) {
		boolean choosing = true;
				while(choosing) {
					System.out.println("\n---- Super Admin Menu ----\n");
					System.out.println("1)  View Orders");
					System.out.println("2)  Add Orders");
					System.out.println("3)  Edit Orders");
					System.out.println("4)  Complete Orders");
					System.out.println("5)  Delete Orders");
					System.out.println("6)  View Employees");
					System.out.println("7)  Add Employee");
					System.out.println("8)  Remove Employee");
					System.out.println("9)  Edit Employee");
					System.out.println("10) View Customers");
					System.out.println("11) Issue Refund");
					System.out.println("12) Log Out\n");
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
						addEmployees(conn, input);
					}
					else if(choice == 8){
						viewEmployees(conn);
						removeEmployees(conn, input);
					}
					else if(choice == 9){
						viewEmployees(conn);
						editEmployees(conn, input);
					}
					else if(choice == 10){
						viewCustomers(conn);
					}
					else if(choice == 11){
						viewOrders(conn);
						issueRefund(conn, input);
					}
					else if(choice == 12){
						choosing = false;
					}
					else {
						System.out.println("Not a valid option");
					}
				}
				return choosing;
			}
			
	
			public void addEmployees(Connection conn, Scanner input){
				EmployeeService employeeService = new EmployeeService(conn);
				
				System.out.println("Enter the employee's first name: ");
				String firstName = input.next();
				
				System.out.println("Enter the employee's last name: ");
				String lastName = input.next();
				
				System.out.println("Enter the employee's email: ");
				String email = input.next();
				
				System.out.println("Enter the employee's password: ");
				String password = input.next();
				
				System.out.println("Enter the employee's phone number: ");
				String phone = input.next();
				
				System.out.println("Country: ");
				String country = input.next();
				
				System.out.println("State: ");
				String state = input.next();
				
				System.out.println("City: ");
				String city = input.next();
				
				input.nextLine();
				System.out.println("Street number: ");
				String street = input.nextLine();
				
				System.out.println("Zip code: ");
				String zip = input.next();
				
				System.out.println("Enter hire date (MM/DD/YYY): ");
				String hireDate = input.next();
				
				System.out.println("Enter title (no spaces): ");
				String title = input.next();
				
		
				System.out.println("Enter work address ID: ");
				int workAddrID = input.nextInt();
				
				LoginService loginService = new LoginService(conn);
				
				Login login = new Login();
				login.setLoginID(0);
				login.setUsername(email);
				login.setPassword(password);
				login.setType("employee");
				loginService.insert(login);
				
				LocationService locationService = new LocationService(conn);
				
				Location home = new Location(0, country, state, city, street, null, zip);
				locationService.insert(home);
				
				Object employee = new Object();
				
				
				switch(title){
				case "admin": employee = new Admin(email, firstName, lastName, login.getLoginID(), 
						phone, home.getLocationID(), "05/29/2018", 0, hireDate, title, workAddrID);
				case "Admin": employee = new Admin(email, firstName, lastName, login.getLoginID(), 
						phone, home.getLocationID(), "05/29/2018", 0, hireDate, title, workAddrID);
				case "driver": employee = new Driver(email, firstName, lastName, login.getLoginID(), 
						phone, home.getLocationID(), "05/29/2018", 0, hireDate, title, workAddrID);
				case "Driver": employee = new Driver(email, firstName, lastName, login.getLoginID(), 
						phone, home.getLocationID(), "05/29/2018", 0, hireDate, title, workAddrID);
				case "Superadmin": employee = new SuperAdmin(email, firstName, lastName, login.getLoginID(), 
						phone, home.getLocationID(), "05/29/2018", 0, hireDate, title, workAddrID);
				case "superadmin": employee = new SuperAdmin(email, firstName, lastName, login.getLoginID(), 
						phone, home.getLocationID(), "05/29/2018", 0, hireDate, title, workAddrID);
				}
				
				employeeService.insert((Employee) employee);
				
			}
			
			public void removeEmployees(Connection conn, Scanner input){
				EmployeeService employeeService = new EmployeeService(conn);
				LoginService loginService = new LoginService(conn);
				
				System.out.println("Enter employee ID to remove: ");
				int empID = input.nextInt();
				int loginID = employeeService.getLoginID(empID);
				System.out.println(loginID);
				
				employeeService.delete(empID);
				loginService.delete(loginID);
			}
			
			
			public void editEmployees(Connection conn, Scanner input){
				System.out.println("Enter employee ID to edit:");
				int empID = input.nextInt();
		
				System.out.println("\nWhat would you like to edit?\n");
				System.out.println("1)  First Name");
				System.out.println("2)  Last Name");
				System.out.println("3)  Title");
				System.out.println("4)  Email");
				System.out.println("5)  Phone Number");
				System.out.println("6)  Work Address ID");
				System.out.println("7)  Home Address");
				
				int choice = input.nextInt();
				switch(choice){
				
				case 1: editFirstName(empID, conn, input);
				break;
					
				case 2: editLastName(empID, conn, input);
				break;
				
				case 3: editTitle(empID, conn, input);
				break;
				
				case 4: editEmail(empID, conn, input);
				break;
				
				case 5: editPhoneNumber(empID, conn, input);
				break;
				
				case 6: editWorkAddressID(empID, conn, input);
				break;
				
				case 7: editAddress(empID, conn, input);
				break;
				
			}
				
		
		}
			
			public void editFirstName(int empID, Connection conn, Scanner input){
				System.out.println("Enter new first name:");
				String firstName = input.next();
				String query = "update employee set firstname=? where employeeid=?";
				
				try {
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, firstName);
					stmt.setInt(2, empID);
					stmt.execute();
					System.out.println("Edit complete.");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			
			
			public void editLastName(int empID, Connection conn, Scanner input){
				 System.out.println("Enter new last name:");
					String lastName = input.next();
					String query = "update employee set lastname=? where employeeid=?";
					
					try {
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, lastName);
						stmt.setInt(2, empID);
						stmt.execute();
						System.out.println("Edit complete.");
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			public void editAddress(int empID, Connection conn, Scanner input){
				System.out.println("Enter new home address:");
				String address = input.next();
				
			String query = "select homeaddrid from employee where employeeid=?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, empID);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				int currentID=0;
				
				while(rs.next()){
					currentID = rs.getInt(1);
				}
				
				query = "update location set streetnum=? where locationid=?";
				stmt = conn.prepareStatement(query);
				stmt.setString(1, address);
				stmt.setInt(2, currentID);
				stmt.execute();
				
				System.out.println("Edit complete.");
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			
			public void editWorkAddressID(int empID, Connection conn, Scanner input){
				System.out.println("Enter new work address ID:");
				int work = input.nextInt();
				String query = "update employee set workaddrid=? where employeeid=?";
				
				try {
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setInt(1, work);
					stmt.setInt(2, empID);
					stmt.execute();
					System.out.println("Edit complete.");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void editPhoneNumber(int empID, Connection conn, Scanner input){
				System.out.println("Enter new phone number:");
				String phone = input.next();
				String query = "update employee set phonenumber=? where employeeid=?";
				
				try {
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, phone);
					stmt.setInt(2, empID);
					stmt.execute();
					System.out.println("Edit complete.");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void editEmail(int empID, Connection conn, Scanner input){
				System.out.println("Enter new email:");
				String email = input.next();
				String query = "update employee set email=? where employeeid=?";
				
				try {
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, email);
					stmt.setInt(2, empID);
					stmt.execute();
					System.out.println("Edit complete.");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void editTitle(int empID, Connection conn, Scanner input){
				System.out.println("Enter new title:");
				String title = input.next();
				String query = "update employee set title=? where employeeid=?";
				
				try {
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, title);
					stmt.setInt(2, empID);
					stmt.execute();
					System.out.println("Edit complete.");
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 	}
		 }
