import java.util.*;
import java.io.*;

public class selectStock {

	CalculationMethods cm = new CalculationMethods();

	SharpeRatioAnalysis sr = new SharpeRatioAnalysis();
	ROAAnalysis roa = new ROAAnalysis();

	ArrayList<String> stockListSR = sr.getStockSymbol();
	ArrayList<String> stockListROA = roa.getStockSymbol();

	ArrayList<Integer> location = new ArrayList<Integer>();
	ArrayList<String> selectedStock = new ArrayList<String>();
	
	public selectStock() {
		
		for(int i = 0; i < stockListSR.size(); i++) {
			for(int j = 0; j < stockListROA.size(); j++) {
				if(stockListSR.get(i).contentEquals(stockListROA.get(j))) {
					location.add(i);
				}
			}
		}
		
		for(int i = 0; i < location.size(); i++) {
			selectedStock.add(stockListSR.get(location.get(i)));
		}
		
	}

	public static void main(String[] args) {

		selectStock test = new selectStock();

		System.out.println(test.stockListSR);
		System.out.println(test.stockListSR.size());
		System.out.println();
		
		System.out.println(test.stockListROA);
		System.out.println(test.stockListROA.size());
		System.out.println();
		
		System.out.println(test.getLocation());
		System.out.println(test.getLocation().size());
		System.out.println();
		
		System.out.println(test.getSelectedStock());
		System.out.println(test.getSelectedStock().size());
	}
	
	public ArrayList<Integer> getLocation(){
		return location;
	}

	public ArrayList<String> getSelectedStock() {
		return selectedStock;
	}
	
}
