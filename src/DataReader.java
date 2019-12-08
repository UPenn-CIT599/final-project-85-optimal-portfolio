import java.util.*;
import java.io.*;

public class DataReader {

	/**
	 * set up stock data HashMap and stock list
	 */
	HashMap<String, ArrayList<Double>> Data = new HashMap<String, ArrayList<Double>>();
	ArrayList<String> stockList = new ArrayList<String>();

	/**
	 * read csv files data into Java
	 * @param fileName
	 */
	public DataReader(String fileName) {

		/**
		 * exception handling
		 */
		if(fileName == null) {
			throw new IllegalArgumentException();
		}

		try {
			FileReader f = new FileReader(fileName);
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
					}
				}
				Data.put(stockTicker,cast);
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public HashMap<String, ArrayList<Double>> getData() {
		return Data;
	}

	public ArrayList<String> getStockList() {
		return stockList;
	}

}
