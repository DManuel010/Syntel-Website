package entities;

public class Combo {
	
	private int comboID;
	private String comboName;
	private double comboPrice;
	private String description;
	
	private static int comboCount;
	
	public Combo(int comboID, String comboName, double comboPrice, String description) {
		super();
		this.comboID = comboID;
		this.comboName = comboName;
		this.comboPrice = comboPrice;
		this.description = description;
		comboCount++;
	}
	
	public Combo() {
		super();
		comboCount++;
	}
	
	public int getComboID() {
		return comboID;
	}
	
	public void setComboID(int comboID) {
		this.comboID = comboID;
	}
	
	public String getComboName() {
		return comboName;
	}
	
	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	
	public double getComboPrice() {
		return comboPrice;
	}
	
	public void setComboPrice(double comboPrice) {
		this.comboPrice = comboPrice;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static int getComboCount() {
		return comboCount;
	}

	public static void setComboCount(int comboCount) {
		Combo.comboCount = comboCount;
	}

	@Override
	public String toString() {
		return "Combos [comboID=" + comboID + ", comboName=" + comboName + ", comboPrice=" + comboPrice
				+ ", description=" + description + "]";
	}
	
}
