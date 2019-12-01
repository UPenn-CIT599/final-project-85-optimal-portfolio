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
            //skip last line (it's SPX index line)
            if (piecesOfLine[0].equals("SPX Index")) {
                System.out.println("continue reached: line is " + line);
                continue;
            }
            for (int i = 1; i < piecesOfLine.length; i++) {
                if (!piecesOfLine[i].isEmpty()) {
                    counter++;
//                    doubleTemp += Double.parseDouble(piecesOfLine[i].substring(0, piecesOfLine[i].length() - 1));
                    doubleTemp += Double.parseDouble(piecesOfLine[i]);
                }
            }
            topTwenty.put(piecesOfLine[0], doubleTemp / counter);
        }
        sc.close();
        return topTwenty;
    }
}