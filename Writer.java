import java.io.PrintWriter;
import java.util.HashMap;

/**
 * class to write to a file
 */
public class Writer {
    HelperMethods myHelpMethods = new HelperMethods();

    /**
     * function to write stocks to a file
     *
     * @param myHash
     * @param myFileWriter
     * @param minOrMax
     */
    public void writeToFile(HashMap<String, Double> myHash, PrintWriter myFileWriter, String minOrMax) {
        String[] sTempMax = new String[20];
        String myKey = "";
        for (int i = 0; i < 20; i++) {
            if (minOrMax.equals("max")) {
                myKey = myHelpMethods.findKeyWithMaxValue(myHash);
            } else if (minOrMax.equals("min")) {
                myKey = myHelpMethods.findKeyWithMinValue(myHash);
            }
            sTempMax[i] = "" + myHash.remove(myKey);
            System.out.println(sTempMax[i]);
            myFileWriter.println(myKey);
        }
    }
}
