package entities;

import java.time.LocalDate;

public class Payment {
	private int paymentID;
	private String type;
	private float amount;
	private LocalDate datePaid;
	
	private static int paymentCount;

	
	public Payment(int paymentID, String type, float amount, LocalDate datePaid) {
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public LocalDate getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(LocalDate date) {
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
