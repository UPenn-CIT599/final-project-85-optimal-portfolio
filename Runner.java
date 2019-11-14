import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        RoaReader roaReader = new RoaReader("ROA.csv");
        roaReader.run();
    }
}
