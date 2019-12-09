import java.util.*;

public class SimulationAndGetOptimalPortfolio {

	/**
	 * link class and methods and retrieve results from previous class
	 */
	CalculationMethods cm = new CalculationMethods();
	SharpeRatioAnalysis sr = new SharpeRatioAnalysis();
	selectStock ss = new selectStock();

	ArrayList<Double> rfData = sr.getRfData();
	ArrayList<String> stockList = ss.getSelectedStock();
	HashMap<String, ArrayList<Double>> returns = ss.getChosenStockWithMonthlyReturns();

	/**
	 * get all the historical returns data of the chosen top 10 stocks
	 * calculate and get the covariance of the top 10 stocks for portfolio standard deviation calculation
	 * calculate and get the risk-free return to calculate portfolio Sharpe ratio
	 */
	double[][] returnsData = cm.getReturnsData(stockList, returns);
	double[][] covarianceMatrix = cm.calculateCovriance(returnsData);
	double rf = cm.calculateAnnRet(rfData);

	/**
	 * set simulation times and the length of randoom weights generated
	 */
	int simulationTimes1 = 1000;
	int simulationTimes2 = 1000;
	int randowNumberLength = stockList.size();

	/**
	 * set variables for analysis and comparison
	 */
	double[] annualRet = new double[randowNumberLength];
	double[][] weights = new double[simulationTimes1][randowNumberLength];
	double[] portRet = new double[simulationTimes1];
	double[] portStd = new double[simulationTimes1];
	double[] portSharpeRatio = new double[simulationTimes1];
	double[] sortedPortSharpeRatio = new double[simulationTimes1];
	int[] location = new int[simulationTimes1];
	int finalLocation;

	double[][] weightsRecord = new double[simulationTimes2][randowNumberLength];
	double[][] transformedWeightsRecord = new double[weightsRecord[1].length][weightsRecord.length];
	double[] portRetRecord = new double[simulationTimes2];
	double[] portStdRecord = new double[simulationTimes2];
	double[] portSharpeRatioRecord = new double[simulationTimes2];

	double[] finalWeights = new double[randowNumberLength];
	double finalPortRet;
	double finalPortStd;
	double finalPortSharpeRatio;

	/**
	 * calculate portfolio return, standard deviation, and Sharpe ratio under simulation scenarios
	 * generate the sorted Sharpe ratio from the highest to lowest, and find each relevant location
	 * according to the relevant simulation scenario
	 */
	public SimulationAndGetOptimalPortfolio() {

		for(int i = 0; i < returnsData.length; i++) {
			annualRet[i] = cm.calculateAnnRet(returnsData[i]);
		}

		for(int k = 0; k < simulationTimes2; k++) {

			for(int i = 0; i < simulationTimes1; i++) {
				weights[i] = cm.generateRandomNumber(randowNumberLength);
				portRet[i] = cm.calculatePortRet(weights[i], annualRet);
				portStd[i] = cm.calculatePortStd(weights[i], covarianceMatrix);
				portSharpeRatio[i] = (portRet[i] - rf) / portStd[i];
				sortedPortSharpeRatio[i] = portSharpeRatio[i];
			}

			Arrays.sort(sortedPortSharpeRatio);
			cm.newSort(sortedPortSharpeRatio);

			for(int i = 0; i < simulationTimes1; i++) {
				for(int j = 0; j < simulationTimes1; j++) {
					if(sortedPortSharpeRatio[i] == portSharpeRatio[j]) {
						location[i] = j;
					}
				}
			}

			finalLocation = location[0];
			
			weightsRecord[k] = weights[finalLocation];
			portRetRecord[k] = portRet[finalLocation];
			portStdRecord[k] = portStd[finalLocation];
			portSharpeRatioRecord[k] = portSharpeRatio[finalLocation];

		}
		
		transformedWeightsRecord = cm.transformMatrix(weightsRecord);
		for(int i = 0; i < transformedWeightsRecord.length; i++) {
			finalWeights[i] = cm.calculateAverage(transformedWeightsRecord[i]);
		}

		finalPortRet = cm.calculateAverage(portRetRecord);
		finalPortStd = cm.calculateAverage(portStdRecord);
		finalPortSharpeRatio = cm.calculateAverage(portSharpeRatioRecord);
	}

	/**
	 * output the results for verification
	 * @param args
	 */
	public static void main(String[] args) {

		SimulationAndGetOptimalPortfolio sop = new SimulationAndGetOptimalPortfolio();

		System.out.println("Following matrix shows the chosen stocks returns mnatrix:");
		sop.cm.printBoard(sop.getReturnsData());
		System.out.println();

		System.out.println("Following matrix shows the covariance of chosen stocks returns:");
		sop.cm.printBoard(sop.getCovarianceMatrix());
		System.out.println();

		System.out.println("Following data list shows the portfolio expected annual return under each simulation scenarios:");
		sop.cm.printArray(sop.getPortRet());
		System.out.println();

		System.out.println("Following data list shows the portfolio expected annual standard deviation under each simulation scenarios:");
		sop.cm.printArray(sop.getPortStd());
		System.out.println();

		System.out.println("Following data list shows the portfolio expected Sharpe ratio under each simulation scenarios:");
		sop.cm.printArray(sop.getPortSharpeRatio());
		System.out.println();

		System.out.println("Following data list shows the portfolio sorted expected Sharpe ratio from highest to lowest:");
		sop.cm.printArray(sop.getSortedPortSharpeRatio());
		System.out.println();

	}

	public double[][] getWeights() {
		return weights;
	}

	public double[] getPortRet() {
		return portRet;
	}

	public double[] getPortStd() {
		return portStd;
	}

	public double[] getPortSharpeRatio() {
		return portSharpeRatio;
	}

	public double[][] getReturnsData() {
		return returnsData;
	}

	public double[][] getCovarianceMatrix() {
		return covarianceMatrix;
	}

	public ArrayList<Double> getRfData() {
		return rfData;
	}

	public double getRf() {
		return rf;
	}

	public double[] getAnnualRet() {
		return annualRet;
	}

	public double[] getSortedPortSharpeRatio() {
		return sortedPortSharpeRatio;
	}

	public int[] getLocation() {
		return location;
	}

	public int getFinalLocation() {
		return finalLocation;
	}

	public ArrayList<String> getStockList() {
		return stockList;
	}

	public int getRandowNumberLength() {
		return randowNumberLength;
	}

	public HashMap<String, ArrayList<Double>> getReturns() {
		return returns;
	}

	public int getSimulationTimes1() {
		return simulationTimes1;
	}

	public int getSimulationTimes2() {
		return simulationTimes2;
	}

	public double[][] getWeightsRecord() {
		return weightsRecord;
	}

	public double[] getPortRetRecord() {
		return portRetRecord;
	}

	public double[] getPortStdRecord() {
		return portStdRecord;
	}

	public double[] getPortSharpeRatioRecord() {
		return portSharpeRatioRecord;
	}

	public double[] getFinalWeights() {
		return finalWeights;
	}

	public double getFinalPortRet() {
		return finalPortRet;
	}

	public double getFinalPortStd() {
		return finalPortStd;
	}

	public double getFinalPortSharpeRatio() {
		return finalPortSharpeRatio;
	}
	
}

