package entities;

public class ComboFood {
	
	private int comboFoodID;
	private int foodID;
	private int comboID;
	
	private static int comboFoodCount;
	
	public ComboFood() {
		super();
		comboFoodCount++;
	}
	
	public ComboFood(int comboFoodID, int foodID, int comboID) {
		super();
		this.comboFoodID = comboFoodID;
		this.foodID = foodID;
		this.comboID = comboID;
		comboFoodCount++;
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

	public static int getComboFoodCount() {
		return comboFoodCount;
	}

	public static void setComboFoodCount(int comboFoodCount) {
		ComboFood.comboFoodCount = comboFoodCount;
	}

	@Override
	public String toString() {
		return "ComboFood [comboFoodID=" + comboFoodID + ", foodID=" + foodID + ", comboID=" + comboID + "]";
	}

}
