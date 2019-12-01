import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * general reader class
 */
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

    static class DescOrder implements Comparator<Double> {

        @Override
        public int compare(Double o1, Double o2) {
            return o2.compareTo(o1);
        }
    }

    /**
     * function reads data from CSV file into data structure
     *
     * @return
     * @throws FileNotFoundException
     */
    public HashMap readCSV(String flag) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        HashMap<String, Double> averageForEachStock = new HashMap<>();
        //leverageRatioSet with TreeMap
        TreeMap<Double, String> leverageRatioSet = new TreeMap<>();
        TreeMap<Double, String> ROASet = new TreeMap<>(new DescOrder());
        sc.nextLine(); //skip header row
        //read data
        while (sc.hasNextLine()) {
            double doubleTemp = 0.0;
            int counter = 0;
            String line = sc.nextLine();
            String[] piecesOfLine = line.split(",");
            for (int i = 1; i < piecesOfLine.length; i++) {
                if (!piecesOfLine[i].isEmpty()) {
                    counter++;
                    doubleTemp += Double.parseDouble(piecesOfLine[i]);
                }
            }
            BigDecimal bd = new BigDecimal(Double.toString(doubleTemp/counter));
            bd = bd.setScale(4, RoundingMode.HALF_UP);
            double roundedResult = bd.doubleValue();
            averageForEachStock.put(piecesOfLine[0], roundedResult);
            leverageRatioSet.put(roundedResult, piecesOfLine[0]);
            ROASet.put(roundedResult, piecesOfLine[0]);
        }
        //get "SPX Index" value
        Double spxIndexAverage = averageForEachStock.get("SPX Index");
        //initialize set to loop through by keys
        Set<String> loopSet = averageForEachStock.keySet();
        //will hold all keys that needs to be removed
        ArrayList<String> keysToBeRemoved = new ArrayList<>();
        //loop through HashMap
        for (String key : loopSet) {
            double currentValue = averageForEachStock.get(key);
            if (flag.equals("roa")) {
                if (spxIndexAverage > currentValue) {
                    System.out.println("roa: " + spxIndexAverage + " > " + currentValue);
                    //build array of strings with keys to be removed
                    keysToBeRemoved.add(key);
                }
            } else if (flag.equals("leverage-ratio")) {
                if (spxIndexAverage < currentValue) {
                    System.out.println("leverage-ratio: " + spxIndexAverage + " < " + currentValue);
                    //build array of strings with keys to be removed
                    keysToBeRemoved.add(key);
                }
            }
        }
        //remove all flagged keys
        for (int i = 0; i < keysToBeRemoved.size(); i++) {
            System.out.println("KEYS ARE BEING REMOVED");
            ROASet.remove(averageForEachStock.get(keysToBeRemoved.get(i)));
            leverageRatioSet.remove(averageForEachStock.get(keysToBeRemoved.get(i)));
            averageForEachStock.remove(keysToBeRemoved.get(i));
        }
        sc.close();
        System.out.println("leverage: " + leverageRatioSet);
        int ii = 1;
        for (Map.Entry<Double, String> a : leverageRatioSet.entrySet()) {
            System.out.println(ii + " KEY->" + a.getKey() + " VALUE->" + a.getValue());
            if (ii == 20) {
                break;
            }
            ii++;
        }
        System.out.println("ROA: " + ROASet);
        int jj = 1;
        for (Map.Entry<Double, String> a : ROASet.entrySet()) {
            System.out.println(jj + " KEY->" + a.getKey() + " VALUE->" + a.getValue());
            if (jj == 20) {
                break;
            }
            jj++;
        }
        return averageForEachStock;
    }
}