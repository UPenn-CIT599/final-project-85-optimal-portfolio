import java.util.HashMap;
import java.util.Set;

public class HelperMethods {
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
}
