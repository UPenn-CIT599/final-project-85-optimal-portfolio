import java.util.HashMap;
import java.util.Set;

/**
 * General help methods to work with data structures
 */
public class HelperMethods {
    /**
     * For ROA computation
     *
     * @param aHashMap
     * @return
     */
    public String findKeyWithMaxValue(HashMap<String, Double> aHashMap) {
        Set<String> loopSet = aHashMap.keySet();
        double currentMaxValue = 0;
        String keyWithMaxValue = "";
        for (String key : loopSet) {
            double currentValue = aHashMap.get(key);
            if (currentMaxValue < currentValue) {
                currentMaxValue = currentValue;
                keyWithMaxValue = key;
            }
        }
        return keyWithMaxValue;
    }

    /**
     * For Leverage ratio computation
     *
     * @param aHashMap
     * @return
     */
    public String findKeyWithMinValue(HashMap<String, Double> aHashMap) {
        Set<String> loopSet = aHashMap.keySet();
        double currentMinValue = 100;
        String keyWithMinValue = "";
        for (String key : loopSet) {
            double currentValue = aHashMap.get(key);
            if (currentMinValue > currentValue) {
                currentMinValue = currentValue;
                keyWithMinValue = key;
            }
        }
        return keyWithMinValue;
    }
}
