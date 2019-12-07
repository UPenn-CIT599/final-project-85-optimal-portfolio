import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Read and calculate Leverage Ratio
 * sorted from lowest to highest
 */
public class LeverageRatioAnalysis {
    DataReader readLeverageRatio = new DataReader("leverageratio.csv");
    ArrayList<String> stockList = readLeverageRatio.getStockList();
    HashMap<String, ArrayList<Double>> leverageRatioNumbers = readLeverageRatio.getData();

    CalculationMethods cm = new CalculationMethods();

    // set up array lists of average Leverage Ratio
    ArrayList<Double> averageLeverageRatio = new ArrayList<Double>();
    ArrayList<Integer> filteredStockID = new ArrayList<Integer>();
    ArrayList<Double> chosenAverageLeverageRatio = new ArrayList<Double>();
    double[] sortedAverageLeverageRatio;
    // this is the final chosen top stocks' average Leverage Ratio
    double[] newSortedAverageLeverageRatio;
    // this is the final chosen top stocks' location in initial stock list
    ArrayList<Integer> stockLocation = new ArrayList<Integer>();
    // this is the final chosen top stocks' symbol
    ArrayList<String> stockSymbol = new ArrayList<String>();
    // set up HashMap for chosen stocks symbol with their average Leverage Ratio
    HashMap<String, Double> chosenStockWithLeverageRatio = new HashMap<>();
    // set up HashMap for chosen stocks symbol with their monthly returns, and this is used for later matrix calculation
    HashMap<String, ArrayList<Double>> chosenStockWithMonthlyReturns = new HashMap<>();

    /**
     * this method is used to round double value to places decimal number
     *
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
     * Constructor to set up ArrayList and HashMap for future files usage
     */
    public LeverageRatioAnalysis() {

        for (int i = 0; i < stockList.size(); i++) {
            ArrayList<Double> retData = new ArrayList<Double>();

            for (int j = 0; j < leverageRatioNumbers.get(stockList.get(i)).size(); j++) {
                retData.add(leverageRatioNumbers.get(stockList.get(i)).get(j));
            }

            // I. calculate annual return of each stock, S&P 500 Index, and risk-free bond
            double temp = 1.0000;
            temp = cm.calculateAverage(retData);
            temp = round(temp, 4);
            averageLeverageRatio.add(temp);
        }

        // filter ID of stocks that has lower annual leverage ratio than S&P 500 Index
        for (int i = 0; i < averageLeverageRatio.size() - 1; i++) {
            if (averageLeverageRatio.get(i) < averageLeverageRatio.get(averageLeverageRatio.size() - 1)) {
                filteredStockID.add(i);
            }
        }

        // output filtered stocks
        for (int i = 0; i < filteredStockID.size(); i++) {
            chosenAverageLeverageRatio.add(averageLeverageRatio.get(filteredStockID.get(i)));
        }

        // convert to array of double
        sortedAverageLeverageRatio = new double[chosenAverageLeverageRatio.size()];
        for (int i = 0; i < chosenAverageLeverageRatio.size(); i++) {
            sortedAverageLeverageRatio[i] = chosenAverageLeverageRatio.get(i);
        }
        Arrays.sort(sortedAverageLeverageRatio);
        newSortedAverageLeverageRatio = sortedAverageLeverageRatio;

        // get chosen stocks' location in initial stock list
        for (int i = 0; i < newSortedAverageLeverageRatio.length; i++) {
            for (int j = 0; j < averageLeverageRatio.size(); j++) {
                if (newSortedAverageLeverageRatio[i] == averageLeverageRatio.get(j)) {
                    stockLocation.add(j);
                }
            }
        }

        // get chosen stocks' symbols
        for (int i = 0; i < stockLocation.size(); i++) {
            stockSymbol.add(stockList.get(stockLocation.get(i)));
        }

        // set up HashMap for chosen stock with Leverage Ratio ratio data
        for (int i = 0; i < stockSymbol.size(); i++) {
            chosenStockWithLeverageRatio.put(stockSymbol.get(i), newSortedAverageLeverageRatio[i]);
        }

        // set up HashMap for chosen stock with monthly returns data
        for (int i = 0; i < stockSymbol.size(); i++) {
            chosenStockWithMonthlyReturns.put(stockSymbol.get(i), leverageRatioNumbers.get(stockSymbol.get(i)));
        }
    }

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println(stockList);
        // System.out.println(Data);

        LeverageRatioAnalysis test = new LeverageRatioAnalysis();

        // print out results for verification
        System.out.println(test.getAverageLeverageRatio());
        System.out.println(test.getAverageLeverageRatio().size());

        // print out ID of stocks that has higher average Leverage Ratio than S&P 500 Index
        System.out.println(test.getFilteredStockID());
        System.out.println(test.getFilteredStockID().size());

        System.out.println(test.getChosenAverageLeverageRatio());
        System.out.println(test.getChosenAverageLeverageRatio().size());

        // print out the sorted average Leverage Ratio
        test.cm.printArray(test.getSortedAverageLeverageRatio());
        System.out.println(test.getSortedAverageLeverageRatio().length);

        test.cm.printArray(test.getNewSortedAverageLeverageRatio());
        System.out.println(test.getNewSortedAverageLeverageRatio().length);

        System.out.println(test.getStockLocation());
        System.out.println(test.getStockLocation().size());

        // print selected stocks symbols list, the list has highest Leverage Ratio to lowest Leverage Ratio
        System.out.println(test.getStockSymbol());
        System.out.println(test.getStockSymbol().size());

        // print the selected stock HashMap with Leverage Ratio
        System.out.println(test.getChosenStockWithLeverageRatio());
        System.out.println(test.getChosenStockWithLeverageRatio().size());
        System.out.println(test.getChosenStockWithLeverageRatio().get("FISV US Equity"));

        // print the selected stock HashMap with returns data
        System.out.println(test.getChosenStockWithMonthlyReturns());
        System.out.println(test.getChosenStockWithMonthlyReturns().size());

        // for (String s : test.chosenStockWithLeverageRatio.keySet()) {
        // System.out.print(s);
        // System.out.print(" ");
        // System.out.print(test.chosenStockWithLeverageRatio.get(s));
        // System.out.println();
        // }

    }

    /**
     * getter
     *
     * @return
     */
    public ArrayList<Double> getAverageLeverageRatio() {
        return averageLeverageRatio;
    }

    /**
     * getter
     *
     * @return
     */
    public ArrayList<Integer> getFilteredStockID() {
        return filteredStockID;
    }

    /**
     * getter
     *
     * @return
     */
    public ArrayList<Double> getChosenAverageLeverageRatio() {
        return chosenAverageLeverageRatio;
    }

    /**
     * getter
     *
     * @return
     */
    public double[] getSortedAverageLeverageRatio() {
        return sortedAverageLeverageRatio;
    }

    /**
     * getter
     *
     * @return
     */
    public double[] getNewSortedAverageLeverageRatio() {
        return newSortedAverageLeverageRatio;
    }

    /**
     * getter
     *
     * @return
     */
    public ArrayList<Integer> getStockLocation() {
        return stockLocation;
    }

    /**
     * getter
     *
     * @return
     */
    public ArrayList<String> getStockSymbol() {
        return stockSymbol;
    }

    /**
     * getter
     *
     * @return
     */
    public HashMap<String, Double> getChosenStockWithLeverageRatio() {
        return chosenStockWithLeverageRatio;
    }

    /**
     * getter
     *
     * @return
     */
    public HashMap<String, ArrayList<Double>> getChosenStockWithMonthlyReturns() {
        return chosenStockWithMonthlyReturns;
    }
}
