package club.astro.base.utils.render.font;

import club.astro.base.utils.game.Game;

import java.awt.*;

public class FontRenderer implements Game {
    public void drawText(String text, int posX, int posY, Color color) {
        mc.fontRenderer.drawStringWithShadow(text, posX, posY, color.getRGB());
    }

    public void drawTextRainbow(String text, int x, int y, Color startColor, float factor) {
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
            char nextChar = text.charAt(clamp(i + 1, 0, text.length() - 1));
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
                drawText(escapeString, (int) ((int) x + (float) currentWidth), (int) y, Color.WHITE);
                break;
            }
            drawText(String.valueOf(currentChar).equals("\u00a7") ? "" : String.valueOf(currentChar), (int) (x + currentWidth), y, shouldRainbow ? currentColor : Color.WHITE);
            if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldContinue = true;
            }
            currentWidth += getTextWidth(String.valueOf(currentChar));
            if (String.valueOf(currentChar).equals(" ")) continue;
            currentColor = new Color(Color.HSBtoRGB(currentHue, saturation, brightness));
            currentHue += hueIncrement;
        }
    }

    public static int clamp(int num, int min, int max) {
        return num < min ? min : Math.min(num, max);
    }

    public int getTextWidth(String text) {
        return mc.fontRenderer.getStringWidth(text);
    }

    public int getTextHeight() {
        return (-1 - 8) / 2;
    }
}
