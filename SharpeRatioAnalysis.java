import java.util.*;
import java.io.*;

public class SharpeRatioAnalysis {

	// set up array list of annual return, annual standard deviation, and annual Sharpe ratio
	static ArrayList<Double> annualRet = new ArrayList<Double>();
	static ArrayList<Double> annualStd = new ArrayList<Double>();
	static ArrayList<Double> annualSR = new ArrayList<Double>();

	// write a method to sort numbers from the highest to lowest
	public static double[] sort (ArrayList<Double> data){
		double[] newData = new double[data.size()];
		double temp;
		for (int i = 0; i < data.size() - 1; i++){
			if(data.get(i) < data.get(i + 1)){
				temp = data.get(i);
				newData[i] = data.get(i + 1);
				newData[i + 1] = temp;
			}
		}
		 // System.out.println(newData);
		return newData;
	}
	
	public static void printArray(double[] values){
		for (int i = 0; i < values.length; i++){
			System.out.print(values[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		DataReader sr = new DataReader("return.csv");
		ArrayList<String> stockList = sr.stockList;
		// System.out.println(stockList);
		HashMap<String, ArrayList<Double>> Data = sr.Data;

		for(int i = 0; i < stockList.size(); i++) {
			ArrayList<Double> retData = new ArrayList<Double>();

			for(int j = 0; j < Data.get(stockList.get(i)).size(); j++) {
				retData.add(Data.get(stockList.get(i)).get(j));
			}

			// I. calculate annual return of each stock, S&P 500 Index, and risk-free bond
			double temp = 1.0000;
			for(int k = 0; k < retData.size(); k++) {
				temp *= (1 + retData.get(k));
			}
			temp = Math.pow(temp, (double) 12/retData.size()) - 1;
			annualRet.add(temp);

			// II. calculate annual standard deviation of each stock, S&P 500 Index, and risk-free bond
			// 1. firstly calculate the average mean
			double tempavg = 0.0000;
			for(int k = 0; k < retData.size(); k++) {
				tempavg += retData.get(k);
			}
			tempavg = tempavg / retData.size();

			// 2. calculate standard deviation
			double temp2 = 0.0000;
			for(int k = 0; k < retData.size(); k++) {
				temp2 += Math.pow((retData.get(k) - tempavg), 2) / retData.size();
			}
			temp2 = Math.pow(temp2, 0.5) * Math.pow(12, 0.5);
			annualStd.add(temp2);
		}

		// III. calaulcate Sharpe Ratio
		double temp3 = 0.0000;
		for(int i = 0; i < annualRet.size() - 1; i++) {
			temp3 = (annualRet.get(i) - annualRet.get(annualRet.size() - 1)) / annualStd.get(i);
			annualSR.add(temp3);
		}

		// print out results for verification
		// System.out.println(annualRet);
		// System.out.println(annualRet.size());
		// System.out.println(annualStd);
		// System.out.println(annualStd.size());
		System.out.println(annualSR);
		//System.out.println(annualSR.size());
		printArray(sort(annualSR));
	}
}
