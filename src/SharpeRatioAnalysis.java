import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SharpeRatioAnalysis {

	/**
	 * link class and methods
	 */
	DataReader dr = new DataReader("return.csv");
	ArrayList<String> stockList = dr.getStockList();
	HashMap<String, ArrayList<Double>> Data = dr.getData();
	CalculationMethods cm = new CalculationMethods();

	/**
	 * set up variables for calculating and sorting average Sharpe ratio, and get relevant stock list
	 * and set up HashMap with stock list and leverage ratio and monthly returns 
	 */
	ArrayList<Double> annualRet = new ArrayList<Double>();
	ArrayList<Double> annualStd = new ArrayList<Double>();
	ArrayList<Double> annualSR = new ArrayList<Double>();
	ArrayList<Integer> filteredStockID = new ArrayList<Integer>();
	ArrayList<Double> chosenAnnualSR = new ArrayList<Double>();
	double[] sortedAnnualSR;
	ArrayList<Integer> stockLocation = new ArrayList<Integer>();
	ArrayList<String> stockSymbol = new ArrayList<String>();
	HashMap<String, Double> chosenStockWithSR = new HashMap<>();
	HashMap<String, ArrayList<Double>> chosenStockWithMonthlyReturns = new HashMap<>();
	ArrayList<Double> rfData = new ArrayList<Double>();

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
	 * calculate average Sharpe ratio, sort it from highest to highest, and set HashMap
	 */
	public SharpeRatioAnalysis() {

		for(int i = 0; i < stockList.size(); i++) {
			ArrayList<Double> retData = new ArrayList<Double>();

			for(int j = 0; j < Data.get(stockList.get(i)).size(); j++) {
				retData.add(Data.get(stockList.get(i)).get(j));
			}

			/**
			 * calculate annual return of each stock, S&P 500 Index, and risk-free bond
			 */
			double temp = 1.0000;
			temp = cm.calculateAnnRet(retData);
			temp = round(temp, 4);
			annualRet.add(temp);

			/**
			 * calculate annual standard deviation of each stock, S&P 500 Index, and risk-free bond
			 * firstly calculate the average mean
			 */
			double tempavg = 0.0000;
			tempavg = cm.calculateAverage(retData);

			/**
			 * secondly calculate standard deviation
			 */
			double temp2 = 0.0000;
			temp2 = cm.calculateAnnStd(retData, tempavg);
			temp2 = round(temp2, 4);
			annualStd.add(temp2);
		}
		/**
		 * calculate Sharpe Ratio
		 */
		double temp3 = 0.0000;
		for(int i = 0; i < annualRet.size() - 1; i++) {
			temp3 = (annualRet.get(i) - annualRet.get(annualRet.size() - 1)) / annualStd.get(i);
			temp3 = round(temp3, 4);
			annualSR.add(temp3);
		}

		/**
		 * filter ID of stocks that has higher annual Sharpe ratio than S&P 500 Index
		 */
		for(int i =  0; i < annualSR.size() - 1; i++) {
			if(annualSR.get(i) > annualSR.get(annualSR.size() - 1)) {
				filteredStockID.add(i);
			}
		}

		/**
		 * get and output the chosen annual SR
		 */
		for(int i = 0; i < filteredStockID.size(); i++) {
			chosenAnnualSR.add(annualSR.get(filteredStockID.get(i)));
		}

		/**
		 * change the format of chosen annual SR from double Array List to doubles
		 * and at the same time adjust the size to select the top 20 stocks by sharpe ratio
		 */
		if(chosenAnnualSR.size() > 20) {
			sortedAnnualSR = new double[20];
			for(int i = 0; i < sortedAnnualSR.length; i++) {
				sortedAnnualSR[i] = chosenAnnualSR.get(i);
			}
		}
		else if(chosenAnnualSR.size() <=20 ) {
			sortedAnnualSR = new double[chosenAnnualSR.size()];
			for(int i = 0; i < sortedAnnualSR.length; i++) {
				sortedAnnualSR[i] = chosenAnnualSR.get(i);
			}	
		}
		Arrays.sort(sortedAnnualSR);
		cm.newSort(sortedAnnualSR);

		/**
		 * get chosen stocks' location in initial stock list
		 */
		for(int i = 0; i < sortedAnnualSR.length; i++) {
			for(int j = 0; j < annualSR.size(); j++) {
				if(sortedAnnualSR[i] == annualSR.get(j)) {
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
		 * set up HashMap for chosen stock with Sharpe ratio data
		 */
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithSR.put(stockSymbol.get(i), sortedAnnualSR[i]);
		}

		/**
		 * set up HashMap for chosen stock with monthly returns data
		 */
		for(int i = 0; i < stockSymbol.size(); i++) {
			chosenStockWithMonthlyReturns.put(stockSymbol.get(i), Data.get(stockSymbol.get(i)));
		}
		
		/**
		 * set up rfData
		 */
		for(int i = 0; i < Data.get(stockList.get(stockList.size() - 1)).size(); i++) {
			rfData.add(Data.get(stockList.get(stockList.size() - 1)).get(i));
		}
		
	}

	public static void main(String[] args) {
		
		SharpeRatioAnalysis test = new SharpeRatioAnalysis();
		
		/**
		 * print selected stocks symbols list, the list has highest SR to lowest SR
		 */
		System.out.println("The top 20 stocks with higher Sharpe ratio than the benchmark, S&P 500 Index, are shown as follows:");
		System.out.println(test.getStockSymbol());
		System.out.println();

		/**
		 * print out the sorted SR
		 */
		System.out.println("Their Sharpe ratios are shown as follows:");
		test.cm.printArray(test.getSortedAnnualSR());
		
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

	public ArrayList<Double> getRfData() {
		return rfData;
	}
	
}
