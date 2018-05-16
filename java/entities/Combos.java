package caseStudy;

public class Combos {

	
	
	int comboID;
	String comboName;
	double comboPrice;
	String description;
	public Combos(int comboID, String comboName, double comboPrice, String description) {
		super();
		this.comboID = comboID;
		this.comboName = comboName;
		this.comboPrice = comboPrice;
		this.description = description;
	}
	public Combos() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "Combos [comboID=" + comboID + ", comboName=" + comboName + ", comboPrice=" + comboPrice
				+ ", description=" + description + "]";
	}
	
}
