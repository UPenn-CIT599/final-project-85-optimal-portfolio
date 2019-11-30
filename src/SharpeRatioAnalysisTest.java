import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class SharpeRatioAnalysisTest {
	
	private ArrayList<Double> annRet = new ArrayList<Double>();
	private ArrayList<Double> annStd = new ArrayList<Double>();
	private ArrayList<Double> annSR = new ArrayList<Double>();
	private HashMap<String, Double> chosenStockWithSR = new HashMap<String, Double>();
	
	
	public SharpeRatioAnalysisTest() {
		
		annRet.add(0.279); annRet.add(0.1559); annRet.add(0.1606); annRet.add(0.1253); annRet.add(0.1059);
		annRet.add(0.0387); annRet.add(0.1088); annRet.add(0.0407); annRet.add(0.1977); annRet.add(0.1663);
		annRet.add(0.3372); annRet.add(0.1832); annRet.add(0.1062); annRet.add(0.1488); annRet.add(0.248);
		annRet.add(0.096); annRet.add(0.1358); annRet.add(-0.2497); annRet.add(0.0519); annRet.add(0.163);
		annRet.add(0.177); annRet.add(0.2562); annRet.add(0.2007); annRet.add(0.0143); annRet.add(0.0523);
		annRet.add(0.1887); annRet.add(0.1445); annRet.add(0.1743); annRet.add(0.148); annRet.add(0.0943);
		annRet.add(0.1475); annRet.add(0.1238); annRet.add(0.2028); annRet.add(0.156); annRet.add(0.1002);
		annRet.add(0.0895); annRet.add(0.0389); annRet.add(0.1058); annRet.add(0.0993); annRet.add(0.0862);
		annRet.add(0.0192); annRet.add(0.135); annRet.add(0.1045); annRet.add(0.2275); annRet.add(0.1608);
		annRet.add(0.1421); annRet.add(0.0914); annRet.add(0.136); annRet.add(0.0019); annRet.add(0.1161);
		annRet.add(0.1851); annRet.add(0.0838); annRet.add(0.0089);
		
		annStd.add(0.309); annStd.add(0.1979); annStd.add(0.279); annStd.add(0.2518); annStd.add(0.3848);
		annStd.add(0.4099); annStd.add(0.3079); annStd.add(0.6119); annStd.add(0.2714); annStd.add(0.3087);
		annStd.add(0.2834); annStd.add(0.2428); annStd.add(0.3153); annStd.add(0.2559); annStd.add(0.3342);
		annStd.add(0.2808); annStd.add(0.3078); annStd.add(0.4214); annStd.add(0.3481); annStd.add(0.2105);
		annStd.add(0.2031); annStd.add(0.3554); annStd.add(0.2963); annStd.add(0.2925); annStd.add(0.2019);
		annStd.add(0.219); annStd.add(0.48); annStd.add(0.1812); annStd.add(0.338); annStd.add(0.2469);
		annStd.add(0.3129); annStd.add(0.263); annStd.add(0.3023); annStd.add(0.2333); annStd.add(0.4809);
		annStd.add(0.2768); annStd.add(0.3555); annStd.add(0.2273); annStd.add(0.1844); annStd.add(0.3191);
		annStd.add(0.4141); annStd.add(0.1992); annStd.add(0.4943); annStd.add(0.402); annStd.add(0.42);
		annStd.add(0.2344); annStd.add(0.4189); annStd.add(0.2617); annStd.add(0.3483); annStd.add(0.2392);
		annStd.add(0.4647); annStd.add(0.1461); annStd.add(0.0037);
		
		annSR.add(0.8741); annSR.add(0.7428); annSR.add(0.5437); annSR.add(0.4623); annSR.add(0.2521);
		annSR.add(0.0727); annSR.add(0.3245); annSR.add(0.052); annSR.add(0.6957); annSR.add(0.5099);
		annSR.add(1.1584); annSR.add(0.7179); annSR.add(0.3086); annSR.add(0.5467); annSR.add(0.7154);
		annSR.add(0.3102); annSR.add(0.4123); annSR.add(-0.6137); annSR.add(0.1235); annSR.add(0.7321);
		annSR.add(0.8277); annSR.add(0.6958); annSR.add(0.6473); annSR.add(0.0185); annSR.add(0.215);
		annSR.add(0.821); annSR.add(0.2825); annSR.add(0.9128); annSR.add(0.4115); annSR.add(0.3459);
		annSR.add(0.443); annSR.add(0.4369); annSR.add(0.6414); annSR.add(0.6305); annSR.add(0.1899);
		annSR.add(0.2912); annSR.add(0.0844); annSR.add(0.4263); annSR.add(0.4902); annSR.add(0.2422);
		annSR.add(0.0249); annSR.add(0.633); annSR.add(0.1934); annSR.add(0.5438); annSR.add(0.3617);
		annSR.add(0.5683); annSR.add(0.1969); annSR.add(0.4857); annSR.add(-0.0201); annSR.add(0.4482);
		annSR.add(0.3792); annSR.add(0.5127);
		
		chosenStockWithSR.put("AVGO US Equity", 1.1584);
		chosenStockWithSR.put("JKHY US Equity", 0.9128);
		chosenStockWithSR.put("AAPL US Equity", 0.8741);
		chosenStockWithSR.put("FISV US Equity", 0.8277);
		chosenStockWithSR.put("INTU US Equity", 0.821);
		chosenStockWithSR.put("ACN US Equity", 0.7428);
		chosenStockWithSR.put("FIS US Equity", 0.7321);
		chosenStockWithSR.put("BR US Equity", 0.7179);
		chosenStockWithSR.put("CRM US Equity", 0.7154);
		chosenStockWithSR.put("FTNT US Equity", 0.6958);
		chosenStockWithSR.put("ANSS US Equity", 0.6957);
		chosenStockWithSR.put("HPE US Equity", 0.6473);
		chosenStockWithSR.put("MSCI US Equity", 0.6414);
		chosenStockWithSR.put("SNPS US Equity", 0.633);
		chosenStockWithSR.put("MSFT US Equity", 0.6305);
		chosenStockWithSR.put("TXN US Equity", 0.5683);
		chosenStockWithSR.put("CERN US Equity", 0.5467);
		chosenStockWithSR.put("SWKS US Equity", 0.5438);
		chosenStockWithSR.put("ADBE US Equity", 0.5437);
	}

	@Test
	void testMain() {
		
		SharpeRatioAnalysis sra = new SharpeRatioAnalysis();
		ArrayList<Double> a = sra.getAnnualRet();
		ArrayList<Double> b = sra.getAnnualStd();
		ArrayList<Double> c = sra.getAnnualSR();
		HashMap<String, Double> d = sra.getChosenStockWithSR();
		
		for (int i = 0; i < a.size(); i++) {
			assertEquals(annRet.get(i), a.get(i));
			assertEquals(annStd.get(i), b.get(i));
		}
		
		for (int i = 0; i < c.size(); i++) {
			assertEquals(annSR.get(i), c.get(i));
		}
		
		for (String s : chosenStockWithSR.keySet()) {
			assertEquals(chosenStockWithSR.get(s), d.get(s));
		}
	}

}
