import java.util.*;
import java.io.*;

public class CovarianceCalculation {

	// link class and get parameters
	CalculationMethods cm = new CalculationMethods();
	SharpeRatioAnalysis sra = new SharpeRatioAnalysis();
	ArrayList<String> stockList = sra.getStockSymbol();
	HashMap<String, ArrayList<Double>> returns = sra.getChosenStockWithMonthlyReturns();

	// get the minimum length of returns series
	int minvalue = cm.getMinLength(stockList, returns);
	
	// get the returns series matrix
	double[][] retData = new double[stockList.size()][minvalue];
	
	public double[][] getRetData() {
		for(int i = 0; i < stockList.size(); i++) {
			for(int j = 0; j < minvalue; j++) {
				retData[i][j] = returns.get(stockList.get(i)).get(returns.get(stockList.get(i)).size() - minvalue + j);
			}
		}
		return retData;
	}
	
	// get the correlation matrix of all stocks
	int sizevalue = stockList.size();
	double[][] corr = new double[sizevalue][sizevalue];

	public double[][] getCorr() {
		double[] mean = new double[sizevalue];
//		for(int i = 0; i < sizevalue; i++) {
//			mean[i] = cm.calculateAverage(retData[i]);
//		}
		return corr;
	}

	public static void main(String[] args) {
		
		CovarianceCalculation test = new CovarianceCalculation();
		// output the results
		System.out.println(test.stockList);
		System.out.println(test.returns);
		test.cm.printBoard(test.getRetData());
		System.out.println(test.getRetData().length);
		System.out.println(test.getRetData()[1].length);
		test.cm.printArray(test.getRetData()[1]);

	}
}
