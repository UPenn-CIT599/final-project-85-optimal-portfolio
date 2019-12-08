import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * write results to a file
 *
 */
public class showFinalResults {

	/**
	 * link class and methods and retrieve results
	 */
	SimulationAndGetOptimalPortfolio sop = new SimulationAndGetOptimalPortfolio();
	CalculationMethods mc = new CalculationMethods();

	String[] stockList = new String[sop.getStockList().size()];
	double[] weights = new double[sop.getStockList().size()];
	double portRet;
	double portStd;
	double portSharpeRatio;

	/**
	 * output the result to an external file
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

		String filename = "results.txt";

		PrintWriter pw = new PrintWriter(filename);

		String fileContents1 = "The final constructed optimal portfolio is composed of the follwoing 10 stocks after comprehensively considering Sharpe ratio, return on assets, and leverage ratio:";
		pw.println(fileContents1);

		String fileContents2 = "";
		pw.println(fileContents2);

		String fileContents3 = stockList.toString();
		pw.println(fileContents3);

		String fileContents4 = "";
		pw.println(fileContents4);

		String fileContents5 = "We simulated 1,000 times. After that the results are described as follows.";
		pw.println(fileContents5);

		String fileContents6 = "";
		pw.println(fileContents6);

		String fileContents7 = "The capital weights assigned for the 10 stocks are shown as follows (the capital weights sum is one):";
		pw.println(fileContents7);

		String fileContents8 = "";
		pw.println(fileContents8);

		String fileContents9 = weights.toString();
		pw.println(fileContents9);

		String fileContents10 = "";
		pw.println(fileContents10);

		String fileContents11 = "The optimal portfolio expeccted annual return is:";
		pw.println(fileContents11);

		String fileContents12 = Double.toString(portRet);
		pw.println(fileContents12);

		String fileContents13 = "";
		pw.println(fileContents13);

		String fileContents14 = "The optimal portfolio expeccted annual standard deviation is:";
		pw.println(fileContents14);

		String fileContents15 = Double.toString(portStd);
		pw.println(fileContents15);

		String fileContents16 = "";
		pw.println(fileContents16);

		String fileContents17 = "The optimal portfolio expected annual Sharpe ratio (this means excess return per unit of investment risk) is:";
		pw.println(fileContents17);

		String fileContents18 = Double.toString(portSharpeRatio);
		pw.println(fileContents18);

		pw.flush();
		pw.close();
	}

	/**
	 * print out the results
	 *
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		showFinalResults result = new showFinalResults();

		System.out.println(
				"The final constructed optimal portfolio is composed of the follwoing 10 stocks after comprehensively consideing Sharpe ratio, return on assets, and leverage ratio:");

		System.out.println();

		System.out.println(result.sop.getStockList());

		System.out.println();

		System.out.println("We simulated 1,000 times. After that the results are described as follows.");

		System.out.println();

		System.out.println(
				"The capital weights assigned for the 10 stocks are shown as follows (the capital weights sum is one):");

		System.out.println();

		result.mc.printArray(result.sop.getWeights()[result.sop.getFinalLocation()]);

		System.out.println();

		System.out.println("The optimal portfolio expeccted annual return is: "
				+ result.sop.getPortRet()[result.sop.getFinalLocation()]);

		System.out.println();

		System.out.println("The optimal portfolio expeccted annual standard deviation is: "
				+ result.sop.getPortStd()[result.sop.getFinalLocation()]);

		System.out.println();

		System.out.println(
				"The optimal portfolio expeccted annual Sharpe ratio (this means excess return per unit of investment risk) is: "
						+ result.sop.getPortSharpeRatio()[result.sop.getFinalLocation()]);
	}

}
