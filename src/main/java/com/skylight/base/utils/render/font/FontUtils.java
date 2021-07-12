package com.skylight.base.utils.render.font;

import com.skylight.base.utils.math.MathUtils;
import com.skylight.client.modules.client.Font;
import net.minecraft.client.Minecraft;

import java.awt.*;

public final class FontUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static float drawString(String text, int x, int y, Color color) {
        CFontRenderer renderer = Font.customFont.getValue().getRenderer();

        if (renderer != null) return renderer.drawStringWithShadow(text, x, y, color);
        else return mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
    }

    public static float drawStringWithShadow(String text, int x, int y, Color color) {
        CFontRenderer renderer = Font.customFont.getValue().getRenderer();

        if (renderer != null) return renderer.drawStringWithShadow(text, x, y, color);
        else return mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
    }

    public static int getStringWidth(String string) {
        CFontRenderer renderer = Font.customFont.getValue().getRenderer();

        if (renderer != null) return renderer.getStringWidth(string);
        else return mc.fontRenderer.getStringWidth(string);
    }

    public static int getFontHeight() {
        CFontRenderer renderer = Font.customFont.getValue().getRenderer();

        if (renderer != null) return renderer.getHeight();
        else return mc.fontRenderer.FONT_HEIGHT;
    }

    public static void drawRainbowString(String text, int x, int y, Color startColor, float factor) {
        Color currentColor = startColor;
        float hueIncrement = 1.0f / factor;
        String[] rainbowStrings = text.split("\u00a7.");
        float currentHue = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[0];
        float saturation = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[1];
        float brightness = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[2];
        int currentWidth = 0;
        boolean shouldRainbow = true;
        boolean shouldContinue = false;
        for (int i = 0; i < text.length(); ++i) {
            char currentChar = text.charAt(i);
            char nextChar = text.charAt(MathUtils.clamp(i + 1, 0, text.length() - 1));
            if ((String.valueOf(currentChar) + nextChar).equals("\u00a7r")) {
                shouldRainbow = false;
            } else if ((String.valueOf(currentChar) + nextChar).equals("\u00a7+")) {
                shouldRainbow = true;
            }
            if (shouldContinue) {
                shouldContinue = false;
                continue;
            }
            if ((String.valueOf(currentChar) + nextChar).equals("\u00a7r")) {
                String escapeString = text.substring(i);
                drawString(escapeString, x + currentWidth, y, Color.WHITE);
                break;
            }
            drawString(String.valueOf(currentChar).equals("\u00a7") ? "" : String.valueOf(currentChar), x + currentWidth, y, shouldRainbow ? currentColor : Color.WHITE);
            if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldContinue = true;
            }
            currentWidth += getStringWidth(String.valueOf(currentChar));
            if (String.valueOf(currentChar).equals(" ")) continue;
            currentColor = new Color(Color.HSBtoRGB(currentHue, saturation, brightness));
            currentHue += hueIncrement;
        }
    }

    private FontUtils() {
        throw new UnsupportedOperationException();
    }
}
