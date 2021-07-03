package club.astro.base.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingUtil {
    public static double roundNumber(double number, int scale) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.UP);

        return bigDecimal.doubleValue();
    }

    public static int roundNumberDown(float number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(1, RoundingMode.DOWN);

        return bigDecimal.intValue();
    }
}
