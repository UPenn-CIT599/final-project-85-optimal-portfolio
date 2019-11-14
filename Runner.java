import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        //will read either ROA.csv or leverageratio.csv
        RoaReader roaReader = new RoaReader("ROA.csv");
        roaReader.run();
    }
}
