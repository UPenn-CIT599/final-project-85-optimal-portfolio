import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * main class that works with stocks
 */
public class ROAandLeverageRatioReader {
    /**
     * @throws FileNotFoundException
     */
    public void run() throws FileNotFoundException {
        HelperMethods helpMethods = new HelperMethods();
        Reader topMaxReader = new Reader("ROA.csv");
        HashMap<String, Double> topMax20 = topMaxReader.readCSV();
        Reader topMinReader = new Reader("leverage_ratio.csv");
        HashMap<String, Double> topMin20 = topMinReader.readCSV();
        Writer myWriter = new Writer();

        //open file for writing data
        File out = new File("results.txt");
        PrintWriter pw = new PrintWriter(out);

        //write header to file
        pw.println("20 Highest ROA Stocks:");
        myWriter.writeToFile(topMax20, pw, "max");

        pw.println("\n20 Lowest Leverage Ratio Stocks:");
        myWriter.writeToFile(topMin20, pw, "min");

        pw.flush();
        pw.close();
    }
}
