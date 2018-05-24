package entities;

import java.time.LocalDate;

public class Card {
	private int cardID;
	private String name;
	private String number;
	private LocalDate expirationDate;
	private int cvv;
	private String type;
	private Location billingAddr;
	
	private static int cardCount;

	public Card(int cardID, String name, String number, LocalDate expirationDate, int cvv, String type, Location billingAddr) {
		super();
		this.cardID = cardID;
		this.name = name;
		this.number = number;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
		this.type = type;
		this.billingAddr = billingAddr;
		cardCount++;
	}

	
	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getcvv() {
		return cvv;
	}

	public void setcvv(int cvv) {
		this.cvv = cvv;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Location getBillingAddr() {
		return billingAddr;
	}

	public void setBillingAddr(Location billingAddr) {
		this.billingAddr = billingAddr;
	}

	public static int getCardCount() {
		return cardCount;
	}

	public static void setCardCount(int cardCount) {
		Card.cardCount = cardCount;
	}

	@Override
	public String toString() {
		return "Card [cardID=" + cardID + ", name=" + name + ", number=" + number + ", expirationDate=" + expirationDate
				+ ", cvv=" + cvv + ", type=" + type + ", billingAddr=" + billingAddr + "]";
	}
}
