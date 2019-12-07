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
	
	double[][] covMatrix = new double[sizevalue][sizevalue];
	
	public double[][] getCovMatrix() {
		// calculate average of each series returns
		double[] mean = new double[sizevalue];
		for(int i = 0; i < mean.length; i++) {
			mean[i] = cm.calculateAverage(retData[i]);
		}
		
		// calculate correlation matrix of all series returns
		double[][] corr = new double[sizevalue][sizevalue];
		for(int i = 0; i < sizevalue; i++) {
			for(int j = 0; j < sizevalue; j++) {
				corr[i][j] = (cm.sumProduct(retData[i], retData[j]) - minvalue * mean[i] * mean[j]) / (minvalue * cm.calculateStd(retData[i]) * cm.calculateStd(retData[j]));
			}
		}
		
		// calculate covariance matrix of all series returns
		double[][] covMatrix = new double[sizevalue][sizevalue];
		for(int i = 0; i < sizevalue; i++) {
			for(int j = 0; j < sizevalue; j++) {
				covMatrix[i][j] = cm.calculateStd(retData[i]) * cm.calculateStd(retData[j]) * corr[i][j];
			}
		}
		return covMatrix;
	}

	public static void main(String[] args) {
		
		CovarianceCalculation test = new CovarianceCalculation();
		// output the results
//		System.out.println(test.stockList);
//		System.out.println(test.returns);
		test.cm.printBoard(test.getRetData());
		System.out.println(test.getRetData().length); // this gives height or row of a matrix
		System.out.println(test.getRetData()[1].length); // this gives wideness or column of a matrix
//		test.cm.printArray(test.getRetData()[1]);
		System.out.println();
		
		double[] mean = new double[19];
		for(int i = 0; i < mean.length; i++) {
			mean[i] = test.cm.calculateAverage(test.getRetData()[i]);
		}
		test.cm.printArray(mean);
		
		System.out.println();
		
		test.cm.printBoard(test.getCovMatrix());

	}
}
