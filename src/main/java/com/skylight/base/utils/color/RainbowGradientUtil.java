package com.skylight.base.utils.color;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RainbowGradientUtil {
    public static Map<Integer, Integer> getGradient(int speed, int saturation, int brightness) {
        Map<Integer, Integer> colorHeightMap = new HashMap<Integer, Integer>();
        int colorSpeed = (int) (101 - speed);
        float tempHue = (float) (System.currentTimeMillis() % (long) (360 * colorSpeed)) / (360.0f * (float) colorSpeed);
        for (int i = 0; i <= 510 * 8; ++i) {
            colorHeightMap.put(i, Color.HSBtoRGB(tempHue, (float) saturation / 255.0f, (float) brightness / 255.0f));
            if (tempHue + 0.0013071896f < 1) {
                tempHue += 0.0013071896f;
            }
            else {
                tempHue = 0;
            }
        }
        return colorHeightMap;
    }
}
