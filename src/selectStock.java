import java.util.*;

public class selectStock {

	/**
	 * link classes
	 */
	CalculationMethods cm = new CalculationMethods();
	SharpeRatioAnalysis sr = new SharpeRatioAnalysis();
	ROAAnalysis roa = new ROAAnalysis();
	LeverageRatioAnalysis leverage = new LeverageRatioAnalysis();

	/**
	 * retrieve stock lists sorted by sharpe ratio, roa, and leverage ratio
	 * and HashMap returns data from sharpe ratio
	 */
	ArrayList<String> stockListSR = sr.getStockSymbol();
	ArrayList<String> stockListROA = roa.getStockSymbol();
	ArrayList<String> stockListLeverage = leverage.getStockSymbol();
	HashMap<String, ArrayList<Double>> Data = sr.Data;

	/**
	 * set up variables to get the final top 10 selected stocks
	 */
	ArrayList<Integer> location1 = new ArrayList<Integer>();
	ArrayList<String> selectedStock1 = new ArrayList<String>();

	ArrayList<Integer> location2 = new ArrayList<Integer>();
	ArrayList<String> selectedStock2 = new ArrayList<String>();

	int numDiff;

	ArrayList<Integer> location3 = new ArrayList<Integer>();

	int[] location = new int[stockListSR.size()];
	int[] referenceLocation = new int[stockListSR.size()];

	ArrayList<Integer> locationForSelection = new ArrayList<Integer>();

	/**
	 * this is the finally selected stocks
	 */
	ArrayList<String> selectedStock = new ArrayList<String>();

	/**
	 * set up HashMap for chosen stocks symbol with their monthly returns, and this is used for later matrix calculation
	 */
	HashMap<String, ArrayList<Double>> chosenStockWithMonthlyReturns = new HashMap<>();

	public selectStock() {

		/**
		 * find the location in stockListSR of common stocks that are in stock list sortrd by sharpe ratio analysis
		 * and in stock list that are sorted by roa analysis
		 */
		for(int i = 0; i < stockListSR.size(); i++) {
			for(int j = 0; j < stockListROA.size(); j++) {
				if(stockListSR.get(i).contentEquals(stockListROA.get(j))) {
					location1.add(i);
				}
			}
		}

		/**
		 * set the stock list that made up of stocks commonly existed in sharpe ratio analysis list and
		 * roa analysis list
		 */
		for(int i = 0; i < location1.size(); i++) {
			selectedStock1.add(stockListSR.get(location1.get(i)));
		}

		/**
		 * go on finding the location in stockListSR of common stocks that are in stock list selectedStock1
		 * and in stock list that are sorted by leverage ratio analysis
		 */
		for(int i = 0; i < selectedStock1.size(); i++) {
			for(int j = 0; j < stockListLeverage.size(); j++) {
				if(selectedStock1.get(i).contentEquals(stockListLeverage.get(j))) {
					location2.add(i);
				}
			}
		}

		/**
		 * set the stock list that made up of stocks commonly existed in sharpe ratio analysis list, roa analysis
		 * list and leverage ratio analysis list
		 * and we need to add more stocks from Sharpe ratio list if the common chosen stocks from the three list
		 * are less than 10
		 */
		for(int i = 0; i < location2.size(); i++) {
			selectedStock2.add(selectedStock1.get(location2.get(i)));
		}

		/**
		 * set up location which is used for adding stocks from sharpe ratio list if the chosen stocks
		 * from the three lists is less than 10
		 */
		for(int i = 0; i < stockListSR.size(); i++) {
			location[i] = i;
		}

		/**
		 * set up the final chosen top 10 stocks
		 * and we need to add more stocks from Sharpe ratio list if the common chosen stocks from the three list
		 * are less than 10
		 */
		for(int i = 0; i < selectedStock2.size(); i++) {
			selectedStock.add(selectedStock2.get(i));
		}

		if(selectedStock.size() < 10) {

			numDiff = 10 - selectedStock.size();

			for(int j = 0; j < selectedStock.size(); j++) {
				for(int i = 0; i < stockListSR.size(); i++) {
					if(selectedStock.get(j).contentEquals(stockListSR.get(i))) {
						location3.add(i);
					}
				}
			}

			for(int i = 0; i < location.length; i++) {
				if(location3.contains(location[i])) {
					referenceLocation[i] = 1;
				}
				else {
					referenceLocation[i] = 0;
				}
			}

			for(int i = 0; i < referenceLocation.length; i++) {
				if(referenceLocation[i] == 0) {
					locationForSelection.add(location[i]);
				}
			}

			if(locationForSelection.size() >= numDiff) {
				for(int i = 0; i < numDiff; i++) {
					selectedStock.add(stockListSR.get(locationForSelection.get(i)));
				}
			}
			else if(locationForSelection.size() < numDiff) {
				for(int i = 0; i < locationForSelection.size(); i++) {
					selectedStock.add(stockListSR.get(locationForSelection.get(i)));
				}
			}

		}

		/**
		 * set up the HashMap of finally chose top 10 stocks with returns data 
		 */
		for(int i = 0; i < selectedStock.size(); i++) {
			chosenStockWithMonthlyReturns.put(selectedStock.get(i), Data.get(selectedStock.get(i)));
		}
	}

	/**
	 * output results for verification
	 * @param args
	 */
	public static void main(String[] args) {

		selectStock test = new selectStock();

		System.out.println("The finally chosen top 10 stocks based on Sharpe ratio, ROA, and leverage ratio are shown as follows:");
		System.out.println(test.getSelectedStock());
		
	}

	public int[] getLocation() {
		return location;
	}

	public int[] getReferenceLocation() {
		return referenceLocation;
	}

	public ArrayList<Integer> getLocationForSelection() {
		return locationForSelection;
	}

	public ArrayList<Integer> getLocation1(){
		return location1;
	}

	public ArrayList<String> getSelectedStock1() {
		return selectedStock1;
	}

	public ArrayList<Integer> getLocation2(){
		return location2;
	}

	public ArrayList<String> getSelectedStock2() {
		return selectedStock2;
	}

	public ArrayList<Integer> getLocation3() {
		return location3;
	}

	public ArrayList<String> getSelectedStock() {
		return selectedStock;
	}

	public HashMap<String, ArrayList<Double>> getChosenStockWithMonthlyReturns() {
		return chosenStockWithMonthlyReturns;
	}

}
