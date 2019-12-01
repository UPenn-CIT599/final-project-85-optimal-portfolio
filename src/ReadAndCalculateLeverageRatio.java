import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * this class reads and calculates Leverage Ratio stocks
 */
public class ReadAndCalculateLeverageRatio {
    /**
     * @throws FileNotFoundException
     */
    public void run() throws FileNotFoundException {
        Reader topMinReader = new Reader("leverageratio.csv");
        HashMap<String, Double> leverageRatioMin = topMinReader.readCSV("leverage-ratio");
        Writer myWriter = new Writer();

        //open file for writing data
        File out = new File("leverage-ratio-results.txt");
        PrintWriter pw = new PrintWriter(out);

        //write header to file
        pw.println("20 Lowest Leverage Ratio Stocks:");
        myWriter.writeToFile(leverageRatioMin, pw, "min");


        pw.flush();
        pw.close();
    }
}
