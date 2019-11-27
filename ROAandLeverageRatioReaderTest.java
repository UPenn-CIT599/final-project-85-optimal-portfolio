import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class ROAandLeverageRatioReaderTest {

	private ArrayList<String> resultsToBeChecked = new ArrayList<String>();

	public ROAandLeverageRatioReaderTest() {
		File inputFile = new File("results.txt");
		try {
			Scanner in = new Scanner(inputFile);
			while (in.hasNextLine()) {
				resultsToBeChecked.add(in.nextLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void test() {
		assertEquals(19.616666666666674, Double.parseDouble(resultsToBeChecked.get(0)), "wrong. should be 19.616666666666674");
		assertEquals(19.340196078431372, Double.parseDouble(resultsToBeChecked.get(1)), "wrong. should be 19.340196078431372");
		assertEquals(17.544705882352943, Double.parseDouble(resultsToBeChecked.get(2)), "wrong. should be 17.544705882352943");
		assertEquals(17.4921568627451, Double.parseDouble(resultsToBeChecked.get(3)), "wrong. should be 17.4921568627451");
		assertEquals(16.46549019607843, Double.parseDouble(resultsToBeChecked.get(4)), "wrong. should be 16.46549019607843");
		assertEquals(16.319215686274507, Double.parseDouble(resultsToBeChecked.get(5)), "wrong. should be 16.319215686274507");
		assertEquals(15.506999999999996, Double.parseDouble(resultsToBeChecked.get(6)), "wrong. should be 15.506999999999996");
		assertEquals(15.349361702127661, Double.parseDouble(resultsToBeChecked.get(7)), "wrong. should be 15.349361702127661");
		assertEquals(15.221333333333328, Double.parseDouble(resultsToBeChecked.get(8)), "wrong. should be 15.221333333333328");
		assertEquals(13.720000000000002, Double.parseDouble(resultsToBeChecked.get(9)), "wrong. should be 13.720000000000002");
		assertEquals(13.436078431372547, Double.parseDouble(resultsToBeChecked.get(10)), "wrong. should be 13.436078431372547");
		assertEquals(13.343137254901965, Double.parseDouble(resultsToBeChecked.get(11)), "wrong. should be 13.343137254901965");
		assertEquals(13.198181818181816, Double.parseDouble(resultsToBeChecked.get(12)), "wrong. should be 13.198181818181816");
		assertEquals(13.106078431372547, Double.parseDouble(resultsToBeChecked.get(13)), "wrong. should be 13.106078431372547");
		assertEquals(13.102941176470594, Double.parseDouble(resultsToBeChecked.get(14)), "wrong. should be 13.102941176470594");
		assertEquals(12.956862745098036, Double.parseDouble(resultsToBeChecked.get(15)), "wrong. should be 12.956862745098036");
		assertEquals(12.538431372549017, Double.parseDouble(resultsToBeChecked.get(16)), "wrong. should be 12.538431372549017");
		assertEquals(12.520000000000001, Double.parseDouble(resultsToBeChecked.get(17)), "wrong. should be 12.520000000000001");
		assertEquals(12.4078431372549, Double.parseDouble(resultsToBeChecked.get(18)), "wrong. should be 12.4078431372549");
		assertEquals(12.21392156862745, Double.parseDouble(resultsToBeChecked.get(19)), "wrong. should be 12.21392156862745");
	}

}
