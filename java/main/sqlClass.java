package caseStudy;

import java.sql.SQLException;
import exceptions.MyException;

public class sqlClass {

	
	public static void main(String[] args) throws SQLException, MyException {
		// TODO Auto-generated method stub
	
		Employee em = new Employee();
		orderServices os = new orderServices();
		
		os.completeOrder();
		os.showOrders();
		os.changeOrder();
	
	}
	

	
	


	/*private static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	*/

}
