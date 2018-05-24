package entities;

public class Food {

	private int foodID;
	private String name;
	private String group;
	private double price;
	private String description;
	private int stock;
	
	private static int foodCount;
	
	public Food(int foodID, String name, String group, double price, String description, int stock) {
		super();
		this.foodID = foodID;
		this.name = name;
		this.group = group;
		this.price = price;
		this.description = description;
		this.stock = stock;
		foodCount++;
	}
	
	public Food() {
		super();
		foodCount++;
	}
	
	public int getFoodID() {
		return foodID;
	}
	
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public static int getFoodCount() {
		return foodCount;
	}

	public static void setFoodCount(int foodCount) {
		Food.foodCount = foodCount;
	}

	@Override
	public String toString() {
		return "Food [foodID=" + foodID + ", name=" + name + ", group=" + group + ", price=" + price + ", description="
				+ description + ", stock=" + stock + "]";
	}
	
}
