package entities;


public class Payment {
	private int paymentID;
	private String type;
	private double amount;
	private String datePaid;
	
	private static int paymentCount;

	
	public Payment(int paymentID, String type, double amount, String datePaid) {
		super();
		this.paymentID = paymentID;
		this.type = type;
		this.amount = amount;
		this.datePaid = datePaid;
		paymentCount++;
	}
	
	public Payment() {
		super();
		paymentCount++;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(String date) {
		this.datePaid = date;
	}

	public static int getPaymentCount() {
		return paymentCount;
	}

	public static void setPaymentCount(int paymentCount) {
		Payment.paymentCount = paymentCount;
	}

	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentID + ", type=" + type + ", amount=" + amount + ", date=" + datePaid + "]";
	}
}
