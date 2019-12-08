import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeverageRatioAnalysis {
	/**
	 * link class and methods
	 */
	DataReader dr = new DataReader("leverageratio.csv");
	ArrayList<String> stockList = dr.getStockList();
	HashMap<String, ArrayList<Double>> Data = dr.getData();
	CalculationMethods cm = new CalculationMethods();

	/**
	 * set up variables for calculating and sorting average leverage ratio, and get relevant stock list
	 * and set up HashMap with stock list and leverage ratio and monthly returns 
	 */
	ArrayList<Double> averageLeverageRatio = new ArrayList<Double>();
	ArrayList<Integer> filteredStockID = new ArrayList<Integer>();
	ArrayList<Double> chosenAverageLeverageRatio = new ArrayList<Double>();
	double[] sortedAverageLeverageRatio;
	ArrayList<Integer> stockLocation = new ArrayList<Integer>();
	ArrayList<String> stockSymbol = new ArrayList<String>();
	HashMap<String, Double> chosenStockWithLeverageRatio = new HashMap<>();
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
	 * calculate average leverage ratio, sort it from lowest to highest, and set HashMap
	 */
	public LeverageRatioAnalysis() {

		for(int i = 0; i < stockList.size(); i++) {
			ArrayList<Double> retData = new ArrayList<Double>();

			for(int j = 0; j < Data.get(stockList.get(i)).size(); j++) {
				retData.add(Data.get(stockList.get(i)).get(j));
			}

			/**
			 * calculate average leverage ratio of each stock and S&P 500 Index
			 */
			double temp = 1.0000;
			temp = cm.calculateAverage(retData);
			temp = round(temp, 4);
			averageLeverageRatio.add(temp);
		}

		/**
		 * filter ID of stocks that has lower leverage ratio than S&P 500 Index
		 */
		for(int i =  0; i < averageLeverageRatio.size() - 1; i++) {
			if(averageLeverageRatio.get(i) < averageLeverageRatio.get(averageLeverageRatio.size() - 1)) {
				filteredStockID.add(i);
			}
		}

		/**
		 * get and output the chosen average leverage ratio
		 */
		for(int i = 0; i < filteredStockID.size(); i++) {
			chosenAverageLeverageRatio.add(averageLeverageRatio.get(filteredStockID.get(i)));
		}

		/**
		 * change the format of chosen annual SR from double Array List to doubles
		 * and at the same time adjust the size to select the bottom 20 stocks by leverage ratio
		 */
		if(chosenAverageLeverageRatio.size() > 20) {
			sortedAverageLeverageRatio = new double[20];
			for(int i = 0; i < sortedAverageLeverageRatio.length; i++) {
				sortedAverageLeverageRatio[i] = chosenAverageLeverageRatio.get(i);
			}
		}
		else if(chosenAverageLeverageRatio.size() <= 20) {
			sortedAverageLeverageRatio = new double[chosenAverageLeverageRatio.size()];
			for(int i = 0; i < chosenAverageLeverageRatio.size(); i++) {
				sortedAverageLeverageRatio[i] = chosenAverageLeverageRatio.get(i);
			}
		}
		Arrays.sort(sortedAverageLeverageRatio);

		/**
		 * get chosen stocks' location in initial stock list
		 */
		for(int i = 0; i < sortedAverageLeverageRatio.length; i++) {
			for(int j = 0; j < averageLeverageRatio.size(); j++) {
				if(sortedAverageLeverageRatio[i] == averageLeverageRatio.get(j)) {
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
		 * set up HashMap for chosen stock with average leverage ratio data
		 */
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithLeverageRatio.put(stockSymbol.get(i), sortedAverageLeverageRatio[i]);
		}

		/**
		 * set up HashMap for chosen stock with monthly returns data
		 */
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithMonthlyReturns.put(stockSymbol.get(i), Data.get(stockSymbol.get(i)));
		}
	}

	public static void main(String[] args) {

		LeverageRatioAnalysis test = new LeverageRatioAnalysis();

		/**
		 * print selected stocks symbols list, the list has lowest leverage ratio to highest leverage ratio
		 */
		System.out.println("The top 20 stocks with lower leverage ratio than the benchmark, S&P 500 Index, are shown as follows:");
		System.out.println(test.getStockSymbol());
		System.out.println();

		/**
		 * print out the sorted average leverage ratio
		 */
		System.out.println("Their leverage raio are shown as follows:");
		test.cm.printArray(test.getSortedAverageLeverageRatio());

	}

	public ArrayList<Double> getAverageLeverageRatio() {
		return averageLeverageRatio;
	}

	public ArrayList<Integer> getFilteredStockID() {
		return filteredStockID;
	}

	public ArrayList<Double> getChosenAverageLeverageRatio() {
		return chosenAverageLeverageRatio;
	}

	public double[] getSortedAverageLeverageRatio() {
		return sortedAverageLeverageRatio;
	}

	public ArrayList<Integer> getStockLocation() {
		return stockLocation;
	}

	public ArrayList<String> getStockSymbol() {
		return stockSymbol;
	}

	public HashMap<String, Double> getChosenStockWithLeverageRatio() {
		return chosenStockWithLeverageRatio;
	}

	public HashMap<String, ArrayList<Double>> getChosenStockWithMonthlyReturns() {
		return chosenStockWithMonthlyReturns;
	}
}
