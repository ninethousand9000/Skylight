package com.skylight.base.utils.color;

import java.awt.*;

public class ColorUtils {
    public static Color setAlphaAmount(Color color, float alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
}
