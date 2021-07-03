package com.skylight.testing;

import com.skylight.base.settings.Setting;

import java.awt.*;

public class Test {
    public static void main(String[] args) {
        int steps = 9;
        Color start = new Color(0x12C2E9),
                end = new Color(0xBD45EA);


    }

    public static void drawSettings(Setting<?> setting) {
        System.out.println(setting.getValue());
        if (setting.getSubSettings().size() > 0) {
            for (Setting<?> sub : setting.getSubSettings()) {
                drawSettings(sub);
            }
        }
    }
}
