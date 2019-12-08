import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * test class for Leverage Ratio
 */
class LeverageRatioAnalysisTest {

	private ArrayList<Double> average = new ArrayList<Double>();

	/**
	 * input the correct results
	 */
	public LeverageRatioAnalysisTest() {

		average.add(0.1199);
		average.add(9.0E-4);
		average.add(0.1357);
		average.add(0.1625);
		average.add(0.1543);
		average.add(0.1123);
		average.add(0.1447);
		average.add(0.4814);
		average.add(0.0412);
		average.add(0.1428);
		average.add(0.2583);
		average.add(0.2766);
		average.add(0.2338);
		average.add(0.0775);
		average.add(0.1145);
		average.add(0.0306);
		average.add(0.1264);
		average.add(0.2582);
		average.add(0.0733);
		average.add(0.3646);
		average.add(0.4252);
		average.add(8.0E-4);
		average.add(0.1882);
		average.add(0.1889);
		average.add(0.2901);
		average.add(0.162);
		average.add(0.0538);
		average.add(0.0542);
		average.add(0.2921);
		average.add(0.2896);
		average.add(0.1775);
		average.add(0.3028);
		average.add(0.4518);
		average.add(0.1668);
		average.add(0.2246);
		average.add(0.1517);
		average.add(0.1676);
		average.add(0.3106);
		average.add(0.0073);
		average.add(0.1229);
		average.add(0.1133);
		average.add(0.0299);
		average.add(0.376);
		average.add(0.0461);
		average.add(0.1295);
		average.add(0.1763);
		average.add(0.1969);
		average.add(0.2641);
		average.add(0.3242);
		average.add(0.1319);
		average.add(0.0964);
		average.add(0.2756);

	}

	@Test
	void test() {

		LeverageRatioAnalysis lr = new LeverageRatioAnalysis();
		ArrayList<Double> a = lr.getAverageLeverageRatio();

		for (int i = 0; i < a.size(); i++) {
			assertEquals(average.get(i), a.get(i));
		}
	}
}
