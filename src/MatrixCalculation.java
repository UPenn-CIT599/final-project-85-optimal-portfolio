import java.util.*;
import java.io.*;

public class MatrixCalculation {
	
	CalculationMethods cm = new CalculationMethods();
	SharpeRatioAnalysis sra = new SharpeRatioAnalysis();
	ArrayList<String> stockList = sra.getStockSymbol();
	HashMap<String, ArrayList<Double>> returns = sra.getChosenStockWithMonthlyReturns();
	
	double[][] returnsData = cm.getReturnsData(stockList, returns);
	
	double[][] covarianceMatrix;
	double[][] transformedCovarianceMatrix;
	
	int n = 6; // n can be any positive integer larger than 1
	
	double[] matrix = new double[n];
	public double[] getMatrix() {
		for(double i = 0.0000; i < matrix.length; i++) {
			matrix[(int) i] = i + 1;
		}
		return matrix;
	}
	
	double[][] matrix2 = new double[n][n];
	public double[][] getMatrix2() {
		for(double i = 0.0000; i < matrix2.length; i++) {
			for(double j = 0.0000; j < matrix2[1].length; j++) {
				matrix2[(int) i][(int) j] = i + 1 + 3 * j;
			}
		}
		return matrix2;
	}

	public double[][] getCovarianceMatrix() {
		covarianceMatrix = cm.calculateCovriance(returnsData);
		return covarianceMatrix;
	}
	
	public double[][] getTransformedCovarianceMatrix() {
		transformedCovarianceMatrix = cm.transformMatrix(matrix);
		return transformedCovarianceMatrix;
	}
	
	int randowNumberLength = 19;
	double[][] weights = new double[100][randowNumberLength];
	
	public double[][] getWeights() {
		for(int i = 0; i < weights.length; i++) {
			weights[i] = cm.generateRandomNumber(randowNumberLength);
		}
		return weights;
	}

	double[] sum = new double[weights.length];
	
	public double[] getSum() {
		for(int i = 0; i < sum.length; i++) {
			sum[i] = cm.calculateSum(weights[i]);
		}
		return sum;
	}

	public static void main(String[] args) {
		MatrixCalculation mc = new MatrixCalculation();
		
		mc.cm.printBoard(mc.getCovarianceMatrix());
		System.out.println();
		
		mc.cm.printArray(mc.getMatrix());
		System.out.println();
		
		mc.cm.printBoard(mc.getTransformedCovarianceMatrix());
		System.out.println();
		
		mc.cm.printBoard(mc.getMatrix2());
		System.out.println();
		
		double[] result = mc.cm.multiplyMatrix(mc.getMatrix(), mc.getMatrix2());
		mc.cm.printArray(result);
		System.out.println();
		
		double result2 = mc.cm.calculatePortStd(mc.getMatrix(), mc.getMatrix2());
		System.out.println(result2);
		System.out.println();
		
		mc.cm.printBoard(mc.getWeights());
		System.out.println();
		
		mc.cm.printArray(mc.getSum());
	}
}
