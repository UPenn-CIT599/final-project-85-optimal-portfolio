import java.io.FileNotFoundException;

/**
 * class that runs program
 */
public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        ROAandLeverageRatioReader currentReader = new ROAandLeverageRatioReader();
        currentReader.run();
    }
}
