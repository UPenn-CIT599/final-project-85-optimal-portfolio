import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ROAAnalysis {
	/**
	 * link class and methods
	 */
	DataReader dr = new DataReader("roa.csv");
	ArrayList<String> stockList = dr.getStockList();
	HashMap<String, ArrayList<Double>> Data = dr.getData();
	CalculationMethods cm = new CalculationMethods();

	/**
	 * set up variables for calculating and sorting average roa, and get relevant stock list
	 * and set up HashMap with stock list and leverage ratio and monthly returns 
	 */
	ArrayList<Double> averageROA = new ArrayList<Double>();
	ArrayList<Integer> filteredStockID = new ArrayList<Integer>();
	ArrayList<Double> chosenAverageROA = new ArrayList<Double>();
	double[] sortedAverageROA;
	ArrayList<Integer> stockLocation = new ArrayList<Integer>();
	ArrayList<String> stockSymbol = new ArrayList<String>();
	HashMap<String, Double> chosenStockWithROA = new HashMap<>();
	HashMap<String, ArrayList<Double>> chosenStockWithMonthlyReturns = new HashMap<>();

	/**
	 * this method is used to round double value to places decimal number
	 * @param value
	 * @param places
	 * @return
	 */
	private static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * calculate average roa, sort it from highest to highest, and set HashMap
	 */
	public ROAAnalysis() {

		for(int i = 0; i < stockList.size(); i++) {
			ArrayList<Double> retData = new ArrayList<Double>();

			for(int j = 0; j < Data.get(stockList.get(i)).size(); j++) {
				retData.add(Data.get(stockList.get(i)).get(j));
			}

			/**
			 * calculate average ROA of each stock and S&P 500 Index
			 */
			double temp = 1.0000;
			temp = cm.calculateAverage(retData);
			temp = round(temp, 4);
			averageROA.add(temp);
		}

		/**
		 * filter ID of stocks that has higher average ROA than S&P 500 Index
		 */
		for(int i =  0; i < averageROA.size() - 1; i++) {
			if(averageROA.get(i) > averageROA.get(averageROA.size() - 1)) {
				filteredStockID.add(i);
			}
		}

		/**
		 * get and output the chosen average ROA
		 */
		for(int i = 0; i < filteredStockID.size(); i++) {
			chosenAverageROA.add(averageROA.get(filteredStockID.get(i)));
		}

		/**
		 * change the format of chosen average ROA from double Array List to doubles
		 * and at the same time adjust the size to select the top 20 stocks by roa
		 */
		if(chosenAverageROA.size() > 20) {
			sortedAverageROA = new double[20];
			for(int i = 0; i < sortedAverageROA.length; i++) {
				sortedAverageROA[i] = chosenAverageROA.get(i);
			}
		}
		else if(chosenAverageROA.size() <= 20) {
			sortedAverageROA = new double[chosenAverageROA.size()];
			for(int i = 0; i < sortedAverageROA.length; i++) {
				sortedAverageROA[i] = chosenAverageROA.get(i);
			}
		}
		Arrays.sort(sortedAverageROA);
		cm.newSort(sortedAverageROA);

		/**
		 * get chosen stocks' location in initial stock list
		 */
		for(int i = 0; i < sortedAverageROA.length; i++) {
			for(int j = 0; j < averageROA.size(); j++) {
				if(sortedAverageROA[i] == averageROA.get(j)) {
					stockLocation.add(j);
				}
			}
		}

		/**
		 * get chosen stocks' symbols
		 */
		for(int i = 0; i < stockLocation.size(); i++) {
			stockSymbol.add(stockList.get(stockLocation.get(i)));
		}

		/**
		 * set up HashMap for chosen stock with roa data
		 */
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithROA.put(stockSymbol.get(i), sortedAverageROA[i]);
		}

		/**
		 * set up HashMap for chosen stock with monthly returns data
		 */
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithMonthlyReturns.put(stockSymbol.get(i), Data.get(stockSymbol.get(i)));
		}
	}

	public static void main(String[] args) {

		ROAAnalysis test = new ROAAnalysis();

		/**
		 * print selected stocks symbols list, the list has highest ROA to lowest ROA
		 */
		System.out.println("The top 20 stocks with higher ROA (Return on Assets) than the benchmark, S&P 500 Index, are shown as follows:");
		System.out.println(test.getStockSymbol());
		System.out.println();

		/**
		 * print out the sorted average ROA
		 */
		System.out.println("Their ROA are shown as follows:");
		test.cm.printArray(test.getSortedAverageROA());

	}

	public ArrayList<Double> getAverageROA() {
		return averageROA;
	}

	public ArrayList<Integer> getFilteredStockID() {
		return filteredStockID;
	}

	public ArrayList<Double> getChosenAverageROA() {
		return chosenAverageROA;
	}

	public double[] getSortedAverageROA() {
		return sortedAverageROA;
	}

	public ArrayList<Integer> getStockLocation() {
		return stockLocation;
	}

	public ArrayList<String> getStockSymbol() {
		return stockSymbol;
	}

	public HashMap<String, Double> getChosenStockWithROA() {
		return chosenStockWithROA;
	}

	public HashMap<String, ArrayList<Double>> getChosenStockWithMonthlyReturns() {
		return chosenStockWithMonthlyReturns;
	}
}
