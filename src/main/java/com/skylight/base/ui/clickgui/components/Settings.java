package com.skylight.base.ui.clickgui.components;

import com.skylight.base.features.modules.Module;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.ui.clickgui.ClickGUI;
import com.skylight.base.utils.chat.FilterUtil;
import com.skylight.base.utils.color.IntegrateAlphaUtil;
import com.skylight.base.utils.math.RoundingUtil;
import com.skylight.base.utils.misc.MouseUtils;
import com.skylight.base.utils.render.RenderUtils2D;
import com.skylight.base.utils.render.font.FontUtils;
import com.skylight.base.utils.sound.SoundUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    public int totalHeight;
    public int height;
    public int width;
    public int current;
    public Module module;
    public ArrayList<Setting<?>> settings;
    public Map<Integer, Integer> gradMap = new HashMap<Integer, Integer>();
    public static Color finalColor;

    public Settings(Module module, int height, int width, int current, Map<Integer, Integer> gradMap) {
        this.module = module;
        this.height = height;
        this.width = width;
        this.current = current;
        this.gradMap = gradMap;

        settings = module.getSettings();

        for (Setting<?> setting : settings) {
            if (setting.getValue() instanceof Boolean) {
                totalHeight += height;
            }
            if (setting.getValue() instanceof Enum) {
                totalHeight += height;
            }
            if (setting.getValue() instanceof String) {
                totalHeight += height;
            }
            if (setting.getValue() instanceof Integer) {
                totalHeight += height;
            }
            if (setting.getValue() instanceof Float) {
                totalHeight += height;
            }
            if (setting.getValue() instanceof Double) {
                totalHeight += height;
            }
            if (setting.getValue() instanceof Color) {
                if (setting.isOpened()) totalHeight += (height * 6) + 4;
                else totalHeight += height;
            }
        }
        totalHeight++;
    }

    public void draw(int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        for (Setting<?> setting : settings) {
            try {onColor = new Color(gradMap.get(current));} catch (NullPointerException e) {e.printStackTrace();}
            if (setting.getValue() instanceof Boolean) {
                Setting<Boolean> booleanSetting = (Setting<Boolean>) setting;
                drawBoolean(booleanSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Enum) {
                Setting<Enum> enumSetting = (Setting<Enum>) setting;
                drawEnum(enumSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof String) {
                Setting<String> stringSetting = (Setting<String>) setting;
                drawString(stringSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Integer) {
                NumberSetting<Integer> integerSetting = (NumberSetting<Integer>) setting;
                drawIntegerSlider(integerSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Float) {
                NumberSetting<Float> floatSetting = (NumberSetting<Float>) setting;
                drawFloatSlider(floatSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Double) {
                NumberSetting<Double> doubleSetting = (NumberSetting<Double>) setting;
                drawDoubleSlider(doubleSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Color) {
                Setting<Color> colorSetting = (Setting<Color>) setting;
                drawColor(colorSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                if (setting.isOpened()) posY += (height * 6) + 4;
                else posY += height;
            }
            current += 1;
        }
    }

    public int drawSubSettings(int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY, Setting<?> parentSetting) {
        int y = posY;
        for (Setting<?> setting : parentSetting.getSubSettings()) {
            if (setting.getValue() instanceof Boolean) {
                Setting<Boolean> booleanSetting = (Setting<Boolean>) setting;
                drawBoolean(booleanSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Enum) {
                Setting<Enum> enumSetting = (Setting<Enum>) setting;
                drawEnum(enumSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof String) {
                Setting<String> stringSetting = (Setting<String>) setting;
                drawString(stringSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Integer) {
                NumberSetting<Integer> integerSetting = (NumberSetting<Integer>) setting;
                drawIntegerSlider(integerSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Float) {
                NumberSetting<Float> floatSetting = (NumberSetting<Float>) setting;
                drawFloatSlider(floatSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Double) {
                NumberSetting<Double> doubleSetting = (NumberSetting<Double>) setting;
                drawDoubleSlider(doubleSetting, posX, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += height;
            }
            if (setting.getValue() instanceof Color) {

            }
        }
        return posY - y;
    }

    public void drawBoolean(Setting<Boolean> setting, int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        Color buttonColor = setting.getValue() ? onColor : offColor;

        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
        }
        else {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), setting.getValue() ? 190 : 160);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.leftClicked) {
            setting.setValue(!setting.getValue());
            SoundUtils.playGuiClick();
        }

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, buttonColor);
        FontUtils.drawString(setting.getName(), posX + 2, posY + 5, fontColor);
    }

    public <E extends Enum<E>> void drawEnum(Setting<Enum> setting, int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        Color buttonColor = onColor;

        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
        }
        else {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 190);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            final E[] values = (E[]) setting.getValue().getDeclaringClass().getEnumConstants();
            final int ordinal = setting.getValue().ordinal();

            if (ClickGUI.leftClicked) {
                setting.setValue(values[ordinal + 1 >= values.length ? 0 : ordinal + 1]);
                SoundUtils.playGuiClick();
            }
            else if (ClickGUI.rightClicked) {
                setting.setValue(values[ordinal - 1 < 0 ? values.length - 1 : ordinal - 1]);
                SoundUtils.playGuiClick();
            }
        }

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, buttonColor);
        FontUtils.drawString(setting.getName() + ": " + setting.getValue(), posX + 2, posY + 5, fontColor);
    }

    public void drawString(Setting<String> setting, int posX, int posY, Color focusColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        Color buttonColor = setting.isFocus() ? Color.blue : onColor;

        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
        }
        else {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 190);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.leftClicked) {
            if (ClickGUI.leftClicked) setting.setFocus(!setting.isFocus());
            if (ClickGUI.rightClicked) setting.setOpened(!setting.isOpened());
        }

        if (setting.isFocus()) {
            int key = ClickGUI.keyCode;
            char typedChar = ClickGUI.typedChar;

            if (key == Keyboard.KEY_RETURN) {
                setting.setFocus(false);
            }
            else if (key == Keyboard.KEY_NONE) {
                // empty
            }
            else if ((key == Keyboard.KEY_DELETE || key == Keyboard.KEY_BACK) && setting.getValue().length() > 0) {
                setting.setValue(setting.getValue().substring(0, setting.getValue().length() - 1));
            }
            else if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
                setting.setValue(setting.getValue() + (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? String.valueOf(typedChar).toUpperCase() : String.valueOf(typedChar).toLowerCase()));
            }

            ClickGUI.keyCode = Keyboard.KEY_NONE;
        }

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, buttonColor);
        FontUtils.drawString(setting.getName() + ": " + setting.getValue(), posX + 2, posY + 5, fontColor);
    }

    public void drawIntegerSlider(NumberSetting<Integer> setting, int posX, int posY, Color focusColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        Color buttonColor = setting.isFocus() ? Color.blue : onColor;
        int pixAdd = (int) (((posX + width) - posX) * (setting.getValue() - setting.getMin()) / (setting.getMax() - setting.getMin()));

        if (pixAdd > width) pixAdd = width;

        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
        }
        else {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 190);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            if (ClickGUI.leftClicked) setting.setFocus(!setting.isFocus());
            if (ClickGUI.rightClicked) setting.setOpened(!setting.isOpened());
            if (ClickGUI.leftDown) {
                setting.setFocus(true);
                int percentError = (mouseX - posX) * 100 / ((posX + width) - posX);
                setting.setValue((int) RoundingUtil.roundNumber(percentError * ((setting.getMax() - setting.getMin()) / 100.0D) + setting.getMin(), setting.getScale()));
            }
        }

        if (MouseUtils.mouseHovering(posX + width - 2, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.leftDown) {
            setting.setValue(setting.getMax());
        }

        if (setting.isFocus()) {
            int key = ClickGUI.keyCode;
            char typedChar = ClickGUI.typedChar;

            if (key == Keyboard.KEY_RETURN) {
                setting.setFocus(false);
            }
            else if (key == Keyboard.KEY_NONE) {
                // empty
            }
            else if ((key == Keyboard.KEY_DELETE || key == Keyboard.KEY_BACK) && String.valueOf(setting.getValue()).length() > 0) {
                String strVal = String.valueOf(setting.getValue()).substring(0, String.valueOf(setting.getValue()).length() - 1);
                if (strVal.length() <= 0) {
                    strVal = "0";
                }
                setting.setValue(Integer.parseInt(strVal));
            }
            else if (FilterUtil.isAllowedInteger(typedChar)) {
                String strVal = setting.getValue() + "" + typedChar;
                setting.setValue(Integer.parseInt(strVal));
            }

            ClickGUI.keyCode = Keyboard.KEY_NONE;
        }

        RenderUtils2D.drawRect(posX, posY, posX + pixAdd, posY + height, buttonColor);
        FontUtils.drawString(setting.getName() + ": " + setting.getValue(), posX + 2, posY + 5, fontColor);
    }

    public void drawFloatSlider(NumberSetting<Float> setting, int posX, int posY, Color focusColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        Color buttonColor = setting.isFocus() ? Color.blue : onColor;
        int pixAdd = (int) (((posX + width) - posX) * (setting.getValue() - setting.getMin()) / (setting.getMax() - setting.getMin()));

        if (pixAdd > width) pixAdd = width;

        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
        }
        else {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 190);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            if (ClickGUI.leftClicked) setting.setFocus(!setting.isFocus());
            if (ClickGUI.rightClicked) setting.setOpened(!setting.isOpened());
            if (ClickGUI.leftDown) {
                setting.setFocus(true);
                int percentError = (mouseX - posX) * 100 / ((posX + width) - posX);
                setting.setValue((float) RoundingUtil.roundNumber(percentError * ((setting.getMax() - setting.getMin()) / 100.0D) + setting.getMin(), setting.getScale()));
            }
        }

        if (MouseUtils.mouseHovering(posX + width - 2, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.leftDown) {
            setting.setValue(setting.getMax());
        }

        if (setting.isFocus()) {
            int key = ClickGUI.keyCode;
            char typedChar = ClickGUI.typedChar;

            if (key == Keyboard.KEY_RETURN) {
                setting.setFocus(false);
            }
            else if (key == Keyboard.KEY_NONE) {
                // empty
            }
            else if ((key == Keyboard.KEY_DELETE || key == Keyboard.KEY_BACK) && String.valueOf(setting.getValue()).length() > 0) {
                String strVal = String.valueOf(setting.getValue()).substring(0, String.valueOf(setting.getValue()).length() - 1);
                if (strVal.length() <= 0) {
                    strVal = "0";
                }
                setting.setValue(Float.parseFloat(strVal));
            }
            else if (FilterUtil.isAllowedNumber(typedChar)) {
                String strVal = setting.getValue() + "" + typedChar;
                setting.setValue(Float.parseFloat(strVal));
            }

            ClickGUI.keyCode = Keyboard.KEY_NONE;
        }

        RenderUtils2D.drawRect(posX, posY, posX + pixAdd, posY + height, buttonColor);
        FontUtils.drawString(setting.getName() + ": " + setting.getValue(), posX + 2, posY + 5, fontColor);
    }

    public void drawDoubleSlider(NumberSetting<Double> setting, int posX, int posY, Color focusColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        Color buttonColor = setting.isFocus() ? Color.blue : onColor;
        int pixAdd = (int) (((posX + width) - posX) * (setting.getValue() - setting.getMin()) / (setting.getMax() - setting.getMin()));

        if (pixAdd > width) pixAdd = width;

        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
        }
        else {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 190);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            if (ClickGUI.leftClicked) setting.setFocus(!setting.isFocus());
            if (ClickGUI.rightClicked) setting.setOpened(!setting.isOpened());
            if (ClickGUI.leftDown) {
                setting.setFocus(true);
                int percentError = (mouseX - posX) * 100 / ((posX + width) - posX);
                setting.setValue(RoundingUtil.roundNumber(percentError * ((setting.getMax() - setting.getMin()) / 100.0D) + setting.getMin(), setting.getScale()));
            }
        }

        if (MouseUtils.mouseHovering(posX + width - 2, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.leftDown) {
            setting.setValue(setting.getMax());
        }

        if (setting.isFocus()) {
            int key = ClickGUI.keyCode;
            char typedChar = ClickGUI.typedChar;

            if (key == Keyboard.KEY_RETURN) {
                setting.setFocus(false);
            }
            else if (key == Keyboard.KEY_NONE) {
                // empty
            }
            else if ((key == Keyboard.KEY_DELETE || key == Keyboard.KEY_BACK) && String.valueOf(setting.getValue()).length() > 0) {
                String strVal = String.valueOf(setting.getValue()).substring(0, String.valueOf(setting.getValue()).length() - 1);
                if (strVal.length() <= 0) {
                    strVal = "0";
                }
                setting.setValue(Double.parseDouble(strVal));
            }
            else if (FilterUtil.isAllowedNumber(typedChar)) {
                String strVal = setting.getValue() + "" + typedChar;
                setting.setValue(Double.parseDouble(strVal));
            }

            ClickGUI.keyCode = Keyboard.KEY_NONE;
        }

        RenderUtils2D.drawRect(posX, posY, posX + pixAdd, posY + height, buttonColor);
        FontUtils.drawString(setting.getName() + ": " + setting.getValue(), posX + 2, posY + 5, fontColor);
    }

    public void drawColor(Setting<Color> setting, int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        Color buttonColor = onColor;

        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
        }
        else {
            buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 190);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.rightClicked) {
            setting.setOpened(!setting.isOpened());
            SoundUtils.playGuiClick();
        }

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, buttonColor);
        FontUtils.drawString(setting.getName(), posX + 2, posY + 5, fontColor);
        FontUtils.drawString(setting.isOpened() ? "v" : ">", posX + width - FontUtils.getStringWidth(">") - 2, posY + 5, fontColor);

        if (setting.isOpened()) {
            posY += height + 1;

            float[] color = new float[] {
                    Color.RGBtoHSB(setting.getValue().getRed(), setting.getValue().getGreen(), setting.getValue().getBlue(), null)[0],
                    Color.RGBtoHSB(setting.getValue().getRed(), setting.getValue().getGreen(), setting.getValue().getBlue(), null)[1],
                    Color.RGBtoHSB(setting.getValue().getRed(), setting.getValue().getGreen(), setting.getValue().getBlue(), null)[2]
            };

            int pickerX = posX;
            int pickerY = posY;
            int pickerWidth = width;
            int pickerHeight = height * 3;
            // height * 5 + 3
            int hueSliderX = posX;
            int hueSliderY = posY + pickerHeight + 1;
            int hueSliderWidth = width;
            int hueSliderHeight = height;

            int alphaSliderX = posX;
            int alphaSliderY = hueSliderY + hueSliderHeight + 1;
            int alphaSliderWidth = width;
            int alphaSliderHeight = height;

            boolean pickingColour = false;
            boolean pickingHue = false;
            boolean pickingAlpha = false;

            if (ClickGUI.leftDown && MouseUtils.mouseHovering(pickerX, pickerY, pickerX + pickerWidth, pickerY + pickerHeight, mouseX, mouseY)) {
                pickingColour = true;
            }

            if (ClickGUI.leftDown && MouseUtils.mouseHovering(hueSliderX, hueSliderY, hueSliderX + hueSliderWidth, hueSliderY + hueSliderHeight, mouseX, mouseY)) {
                pickingHue = true;
            }

            if (ClickGUI.leftDown && MouseUtils.mouseHovering(alphaSliderX, alphaSliderY, alphaSliderX + alphaSliderWidth, alphaSliderY + alphaSliderHeight, mouseX, mouseY)) {
                pickingAlpha = true;
            }

            if (pickingHue) {
                float restrictedX = (float) Math.min(Math.max(hueSliderX, mouseX), hueSliderX + hueSliderWidth);
                color[0] = (restrictedX - (float) hueSliderX) / hueSliderWidth;
            }

            if (pickingAlpha) {
                float restrictedX = (float) Math.min(Math.max(alphaSliderX, mouseX), alphaSliderX + alphaSliderWidth);
                setting.setAlpha(1 - (restrictedX - (float) alphaSliderX) / alphaSliderWidth);
            }

            if (pickingColour) {
                float restrictedX = (float) Math.min(Math.max(pickerX, mouseX), pickerX + pickerWidth);
                float restrictedY = (float) Math.min(Math.max(pickerY, mouseY), pickerY + pickerHeight);

                color[1] = (restrictedX - (float) pickerX) / pickerWidth;
                color[2] = 1 - (restrictedY - (float) pickerY) / pickerHeight;
            }

            Gui.drawRect(pickerX - 2, pickerY - 2, pickerX + pickerWidth + 2, pickerY + pickerHeight + 80, 0x58F818);

            int selectedColor = Color.HSBtoRGB(color[0], 1.0f, 1.0f);

            float selectedRed = (selectedColor >> 16 & 0xFF) / 255.0f;
            float selectedGreen = (selectedColor >> 8 & 0xFF) / 255.0f;
            float selectedBlue = (selectedColor & 0xFF) / 255.0f;

            RenderUtils2D.drawPickerBase(pickerX, pickerY, pickerWidth, pickerHeight, selectedRed, selectedGreen, selectedBlue, setting.getAlpha());

            drawHueSlider(hueSliderX, hueSliderY, hueSliderWidth, hueSliderHeight, color[0]);

            int cursorX = (int) (pickerX + color[1] * pickerWidth);
            int cursorY = (int) ((pickerY + pickerHeight) - color[2] * pickerHeight);

            Gui.drawRect(cursorX - 2, cursorY - 2, cursorX + 2, cursorY + 2, Color.white.getRGB());

            drawAlphaSlider(alphaSliderX, alphaSliderY, alphaSliderWidth, alphaSliderHeight, selectedRed, selectedGreen, selectedBlue, setting.getAlpha());

            finalColor = IntegrateAlphaUtil.integrateAlpha(new Color(Color.HSBtoRGB(color[0], color[1], color[2])), setting.getAlpha());

            setting.setValue(finalColor);
        }
    }

    public static void drawHueSlider(int x, int y, int width, int height, float hue) {
        int step = 0;

        if (height > width) {
            Gui.drawRect(x, y, x + width, y + 4, 0xFFFF0000);

            y += 4;

            for (int colorIndex = 0; colorIndex < 5; colorIndex++) {
                int previousStep = Color.HSBtoRGB((float) step / 5, 1.0f, 1.0f);
                int nextStep = Color.HSBtoRGB((float) (step + 1) / 5, 1.0f, 1.0f);

                RenderUtils2D.drawGradientRect(x, y + step * (height / 5), x + width, y + (step + 1) * (height / 5), new Color(previousStep), new Color(nextStep));

                step++;
            }

            int sliderMinY = (int) (y + (height * hue)) - 4;

            Gui.drawRect(x, sliderMinY - 1, x + width, sliderMinY + 1, -1);
        } else {
            for (int colorIndex = 0; colorIndex < 5; colorIndex++) {
                int previousStep = Color.HSBtoRGB((float) step / 5, 1.0f, 1.0f);
                int nextStep = Color.HSBtoRGB((float) (step + 1) / 5, 1.0f, 1.0f);

                RenderUtils2D.gradient(x + step * (width / 5), y, x + (step + 1) * (width / 5), y + height, previousStep, nextStep, true);

                step++;
            }

            int sliderMinX = (int) (x + (width * hue));

            Gui.drawRect(sliderMinX - 1, y, sliderMinX + 1, y + height, -1);
        }
    }

    public static void drawAlphaSlider(int x, int y, int width, int height, float red, float green, float blue, float alpha) {
        boolean left = true;

        int checkerBoardSquareSize = height / 2;

        for (int squareIndex = -checkerBoardSquareSize; squareIndex < width; squareIndex += checkerBoardSquareSize) {
            if (!left) {
                Gui.drawRect(x + squareIndex, y, x + squareIndex + checkerBoardSquareSize, y + height, 0xFFFFFFFF);
                Gui.drawRect(x + squareIndex, y + checkerBoardSquareSize, x + squareIndex + checkerBoardSquareSize, y + height, 0xFF909090);

                if (squareIndex < width - checkerBoardSquareSize) {
                    int minX = x + squareIndex + checkerBoardSquareSize;
                    int maxX = Math.min(x + width, x + squareIndex + checkerBoardSquareSize * 2);

                    Gui.drawRect(minX, y, maxX, y + height, 0xFF909090);
                    Gui.drawRect(minX,y + checkerBoardSquareSize, maxX, y + height, 0xFFFFFFFF);
                }
            }

            left = !left;
        }

        RenderUtils2D.drawLeftGradientRect(x, y, x + width, y + height, new Color(red, green, blue, 1), new Color(0, true));

        int sliderMinX = (int) (x + width - (width * alpha));

        Gui.drawRect(sliderMinX - 1, y,  sliderMinX + 1, y + height, -1);
    }

}
