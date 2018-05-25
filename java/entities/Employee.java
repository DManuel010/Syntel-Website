package entities;


public abstract class Employee extends User {

	private int empID;
	private String hireDate;
	private String title;
	private int workAddrID;
	
	private static int employeeCount;
	
	public Employee(String email, String firstName, String lastName, 
					int loginID, String phoneNumber, int homeAddrID, String lastLogin, 
					int empID, String hireDate, String title,int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin);
		this.empID = empID;
		this.hireDate = hireDate;
		this.title = title;
		this.workAddrID = workAddrID;
		employeeCount++;
	}
	
	public Employee(String firstName, String lastName, String email, int loginID, String phoneNumber, int homeAddrID,
			String lastLogin) {
		super(firstName, lastName, email, loginID, phoneNumber, homeAddrID, lastLogin);
		employeeCount++;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWorkAddrID() {
		return workAddrID;
	}

	public void setWorkAddrID(int workAddrID) {
		this.workAddrID = workAddrID;
	}

	public static int getEmployeeCount() {
		return employeeCount;
	}

	public static void setEmployeeCount(int employeeCount) {
		Employee.employeeCount = employeeCount;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", hireDate=" + hireDate + ", title=" + title + ", workAddrID=" + workAddrID
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
