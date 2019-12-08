import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class ROAAnalysisTest {
	/**
	 * link class and retrieve results
	 */
	private ArrayList<Double> average = new ArrayList<Double>();

	/**
	 * input the correct results
	 */
	public ROAAnalysisTest() {

		average.add(0.1962);
		average.add(0.1647);
		average.add(0.0971);
		average.add(0.131);
		average.add(0.046);
		average.add(0.0794);
		average.add(0.1206);
		average.add(-0.0573);
		average.add(0.0906);
		average.add(0.0678);
		average.add(0.132);
		average.add(0.1095);
		average.add(0.0273);
		average.add(0.1087);
		average.add(0.0078);
		average.add(0.1749);
		average.add(0.084);
		average.add(0.0613);
		average.add(0.0485);
		average.add(0.0418);
		average.add(0.0743);
		average.add(0.0603);
		average.add(0.0351);
		average.add(0.0721);
		average.add(0.1241);
		average.add(0.1632);
		average.add(0.1535);
		average.add(0.1252);
		average.add(0.1296);
		average.add(0.0641);
		average.add(0.1254);
		average.add(0.0896);
		average.add(0.0822);
		average.add(0.1754);
		average.add(0.0546);
		average.add(0.11);
		average.add(0.0702);
		average.add(0.1161);
		average.add(0.1056);
		average.add(0.1334);
		average.add(0.0252);
		average.add(0.06);
		average.add(0.1344);
		average.add(0.1551);
		average.add(-0.0139);
		average.add(0.1934);
		average.add(0.1221);
		average.add(0.1311);
		average.add(0.0395);
		average.add(0.1372);
		average.add(0.1522);
		average.add(0.0268);

	}

	@Test
	void test() {

		ROAAnalysis roa = new ROAAnalysis();
		ArrayList<Double> a = roa.getAverageROA();

		for (int i = 0; i < a.size(); i++) {
			assertEquals(average.get(i), a.get(i));
		}
	}
}
