import java.util.*;
import java.io.*;

public class CovarianceCalculation {

	static CalculationMethods cm = new CalculationMethods();
	static SharpeRatioAnalysis sra = new SharpeRatioAnalysis();
	static ArrayList<String> stockList = sra.getStockSymbol();
	static HashMap<String, ArrayList<Double>> returns = sra.getChosenStockWithMonthlyReturns();
	static double[][] retdata = new double[stockList.size()][48];

	public static void main(String[] args) {
		// CovarianceCalculation test = new CovarianceCalculation();
		System.out.println(stockList);
		// System.out.println(returns);
		int[] retSeriesLength = new int[returns.size()];
		for(int i = 0; i < returns.size(); i++) {
			retSeriesLength[i] = returns.get(stockList.get(i)).size();
		}
		Arrays.sort(retSeriesLength);
		int min = retSeriesLength[0];
		cm.printArray(retSeriesLength);
		// System.out.println(min);

		for(int i = 0; i < stockList.size(); i++) {
			for(int j = 0; j < min; j++) {
				retdata[i][j] = returns.get(stockList.get(i)).get(returns.get(stockList.get(i)).size() - min + j);
			}
		}
		
		int a = retdata[0].length; // row, namely height
		int b = retdata[1].length; // column, namely wideness
		
		System.out.println(a);
		System.out.println(b);
		
		printBoard();

	}

	public static void printBoard() {
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 48; j++) {
				System.out.print("" + retdata[i][j]+ " ");
			}
			System.out.println("");
		}
	}
}
