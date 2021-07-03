package com.skylight.base.utils.math;

public class MathUtils {
    public static int clamp(int num, int min, int max) {
        return num < min ? min : Math.min(num, max);
    }
}
