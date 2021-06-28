package club.astro.base.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingUtil {
    public static double roundNumber(double number, int scale) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.UP);

        return bigDecimal.doubleValue();
    }
}
