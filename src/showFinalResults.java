import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * link class and methods and retrieve results
 */
public class showFinalResults {
	private SimulationAndGetOptimalPortfolio sop = new SimulationAndGetOptimalPortfolio();
	private CalculationMethods mc = new CalculationMethods();

	private String[] stockList = new String[sop.getStockList().size()];
	private double[] weights = new double[sop.getStockList().size()];
	private double portRet;
	private double portStd;
	private double portSharpeRatio;
	private String[] descriptions = {
			"The final constructed optimal portfolio is composed of the following 10 stocks after comprehensively considering Sharpe ratio, return on assets, and leverage ratio:",
			"After 1,000 simulations, the following results were obtained:",
			"The capital weights were assigned to the 10 stocks as follows (the sum is 1):",
			"The optimal portfolio expected annual return is:",
			"The optimal portfolio expected annual standard deviation is:",
			"The optimal portfolio expected annual Sharpe ratio (excess return per unit of investment risk) is:" };

	/**
	 * output the result to an external file
	 * 
	 * @throws FileNotFoundException
	 */
	public showFinalResults() throws FileNotFoundException {

		for (int i = 0; i < stockList.length; i++) {
			stockList[i] = sop.getStockList().get(i);
		}

		weights = sop.getWeights()[sop.getFinalLocation()];
		portRet = sop.getPortRet()[sop.getFinalLocation()];
		portStd = sop.getPortStd()[sop.getFinalLocation()];
		portSharpeRatio = sop.getPortSharpeRatio()[sop.getFinalLocation()];
	}

	/**
	 * this method will write results to a text file
	 * @throws FileNotFoundException
	 */
	public void writeToFile(String filename) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(filename);
		pw.println(descriptions[0]);
		for (String printStr : stockList) {
			pw.println(printStr);
		}
		pw.println(""); // add space
		pw.println(descriptions[1]);
		pw.println(""); // add space
		pw.println(descriptions[2]);
		for (double printThis : weights) {
			pw.println(printThis);
		}
		pw.println(""); // add space
		pw.println(descriptions[3]);
		pw.println(portRet);
		pw.println(""); // add space
		pw.println(descriptions[4]);
		pw.println(portStd);
		pw.println(""); // add space
		pw.println(descriptions[5]);
		pw.println(portSharpeRatio);
		pw.flush();
		pw.close();
	}
	/**
	 * this method will write results to console screen
	 */
	public void writeToConsole() {
		System.out.println(descriptions[0]);
		for (String printStr : stockList) {
			System.out.println(printStr);
		}
		System.out.println(""); // add space
		System.out.println(descriptions[1]);
		System.out.println(""); // add space
		System.out.println(descriptions[2]);
		for (double printThis : weights) {
			System.out.println(printThis);
		}
		System.out.println(""); // add space
		System.out.println(descriptions[3]);
		System.out.println(portRet);
		System.out.println(""); // add space
		System.out.println(descriptions[4]);
		System.out.println(portStd);
		System.out.println(""); // add space
		System.out.println(descriptions[5]);
		System.out.println(portSharpeRatio);
	}

	/**
	 * print out the results
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		showFinalResults result = new showFinalResults();
		result.writeToFile("results.txt");
		result.writeToConsole();
	}
}
