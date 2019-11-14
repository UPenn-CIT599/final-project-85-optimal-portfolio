import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class RoaReader {
    private String filename;

    public RoaReader(String filename) {
        this.filename = filename;
    }

    public void run() throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        HashMap<String , Double> topTwenty = new HashMap<>();
        sc.nextLine(); //skip header row
        //read data
        while (sc.hasNextLine()) {
            double doubleTemp = 0.0;
            int counter = 0;
            String line = sc.nextLine();
            System.out.println(line);
            String[] piecesOfLine = line.split(",");
            for (int i = 2; i < piecesOfLine.length; i++) {
                if (!piecesOfLine[i].isEmpty()) {
                    counter++;
                    doubleTemp += Double.parseDouble(piecesOfLine[i].substring(0,piecesOfLine[i].length()-1));
//                    System.out.println(Double.parseDouble(piecesOfLine[i].substring(0,piecesOfLine[i].length()-1)));
//                    System.out.println(doubleTemp);

                }
            }
//            System.out.println(doubleTemp/counter);
            topTwenty.put(piecesOfLine[1], doubleTemp/counter);
            System.out.println(topTwenty);
        }
    }
}
