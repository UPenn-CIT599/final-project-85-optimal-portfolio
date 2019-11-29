import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Reader {
    private String filename;

    /**
     * constructor
     *
     * @param filename
     */
    public Reader(String filename) {
        this.filename = filename;
    }

    /**
     * function reads data from CSV file into data structure
     *
     * @return
     * @throws FileNotFoundException
     */
    public HashMap readCSV() throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        HashMap<String, Double> topTwenty = new HashMap<>();
        sc.nextLine(); //skip header row
        //read data
        while (sc.hasNextLine()) {
            double doubleTemp = 0.0;
            int counter = 0;
            String line = sc.nextLine();
            String[] piecesOfLine = line.split(",");
            for (int i = 2; i < piecesOfLine.length; i++) {
                if (!piecesOfLine[i].isEmpty()) {
                    counter++;
                    doubleTemp += Double.parseDouble(piecesOfLine[i].substring(0, piecesOfLine[i].length() - 1));
                }
            }
            topTwenty.put(piecesOfLine[1], doubleTemp / counter);
        }
        sc.close();
        return topTwenty;
    }
}