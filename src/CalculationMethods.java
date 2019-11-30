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
	
	// this method is used to get the minimum length of all returns series
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
	
}
