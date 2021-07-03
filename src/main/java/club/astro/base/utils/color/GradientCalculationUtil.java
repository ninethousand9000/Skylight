package club.astro.base.utils.color;

import club.astro.Astro;
import club.astro.base.utils.math.RoundingUtil;

import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GradientCalculationUtil {
    public static Map<Integer, Integer> getInterpolatedValues(int steps, Color start, Color end) {
        Map<Integer, Integer> gradMap = new HashMap<Integer, Integer>();
        Color temp;
        float percentChange = 1.0f / steps;
        float percent =  0;
        for (int i = 0; i < steps; i++) {
            int resultRed = RoundingUtil.roundNumberDown(start.getRed() + percent * (end.getRed() - start.getRed()));
            int resultGreen = RoundingUtil.roundNumberDown(start.getGreen() + percent * (end.getGreen() - start.getGreen()));
            int resultBlue = RoundingUtil.roundNumberDown(start.getBlue() + percent * (end.getBlue() - start.getBlue()));
            temp = new Color(resultRed, resultGreen, resultBlue);
            gradMap.put(i, temp.getRGB());
            percent += percentChange;
        }
        return gradMap;
    }
}
