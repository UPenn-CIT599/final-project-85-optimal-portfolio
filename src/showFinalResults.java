import java.io.FileWriter;
import java.io.IOException;

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
	 */
	public showFinalResults() {

		for(int i = 0; i < stockList.length; i++) {
			stockList[i] = sop.getStockList().get(i);
		}
		
		weights = sop.getWeights()[sop.getFinalLocation()];
		portRet = sop.getPortRet()[sop.getFinalLocation()];
		portStd = sop.getPortStd()[sop.getFinalLocation()];
		portSharpeRatio = sop.getPortSharpeRatio()[sop.getFinalLocation()];
		
		String filename = "results.txt";

		try {
			FileWriter fw = new FileWriter(filename);

			String fileContents1 = "The final constructed optimal portfolio is composed of the follwoing 10 stocks after comprehensively consideing Sharpe ratio, return on assets, and leverage ratio:";
			fw.write(fileContents1);

			String fileContents2 = "";
			fw.write(fileContents2);

			String fileContents3 = stockList.toString();
			fw.write(fileContents3);
			
			String fileContents4 = "";
			fw.write(fileContents4);
			
			String fileContents5 = "We simulated 1,000 times. After that the results are described as follows.";
			fw.write(fileContents5);
			
			String fileContents6 = "";
			fw.write(fileContents6);
			
			String fileContents7 = "The capital weights assigned for the 10 stocks are shown as follows (the capital weights sum is one):";
			fw.write(fileContents7);
			
			String fileContents8 = "";
			fw.write(fileContents8);
			
			String fileContents9 = weights.toString();
			fw.write(fileContents9);
			
			String fileContents10 = "";
			fw.write(fileContents10);
			
			String fileContents11 = "The optimal portfolio expeccted annual return is:";
			fw.write(fileContents11);
			
			String fileContents12 = Double.toString(portRet);
			fw.write(fileContents12);
			
			String fileContents13 = "";
			fw.write(fileContents13);
			
			String fileContents14 = "The optimal portfolio expeccted annual standard deviation is:";
			fw.write(fileContents14);
			
			String fileContents15 = Double.toString(portStd);
			fw.write(fileContents15);
			
			String fileContents16 = "";
			fw.write(fileContents16);
			
			String fileContents17 = "The optimal portfolio expected annual Sharpe ratio (this means excess return per unit of investment risk) is:";
			fw.write(fileContents17);
			
			String fileContents18 = Double.toString(portSharpeRatio);
			fw.write(fileContents18);

			fw.close();
		}
		catch(IOException e) {
			// Do nothing
		}
	}

	/**
	 * print out the results
	 * @param args
	 */
	public static void main(String[] args) {

		showFinalResults result = new showFinalResults();

		System.out.println("The final constructed optimal portfolio is composed of the follwoing 10 stocks after comprehensively consideing Sharpe ratio, return on assets, and leverage ratio:");

		System.out.println();

		System.out.println(result.sop.getStockList());

		System.out.println();

		System.out.println("We simulated 1,000 times. After that the results are described as follows.");

		System.out.println();

		System.out.println("The capital weights assigned for the 10 stocks are shown as follows (the capital weights sum is one):");

		System.out.println();

		result.mc.printArray(result.sop.getWeights()[result.sop.getFinalLocation()]);

		System.out.println();

		System.out.println("The optimal portfolio expeccted annual return is: " + result.sop.getPortRet()[result.sop.getFinalLocation()]);

		System.out.println();

		System.out.println("The optimal portfolio expeccted annual standard deviation is: " + result.sop.getPortStd()[result.sop.getFinalLocation()]);

		System.out.println();

		System.out.println("The optimal portfolio expeccted annual Sharpe ratio (this means excess return per unit of investment risk) is: " + result.sop.getPortSharpeRatio()[result.sop.getFinalLocation()]);
	}

}
