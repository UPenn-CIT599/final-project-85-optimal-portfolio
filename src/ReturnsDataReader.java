import java.util.*;
import java.io.*;

public class ReturnsDataReader {

	public static void main(String[] args) {
		HashMap<String, ArrayList<Double>> returnData = new HashMap<String, ArrayList<Double>>();
		ArrayList<String> stockList = new ArrayList<String>();

		try {
			FileReader f = new FileReader("return.csv");
			Scanner scanner = new Scanner(f);
			scanner.nextLine();

			while(scanner.hasNextLine()) {
				String info = scanner.nextLine();
				String[] piecesOfInfo = info.split(",");
				String stockTicker = piecesOfInfo[0];
				stockList.add(stockTicker);
				ArrayList<Double> cast = new ArrayList<Double>();
				for(int i = 1; i < piecesOfInfo.length; i++) {
					Double temp = null;
					try {
						temp = Double.parseDouble(piecesOfInfo[i]);
						cast.add(temp);
					} catch (NumberFormatException e){
						// temp = 0.0000;
						// cast.add(temp);
					}
				}
				returnData.put(stockTicker,cast);
			}

			// retrieve movie information
			System.out.println(stockList);
			for(int i = 0; i < stockList.size(); i++) {
				System.out.print(returnData.get(stockList.get(i)));
				System.out.println();
			}
			// System.out.println(returnData);
			// System.out.println(returnData.get("AVGO US Equity"));
			// System.out.println(returnData.keySet());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
