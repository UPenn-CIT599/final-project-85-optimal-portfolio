import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        ROAandLeverageRatioReader currentReader = new ROAandLeverageRatioReader("ROA.csv");
        currentReader.run();
    }
}
