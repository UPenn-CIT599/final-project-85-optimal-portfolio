import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ROAAnalysis {
	DataReader sr = new DataReader("roa.csv");
	ArrayList<String> stockList = sr.getStockList();
	// System.out.println(stockList);
	HashMap<String, ArrayList<Double>> Data = sr.getData();

	CalculationMethods cm = new CalculationMethods();

	// set up array list of average ROA
	ArrayList<Double> averageROA = new ArrayList<Double>();
	ArrayList<Integer> filteredStockID = new ArrayList<Integer>();
	ArrayList<Double> chosenAverageROA = new ArrayList<Double>();
	double[] sortedAverageROA;
	// this is the final chosen top stocks' average ROA
	double[] newSortedAverageROA;
	// this is the final chosen top stocks' location in initial stock list
	ArrayList<Integer> stockLocation = new ArrayList<Integer>();
	// this is the final chosen top stocks' symbol
	ArrayList<String> stockSymbol = new ArrayList<String>();
	// set up HashMap for chosen stocks symbol with their average ROA
	HashMap<String, Double> chosenStockWithROA = new HashMap<>();
	// set up HashMap for chosen stocks symbol with their monthly returns, and this is used for later matrix calculation
	HashMap<String, ArrayList<Double>> chosenStockWithMonthlyReturns = new HashMap<>();

	// this method is used to round double value to places decimal number
	private static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	// set up ArrayList and HashMap for future files usage
	public ROAAnalysis() {

		for(int i = 0; i < stockList.size(); i++) {
			ArrayList<Double> retData = new ArrayList<Double>();

			for(int j = 0; j < Data.get(stockList.get(i)).size(); j++) {
				retData.add(Data.get(stockList.get(i)).get(j));
			}

			// I. calculate annual return of each stock, S&P 500 Index, and risk-free bond
			double temp = 1.0000;
			temp = cm.calculateAverage(retData);
			temp = round(temp, 4);
			averageROA.add(temp);
		}

		// filter ID of stocks that has higher annual Sharpe ratio than S&P 500 Index
		for(int i =  0; i < averageROA.size() - 1; i++) {
			if(averageROA.get(i) > averageROA.get(averageROA.size() - 1)) {
				filteredStockID.add(i);
			}
		}

		// get and output the chosen annual SR
		for(int i = 0; i < filteredStockID.size(); i++) {
			chosenAverageROA.add(averageROA.get(filteredStockID.get(i)));
		}

		// change the format of chosen annual SR from double Array List to doubles
		sortedAverageROA = new double[chosenAverageROA.size()];
		for(int i = 0; i < chosenAverageROA.size(); i++) {
			sortedAverageROA[i] = chosenAverageROA.get(i);
		}
		Arrays.sort(sortedAverageROA);
		newSortedAverageROA = cm.newSort(sortedAverageROA);

		// get chosen stocks' location in initial stock list
		for(int i = 0; i < newSortedAverageROA.length; i++) {
			for(int j = 0; j < averageROA.size(); j++) {
				if(newSortedAverageROA[i] == averageROA.get(j)) {
					stockLocation.add(j);
				}
			}
		}
		
		// get chosen stocks' symbols
		for(int i = 0; i < stockLocation.size(); i++) {
			stockSymbol.add(stockList.get(stockLocation.get(i)));
		}

		// set up HashMap for chosen stock with Sharpe ratio data
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithROA.put(stockSymbol.get(i), newSortedAverageROA[i]);
		}

		// set up HashMap for chosen stock with monthly returns data
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithMonthlyReturns.put(stockSymbol.get(i), Data.get(stockSymbol.get(i)));
		}
	}

	public static void main(String[] args) {

		// System.out.println(stockList);
		// System.out.println(Data);
		
		ROAAnalysis test = new ROAAnalysis();
		
		// print out results for verification
		System.out.println(test.getAverageROA());
		System.out.println(test.getAverageROA().size());

		// print out ID of stocks that has higher average ROA than S&P 500 Index
		System.out.println(test.getFilteredStockID());
		System.out.println(test.getFilteredStockID().size());

		System.out.println(test.getChosenAverageROA());
		System.out.println(test.getChosenAverageROA().size());

		// print out the sorted average ROA
		test.cm.printArray(test.getSortedAverageROA());
		System.out.println(test.getSortedAverageROA().length);

		test.cm.printArray(test.getNewSortedAverageROA());
		System.out.println(test.getNewSortedAverageROA().length);

		System.out.println(test.getStockLocation());
		System.out.println(test.getStockLocation().size());

		// print selected stocks symbols list, the list has highest SR to lowest SR
		System.out.println(test.getStockSymbol());
		System.out.println(test.getStockSymbol().size());

		// print the selected stock HashMap with SR
		System.out.println(test.getChosenStockWithROA());
		System.out.println(test.getChosenStockWithROA().size());
		System.out.println(test.getChosenStockWithROA().get("FISV US Equity"));

		// print the selected stock HashMap with returns data
		System.out.println(test.getChosenStockWithMonthlyReturns());
		System.out.println(test.getChosenStockWithMonthlyReturns().size());

		// for (String s : test.chosenStockWithSR.keySet()) {
		// System.out.print(s);
		// System.out.print(" ");
		// System.out.print(test.chosenStockWithSR.get(s));
		// System.out.println();
		// }

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

	public double[] getNewSortedAverageROA() {
		return newSortedAverageROA;
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
