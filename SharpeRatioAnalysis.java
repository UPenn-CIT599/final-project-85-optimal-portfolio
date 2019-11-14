import java.util.*;
import java.io.*;

public class SharpeRatioAnalysis {
	ArrayList<Double> annualReturn = new ArrayList<Double>();
	
	public static void main(String[] args) {
		DataReader sr = new DataReader("return.csv");
		ArrayList<String> stockList = sr.stockList;
		// System.out.println(stockList);
		HashMap<String, ArrayList<Double>> Data = sr.Data;
		ArrayList<Double> annualRet = new ArrayList<Double>();
		ArrayList<Double> annualStd = new ArrayList<Double>();
		
		for(int i = 0; i < stockList.size(); i++) {
			ArrayList<Double> retData = new ArrayList<Double>();
			
			for(int j = 0; j < Data.get(stockList.get(i)).size(); j++) {
				retData.add(Data.get(stockList.get(i)).get(j));
			}
			
			// calculate annual return of each stock, S&P 500 Index, and risk-free bond
			double temp = 1.0000;
			for(int k = 0; k < retData.size(); k++) {
				temp *= (1 + retData.get(k));
			}
			temp = Math.pow(temp, (double) 12/retData.size()) - 1;
			annualRet.add(temp);
			
			// calculate annual standard deviation of each stock, S&P 500 Index, and risk-free bond
			// 1. firstly calculate the average mean
			double temp2 = 0.0000;
			for(int k = 0; k < retData.size(); k++) {
				temp += (1 + retData.get(k));
			}
		}
		System.out.println(annualRet);
		System.out.println(annualRet.size());
		
	}
}
