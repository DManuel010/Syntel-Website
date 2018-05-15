package entities;

import java.util.Date;

public class Payment {
	private int paymentID;
	private float amount;
	private Date date;
	
	private static int paymentCount;

	
	public Payment(int paymentID, float amount, Date date) {
		super();
		this.paymentID = paymentID;
		this.amount = amount;
		this.date = date;
		paymentCount++;
	}

	public Payment() {
		super();
		paymentCount++;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static int getPaymentCount() {
		return paymentCount;
	}

	public static void setPaymentCount(int paymentCount) {
		Payment.paymentCount = paymentCount;
	}

	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentID + ", amount=" + amount + ", date=" + date + "]";
	}
}
