import java.util.*;

public class CalculationMethods {

	// this method is used to calculate the annualized geometric return of an double ArrayList
	public double calculateAnnRet(ArrayList<Double> values) {
		double annRet = 1.0000;
		for(int i = 0; i < values.size(); i++) {
			annRet *= 1 + values.get(i);
		}
		annRet = Math.pow(annRet, (double) 12 / values.size()) - 1;
		return (double) annRet;
	}

	// this method is used to calculate the average of an double ArrayList
	// this method can also be used to calculate average of ROA and leverage ratio
	public double calculateAverage(ArrayList<Double> values) {
		double average = 0.0000;
		for(int i = 0; i < values.size(); i++) {
			average += values.get(i);
		}
		average = average / values.size();
		return (double) average;
	}

	// this method is used to calculate the annualized standard deviation of an double ArrayList
	public double calculateAnnStd(ArrayList<Double> values, double average) {
		double annStd = 0.0000;
		for(int i = 0; i < values.size(); i++) {
			annStd += Math.pow(values.get(i) - average, 2);
		}
		annStd = annStd / values.size();
		annStd = Math.pow(annStd, 0.5) * Math.pow(12, 0.5);
		return (double) annStd;
	}

	// this method is used to sort numbers sorted by Arrays.sort() from highest to lowest
	// as the number sorted by Arrays.sort() is from lowest to highest
	// this method can also be used to sort the average of ROA from highest to lowest
	// this method does not need to be used for average leverage ratio, as the lower leverage ratio, the better
	public double[] newSort(double[] values) {
		if(values.length % 2 == 0) {
			for(int i = 0; i < values.length / 2; i++) {
				double temp = values[i];
				values[i] = values[values.length - 1 - i];
				values[values.length - 1 - i] = temp;
			}
		}
		else if(values.length % 2 == 1) {
			for(int i = 0; i < (values.length - 1) / 2; i++) {
				double temp = values[i];
				values[i] = values[values.length - 1 - i];
				values[values.length - 1 - i] = temp;
			}
		}
		return values;
	}

	// this method is used to print double[] array list
	// this method also need to be used in sorting ROA and leverage ratio for outputing the sorting results
	public void printArray(double[] values){
		for (int i = 0; i < values.length; i++){
			System.out.print(values[i] + " ");
		}
		System.out.println();
	}

	// this method is used to print int[] array list
	public void printArray(int[] values){
		for (int i = 0; i < values.length; i++){
			System.out.print(values[i] + " ");
		}
		System.out.println();
	}

