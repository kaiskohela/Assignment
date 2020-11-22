	package Assignment;
	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.util.*;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	
	public class Knapsack {
	
		// items of our problem
		private Item[] items;
		// capacity of the bag
		private int capacity;
	
		public Knapsack(Item[] items, int capacity) {
			this.items = items;
			this.capacity = capacity;
		}
	
		public void display() {
			if (items != null && items.length > 0) {
				System.out.println("List of items :");
	
				for (Item aItem : items) {
					System.out.println("- " + aItem.getNumber());
				}
			}
		}
	
		// we write the solve algorithm
		public Solution solve() {
			int NB_PACKAGES = items.length;
			// we use a matrix to store the max cost at each n-th package
			int[][] matrix = new int[NB_PACKAGES + 1][capacity + 1];
	
			// first line is initialized to 0
			for (int i = 0; i <= capacity; i++)
				matrix[0][i] = 0;
	
			// we iterate on items
			for (int i = 1; i <= NB_PACKAGES; i++) {
				// we iterate on each capacity
				for (int j = 0; j <= capacity; j++) {
					if (items[i - 1].getWeight() > j)
						matrix[i][j] = matrix[i - 1][j];
					else
						// we maximize cost at this rank in the matrix
						matrix[i][j] = Math.max(matrix[i - 1][j],
								matrix[i - 1][(int) (j - items[i - 1].getWeight())] + items[i - 1].getCost());
				}
			}
	
			int res = matrix[NB_PACKAGES][capacity];
			int w = capacity;
			List<Item> itemsSolution = new ArrayList<>();
	
			for (int i = NB_PACKAGES; i > 0 && res > 0; i--) {
				if (res != matrix[i - 1][(int) w]) {
					itemsSolution.add(items[i - 1]);
					// we remove items cost and weight
					res -= items[i - 1].getCost();
					w -= items[i - 1].getWeight();
				}
			}
	
			return new Solution(itemsSolution, matrix[NB_PACKAGES][capacity]);
		}
	
		public static void main(String[] args) throws Exception {
	
			// read text file from input
			String filePath = readFile();
	
			// creating hash map to store capacity and items of each package
			HashMap<Integer, String> map = new HashMap<Integer, String>();
	
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":", 2);
				if (parts.length >= 2) {
					Integer maxWeight = (Integer.parseInt(parts[0].trim())) * 100;
					String items = parts[1];
					map.put(maxWeight, items);
				}
	
			}
	
			outputFormat();
			// iterate over each line of the input file
			for (Integer maxWeight : map.keySet()) {
				List<String> matchList = new ArrayList<>();
				Pattern regex = Pattern.compile("\\((.*?)\\)");
				Matcher regexMatcher = regex.matcher(map.get(maxWeight));
	
				while (regexMatcher.find()) {// Finds Matching Pattern in String
					matchList.add(regexMatcher.group(1));// Fetching Group from String
				}
	
				List<Item> itemsList = new ArrayList<Item>();
	
				for (String str : matchList) {
					String[] vals = str.split(",");
					String id = (vals[0].trim());
					int weight = (int) (Float.parseFloat(vals[1].trim())) * 100;
					int cost = (int) (Float.parseFloat(vals[2].substring(1, vals[2].length()).trim())) * 100;
					Item item = new Item(id, weight, cost);
					itemsList.add(item);
	
				}
				// Sort itemsList by weight
				Collections.sort(itemsList, Comparator.comparing(Item::getWeight));
				Item[] itemsArray = itemsList.toArray(new Item[0]);
				Knapsack knapsack = new Knapsack(itemsArray, maxWeight);
				knapsack.display();
				Solution solution = knapsack.solve();
				solution.display();
	
			}
			outputFormat();
	
			reader.close();
	
		}
	
		/**
		 *  Read File from input 
		 * @return
		 */
		static String readFile() {
			String filePath = null;
			try {
				Scanner inScanner = new Scanner(System.in);
				inScanner.useDelimiter("\\r\\n");
				System.out.print("Enter input file path and number:");
				filePath = inScanner.nextLine();
				System.out.println("You entered: " + filePath);
	
			} catch (Exception e) {
				System.out.println("Error: File not found.");
			}
			return filePath;
		}
	
		/**
		 * 
		 */
		static void outputFormat() {
			System.out.println("```");
		}
	
	}
