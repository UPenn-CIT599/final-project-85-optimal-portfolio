import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SharpeRatioAnalysis {

	DataReader sr = new DataReader("return.csv");
	ArrayList<String> stockList = sr.getStockList();
	// System.out.println(stockList);
	HashMap<String, ArrayList<Double>> Data = sr.getData();

	CalculationMethods cm = new CalculationMethods();

	// set up array list of annual return, annual standard deviation, and annual Sharpe ratio
	ArrayList<Double> annualRet = new ArrayList<Double>();
	ArrayList<Double> annualStd = new ArrayList<Double>();
	ArrayList<Double> annualSR = new ArrayList<Double>();
	ArrayList<Integer> filteredStockID = new ArrayList<Integer>();
	ArrayList<Double> chosenAnnualSR = new ArrayList<Double>();
	double[] sortedAnnualSR;
	// this is the final chosen top stocks' annual Sharpe ratio
	double[] newSortedAnnualSR;
	// this is the final chosen top stocks' location in initial stock list
	ArrayList<Integer> stockLocation = new ArrayList<Integer>();
	// this is the final chosen top stocks' symbol
	ArrayList<String> stockSymbol = new ArrayList<String>();
	// set up HashMap for chosen stocks symbol with their Sharpe ratio
	HashMap<String, Double> chosenStockWithSR = new HashMap<>();
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
	public SharpeRatioAnalysis() {

		for(int i = 0; i < stockList.size(); i++) {
			ArrayList<Double> retData = new ArrayList<Double>();

			for(int j = 0; j < Data.get(stockList.get(i)).size(); j++) {
				retData.add(Data.get(stockList.get(i)).get(j));
			}

			// I. calculate annual return of each stock, S&P 500 Index, and risk-free bond
			double temp = 1.0000;
			temp = cm.calculateAnnRet(retData);
			temp = round(temp, 4);
			annualRet.add(temp);

			// II. calculate annual standard deviation of each stock, S&P 500 Index, and risk-free bond
			// 1. firstly calculate the average mean
			double tempavg = 0.0000;
			tempavg = cm.calculateAverage(retData);

			// 2. calculate standard deviation
			double temp2 = 0.0000;
			temp2 = cm.calculateAnnStd(retData, tempavg);
			temp2 = round(temp2, 4);
			annualStd.add(temp2);
		}

		// III. calculate Sharpe Ratio
		double temp3 = 0.0000;
		for(int i = 0; i < annualRet.size() - 1; i++) {
			temp3 = (annualRet.get(i) - annualRet.get(annualRet.size() - 1)) / annualStd.get(i);
			temp3 = round(temp3, 4);
			annualSR.add(temp3);
		}

		// filter ID of stocks that has higher annual Sharpe ratio than S&P 500 Index
		for(int i =  0; i < annualSR.size() - 1; i++) {
			if(annualSR.get(i) > annualSR.get(annualSR.size() - 1)) {
				filteredStockID.add(i);
			}
		}

		// get and output the chosen annual SR
		for(int i = 0; i < filteredStockID.size(); i++) {
			chosenAnnualSR.add(annualSR.get(filteredStockID.get(i)));
		}

		// change the format of chosen annual SR from double Array List to doubles
		sortedAnnualSR = new double[chosenAnnualSR.size()];
		for(int i = 0; i < chosenAnnualSR.size(); i++) {
			sortedAnnualSR[i] = chosenAnnualSR.get(i);
		}
		Arrays.sort(sortedAnnualSR);
		newSortedAnnualSR = cm.newSort(sortedAnnualSR);

		// get chosen stocks' location in initial stock list
		for(int i = 0; i < newSortedAnnualSR.length; i++) {
			for(int j = 0; j < annualSR.size(); j++) {
				if(newSortedAnnualSR[i] == annualSR.get(j)) {
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
			chosenStockWithSR.put(stockSymbol.get(i), newSortedAnnualSR[i]);
		}

		// set up HashMap for chosen stock with monthly returns data
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithMonthlyReturns.put(stockSymbol.get(i), Data.get(stockSymbol.get(i)));
		}
	}

	public static void main(String[] args) {

		// System.out.println(stockList);
		// System.out.println(Data);
		
		SharpeRatioAnalysis test = new SharpeRatioAnalysis();
		
		// print out results for verification
		System.out.println(test.getAnnualRet());
		System.out.println(test.getAnnualRet().size());
		System.out.println(test.getAnnualStd());
		System.out.println(test.getAnnualStd().size());
		System.out.println(test.getAnnualSR());
		System.out.println(test.getAnnualSR().size());

		// print out ID of stocks that has higher annual Sharpe ratio than S&P 500 Index
		System.out.println(test.getFilteredStockID());
		System.out.println(test.getFilteredStockID().size());

		System.out.println(test.getChosenAnnualSR());
		System.out.println(test.getChosenAnnualSR().size());

		// print out the sorted SR
		test.cm.printArray(test.getSortedAnnualSR());
		System.out.println(test.getSortedAnnualSR().length);

		test.cm.printArray(test.getNewSortedAnnualSR());
		System.out.println(test.getNewSortedAnnualSR().length);

		System.out.println(test.getStockLocation());
		System.out.println(test.getStockLocation().size());

		// print selected stocks symbols list, the list has highest SR to lowest SR
		System.out.println(test.getStockSymbol());
		System.out.println(test.getStockSymbol().size());

		// print the selected stock HashMap with SR
		System.out.println(test.getChosenStockWithSR());
		System.out.println(test.getChosenStockWithSR().size());
		System.out.println(test.getChosenStockWithSR().get("FISV US Equity"));

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

	public ArrayList<Double> getAnnualRet() {
		return annualRet;
	}

	public ArrayList<Double> getAnnualStd() {
		return annualStd;
	}

	public ArrayList<Double> getAnnualSR() {
		return annualSR;
	}

	public ArrayList<Integer> getFilteredStockID() {
		return filteredStockID;
	}

	public ArrayList<Double> getChosenAnnualSR() {
		return chosenAnnualSR;
	}

	public double[] getSortedAnnualSR() {
		return sortedAnnualSR;
	}

	public double[] getNewSortedAnnualSR() {
		return newSortedAnnualSR;
	}

	public ArrayList<Integer> getStockLocation() {
		return stockLocation;
	}

	public ArrayList<String> getStockSymbol() {
		return stockSymbol;
	}

	public HashMap<String, Double> getChosenStockWithSR() {
		return chosenStockWithSR;
	}

	public HashMap<String, ArrayList<Double>> getChosenStockWithMonthlyReturns() {
		return chosenStockWithMonthlyReturns;
	}

}
