import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * this class reads and calculates ROA stocks
 */
public class ReadAndCalculateROA {
	/**
	 * @throws FileNotFoundException
	 */
	public void run() throws FileNotFoundException {
		Reader topMaxReader = new Reader("ROA.csv");
		HashMap<String, Double> roaMax = topMaxReader.readCSV("roa");
		Writer myWriter = new Writer();

		//open file for writing data
		File out = new File("roa-results.txt");
		PrintWriter pw = new PrintWriter(out);

		//write header to file
		pw.println("20 Highest ROA Stocks:");
		myWriter.writeToFile(roaMax, pw, "max");

		pw.flush();
		pw.close();
	}

}
