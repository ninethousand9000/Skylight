package com.skylight.base.utils.chat;

public class FilterUtil {
    public static boolean isAllowedNumber(char character) {
        String filter = "0123456789.";
        for (char c0 : filter.toCharArray()) {
            if (c0 == character) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllowedInteger(char character) {
        String filter = "0123456789-";
        for (char c0 : filter.toCharArray()) {
            if (c0 == character) {
                return true;
            }
        }
        return false;
    }
}
