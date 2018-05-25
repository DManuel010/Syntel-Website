package entities;

public class FoodOrder {

	private int foodOrderID;
	private int orderID;
	private int foodID;
	private int quantity;
	
	private static int foodOrderCount;
	
	
	public FoodOrder(int foodOrderID, int orderID, int foodID, int quantity) {
		super();
		this.foodOrderID = foodOrderID;
		this.orderID = orderID;
		this.foodID = foodID;
		this.quantity = quantity;
	}

	public FoodOrder() {
		foodOrderCount++;
	}

	public int getFoodOrderID() {
		return foodOrderID;
	}

	public void setFoodOrderID(int foodOrderID) {
		this.foodOrderID = foodOrderID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static int getFoodOrderCount() {
		return foodOrderCount;
	}

	public static void setFoodOrderCount(int foodOrderCount) {
		FoodOrder.foodOrderCount = foodOrderCount;
	}

	@Override
	public String toString() {
		return "FoodOrder [foodOrderID=" + foodOrderID + ", orderID=" + orderID + ", foodID=" + foodID + ", quantity="
				+ quantity + "]";
	}

}
