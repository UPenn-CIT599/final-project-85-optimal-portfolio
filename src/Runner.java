import java.io.FileNotFoundException;

/**
 * class that runs program
 */
public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        ReadAndCalculateROA myROA = new ReadAndCalculateROA();
        myROA.run();
        ReadAndCalculateLeverageRatio myLeverageRatio = new ReadAndCalculateLeverageRatio();
        myLeverageRatio.run();
    }
}
