package Assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	
	// list of items 
	public List<Item> items;
	// maximal cost possible
	public float value;
	
	public Solution(List<Item> items, float value) {
		this.items = items;
		this.value = value;
	}
	
	// display the items to pick in the package to the console if applicable
	public void display() {
		// if exists items to pick in the package
		if (items != null  &&  !items.isEmpty()){
			System.out.println("Items to pick in the associated package:");

			List<String> strings = new ArrayList<>();


			for (Item item : items) {
				strings.add(item.getNumber());
			}
			String arrStrings = Arrays.toString(strings.toArray());

			System.out.println(arrStrings.substring(1,arrStrings.length()-1));


		}
		// if there is no items to pick into the package we display a simple message
		else {
			System.out.println("No Items to pick in this package");
			System.out.println("- ");
		}


	}

}
