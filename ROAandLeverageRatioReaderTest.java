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
        assertEquals("20 Highest ROA Stocks:", resultsToBeChecked.get(0), "wrong");
        assertEquals("AAPL US Equity", resultsToBeChecked.get(1), "wrong");
        assertEquals("TXN US Equity", resultsToBeChecked.get(2), "wrong");
        assertEquals("MSFT US Equity", resultsToBeChecked.get(3), "wrong");
        assertEquals("CTSH US Equity", resultsToBeChecked.get(4), "wrong");
        assertEquals("ACN US Equity", resultsToBeChecked.get(5), "wrong");
        assertEquals("INTU US Equity", resultsToBeChecked.get(6), "wrong");
        assertEquals("SWKS US Equity", resultsToBeChecked.get(7), "wrong");
        assertEquals("IPGP US Equity", resultsToBeChecked.get(8), "wrong");
        assertEquals("NVDA US Equity", resultsToBeChecked.get(9), "wrong");
        assertEquals("INTC US Equity", resultsToBeChecked.get(10), "wrong");
        assertEquals("STX US Equity", resultsToBeChecked.get(11), "wrong");
        assertEquals("QCOM US Equity", resultsToBeChecked.get(12), "wrong");
        assertEquals("AVGO US Equity", resultsToBeChecked.get(13), "wrong");
        assertEquals("XLNX US Equity", resultsToBeChecked.get(14), "wrong");
        assertEquals("ADI US Equity", resultsToBeChecked.get(15), "wrong");
        assertEquals("KLAC US Equity", resultsToBeChecked.get(16), "wrong");
        assertEquals("LRCX US Equity", resultsToBeChecked.get(17), "wrong");
        assertEquals("JKHY US Equity", resultsToBeChecked.get(18), "wrong");
        assertEquals("IBM US Equity", resultsToBeChecked.get(19), "wrong");
        assertEquals("WDC US Equity", resultsToBeChecked.get(20), "wrong");
        assertEquals("", resultsToBeChecked.get(21), "wrong");

        assertEquals("20 Lowest Leverage Ratio Stocks:", resultsToBeChecked.get(22), "wrong");
        assertEquals("FTNT US Equity", resultsToBeChecked.get(23), "wrong");
        assertEquals("ACN US Equity", resultsToBeChecked.get(24), "wrong");
        assertEquals("PAYX US Equity", resultsToBeChecked.get(25), "wrong");
        assertEquals("SNPS US Equity", resultsToBeChecked.get(26), "wrong");
        assertEquals("CTSH US Equity", resultsToBeChecked.get(27), "wrong");
        assertEquals("ANSS US Equity", resultsToBeChecked.get(28), "wrong");
        assertEquals("SWKS US Equity", resultsToBeChecked.get(29), "wrong");
        assertEquals("IPGP US Equity", resultsToBeChecked.get(30), "wrong");
        assertEquals("JKHY US Equity", resultsToBeChecked.get(31), "wrong");
        assertEquals("EA US Equity", resultsToBeChecked.get(32), "wrong");
        assertEquals("CERN US Equity", resultsToBeChecked.get(33), "wrong");
        assertEquals("NVDA US Equity", resultsToBeChecked.get(34), "wrong");
        assertEquals("AKAM US Equity", resultsToBeChecked.get(35), "wrong");
        assertEquals("QRVO US Equity", resultsToBeChecked.get(36), "wrong");
        assertEquals("CRM US Equity", resultsToBeChecked.get(37), "wrong");
        assertEquals("AAPL US Equity", resultsToBeChecked.get(38), "wrong");
        assertEquals("QCOM US Equity", resultsToBeChecked.get(39), "wrong");
        assertEquals("CTXS US Equity", resultsToBeChecked.get(40), "wrong");
        assertEquals("TTWO US Equity", resultsToBeChecked.get(41), "wrong");
        assertEquals("INTC US Equity", resultsToBeChecked.get(42), "wrong");
    }

}
