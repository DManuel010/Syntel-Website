package entities;

import java.util.Date;

public class Card 
{
	int cardID;
	String name;
	int number;
	Date expirationDate;
	int ccv;
	String type;
	Location billingAddr;
	
	static int cardCount;

	/**
	 * @param cardID
	 * @param name
	 * @param number
	 * @param expirationDate
	 * @param ccv
	 * @param type
	 * @param billingAddr
	 */
	public Card(int cardID, String name, int number, Date expirationDate, int ccv, String type, Location billingAddr)
	{
		super();
		this.cardID = cardID;
		this.name = name;
		this.number = number;
		this.expirationDate = expirationDate;
		this.ccv = ccv;
		this.type = type;
		this.billingAddr = billingAddr;
		cardCount++;
	}

	public Card() 
	{
		super();
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
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
				+ ", ccv=" + ccv + ", type=" + type + ", billingAddr=" + billingAddr + "]";
	}
}
