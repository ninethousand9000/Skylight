package club.astro.testing;

import club.astro.base.features.modules.ModuleManager;
import club.astro.base.settings.Setting;
import club.astro.client.modules.combat.Test1;

import java.awt.*;

public class Test {
    public static void main(String[] args) {
        Color color = new Color(100, 200, 3, 155);
        /*float a = (float)(color.getRGB() >> 24 & 255);
        float r = (float)(color.getRGB() >> 16 & 255);
        float g = (float)(color.getRGB() >> 8 & 255);
        float b = (float)(color.getRGB() & 255);*/

        float a = color.getAlpha();
        float r = color.getRed();
        float g = color.getGreen();
        float b = color.getBlue();

        System.out.println(" " + a + " " + r + " " + g + " " + b);
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
