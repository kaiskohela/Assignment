package Assignment;

public class Item{
	
	// Attributes
	private String number;
	private Integer weight;
	private Integer cost;
	// Constructor of Item
	public Item(String number,  int weight, int cost) {
		this.setNumber(number);
		this.setWeight(weight);
		this.setCost(cost);

	}

	
	/**
	 * @return the number
	 */
	String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the weight
	 */
	Integer getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * @return the cost
	 */
	Integer getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	void setCost(Integer cost) {
		this.cost = cost;
	}

	

}
