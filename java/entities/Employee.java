package entities;

import java.util.Date;

public abstract class Employee extends User {

	private int empID;
	private Date hireDate;
	private String title;
	private int workAddrID;
	private static int employeeCount;
	
	public Employee(String email, String firstName, String lastName, 
					int loginID, String phoneNumber, int homeAddrID, Date lastLogin, 
					int empID, Date hireDate, String title,int workAddrID) {
		super(email, firstName, lastName, loginID, phoneNumber, homeAddrID, lastLogin);
		this.empID = empID;
		this.hireDate = hireDate;
		this.title = title;
		this.workAddrID = workAddrID;
		employeeCount++;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
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
