package entities;

public class ComboFood {
	
	private int comboFoodID;
	private int foodID;
	private int comboID;
	
	public ComboFood() {
		super();
	}
	
	public ComboFood(int comboFoodID, int foodID, int comboID) {
		super();
		this.comboFoodID = comboFoodID;
		this.foodID = foodID;
		this.comboID = comboID;
	}
	
	
	public int getComboFoodID() {
		return comboFoodID;
	}


	public void setComboFoodID(int comboFoodID) {
		this.comboFoodID = comboFoodID;
	}


	

	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public int getComboID() {
		return comboID;
	}
	public void setComboID(int comboID) {
		this.comboID = comboID;
	}

	@Override
	public String toString() {
		return "ComboFood [comboFoodID=" + comboFoodID + ", foodID=" + foodID + ", comboID=" + comboID + "]";
	}
	
	

}