	// this method is used to print double[][] array list
	public void printBoard(double[][] values) {
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[1].length; j++) {
				System.out.print("" + values[i][j]+ " ");
			}
			System.out.println("");
		}
	}

	// this method is used to get the minimum length of all returns series for correlation and covariance calculation
	public int getMinLength(ArrayList<String> stockList, HashMap<String, ArrayList<Double>> returns) {
		int min = 0;
		int[] retSeriesLength = new int[returns.size()];
		for(int i = 0; i < returns.size(); i++) {
			retSeriesLength[i] = returns.get(stockList.get(i)).size();
		}
		Arrays.sort(retSeriesLength);
		min = retSeriesLength[0];
		// cm.printArray(retSeriesLength);
		// System.out.println(min);
		return min;
	}

	// this method is used to calculate the average of an double[] values for correlation and covariance calculation
	// this method can also be used to calculate average of ROA and leverage ratio
	public double calculateAverage(double[] values) {
		double average = 0.0000;
		for(int i = 0; i < values.length; i++) {
			average += values[i];
		}
		average = average / values.length;
		return (double) average;
	}

	// this method is used to calculate the annualized geometric return of an double[] array
	public double calculateAnnRet(double[] values) {
		double annRet = 1.0000;
		for(int i = 0; i < values.length; i++) {
			annRet *= 1 + values[i];
		}
		annRet = Math.pow(annRet, (double) 12 / values.length) - 1;
		return (double) annRet;
	}

	// this method is used to calculate the standard deviation of an double[] values for correlation and covariance calculation
	public double calculateStd(double[] values) {
		double average = calculateAverage(values);
		double Std = 0.0000;
		for(int i = 0; i < values.length; i++) {
			Std += Math.pow(values[i] - average, 2);
		}
		Std = Std / (values.length - 1);
		Std = Math.pow(Std, 0.5);
		return (double) Std;
	}

	// this method is used to calculate the sumproduct of two series of double[] values for correlation and covariance calculation
	// this method is also used to calculate portfolio return
	// be careful that the size of the double x and double y must be equal
	public double sumProduct(double[] x, double[] y) {
		double sumproduct = 0.0000;
		for(int i = 0; i < x.length; i++) {
			sumproduct += x[i] * y[i];
		}
		return (double) sumproduct;
	}

	/**
	 * this method is used to get returns data of stocks for calculating covariance
	 * @param stockList
	 * @param returns
	 * @return
	 */
	public double[][] getReturnsData(ArrayList<String> stockList, HashMap<String, ArrayList<Double>> returns){

		int minvalue = getMinLength(stockList, returns);

		// get the returns series matrix
		double[][] returnsData = new double[stockList.size()][minvalue];
		for(int i = 0; i < stockList.size(); i++) {
			for(int j = 0; j < minvalue; j++) {
				returnsData[i][j] = returns.get(stockList.get(i)).get(returns.get(stockList.get(i)).size() - minvalue + j);
			}
		}
		return returnsData;
	}

	// this method is used to calculate the covariance matrix of returns for calculating portfolio standard deviation
	public double[][] calculateCovriance(double[][] returnsData){

		int rowValue = returnsData.length;
		int columnValue = returnsData[1].length;

		// calculate average of each series returns
		double[] mean = new double[rowValue];
		for(int i = 0; i < mean.length; i++) {
			mean[i] = calculateAverage(returnsData[i]);
		}

		// calculate correlation matrix of all series returns
		double[][] corr = new double[rowValue][rowValue];
		for(int i = 0; i < rowValue; i++) {
			for(int j = 0; j < rowValue; j++) {
				corr[i][j] = (sumProduct(returnsData[i], returnsData[j]) - columnValue * mean[i] * mean[j]) / (columnValue * calculateStd(returnsData[i]) * calculateStd(returnsData[j]));
			}
		}

		// calculate covariance matrix of all series returns
		double[][] covMatrix = new double[rowValue][rowValue];
		for(int i = 0; i < rowValue; i++) {
			for(int j = 0; j < rowValue; j++) {
				covMatrix[i][j] = calculateStd(returnsData[i]) * calculateStd(returnsData[j]) * corr[i][j];
			}
		}
		return covMatrix;
	}

	// this method is used to transform a double[][] matrix for calculating portfolio standard deviation
	public double[][] transformMatrix(double[][] Matrix){
		double[][] transformedMatrix = new double[Matrix[1].length][Matrix.length];
		for(int i = 0; i < transformedMatrix.length; i++) {
			for(int j = 0; j < transformedMatrix[1].length; j++) {
				transformedMatrix[i][j] = Matrix[j][i];
			}
		}
		return transformedMatrix;
	}

	// this method is used to transform a double[] array matrix for calculating portfolio standard deviation
	public double[][] transformMatrix(double[] Matrix){
		double[][] transformedMatrix = new double[Matrix.length][1];
		for(int i = 0; i < transformedMatrix.length; i++) {
			transformedMatrix[i][0] = Matrix[i];
		}
		return transformedMatrix;
	}

	// this method is specifically used to multiply a double[] array matrix with a double[][] matrix
	// for calculating portfolio standard deviation
	// the wideness of the double[] array matrix and the height of the double[][] matrix must be equal
	// besides, the wideness of the double[][] matrix and the height of the double[][] matrix must be equal
	public double[] multiplyMatrix(double[] matrix1, double[][] matrix2) {
		double[] matrix = new double[matrix2[1].length];
		double[][] newMatrix2 = transformMatrix(matrix2);
		for(int i = 0; i < newMatrix2.length; i++) {
			matrix[i] = sumProduct(matrix1, newMatrix2[i]);
		}
		return matrix;
	}

	// this method is specifically used to get the final portfolio standard deviation
	// this matrix version is a double[] array matrix * double[][] matrix * the same first double[] array matrix
	// the wideness of the double[] array matrix and the height of the double[][] matrix must be equal
	// besides, the wideness of the double[][] matrix and the height of the double[][] matrix must be equal
	public double calculatePortStd(double[] weight, double[][] covariance) {
		double portStd = 0.0000;
		double[] temp = multiplyMatrix(weight, covariance);
		portStd = sumProduct(temp, weight);
		return portStd;
	}

	// this method is used to calculate the final portfolio return
	// the size of double[] weight and the size of double[] annRet must be equal
	public double calculatePortRet(double[] weight, double[] annRet) {
		double portRet = sumProduct(weight, annRet);
		return (double) portRet;
	}

	// this method is to generate a double[] array with n random numbers with their sum to be one
	public double[] generateRandomNumber(int n) {
		double[] weights = new double[n];
		double temp = 0.0000;
		for(int i = 0; i < weights.length - 1; i++) {
			weights[i] = Math.random() / (0.5 * n);
			temp += weights[i];
		}
		weights[n - 1] = 1 - temp;
		return weights;
	}
	
	// this method is used to calculate the sum of a double[] array
	public double calculateSum(double[] values) {
		double sum = 0.0000;
		for(int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		return sum;
	}
}
