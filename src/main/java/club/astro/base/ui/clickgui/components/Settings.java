package club.astro.base.ui.clickgui.components;

import club.astro.Astro;
import club.astro.base.features.modules.Module;
import club.astro.base.settings.Setting;
import club.astro.base.ui.clickgui.ClickGUI;
import club.astro.base.utils.misc.MouseUtils;
import club.astro.base.utils.render.RenderUtils2D;
import club.astro.base.utils.sound.SoundUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;

public class Settings {
    public int totalHeight;
    public int height;
    public int width;
    public Module module;
    public ArrayList<Setting<?>> settings;

    public Settings(Module module, int height, int width) {
        this.module = module;
        this.height = height;
        this.width = width;

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
                totalHeight += height;
            }
        }
    }

    public void draw(int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        for (Setting<?> setting : settings) {
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

            }
            if (setting.getValue() instanceof Float) {

            }
            if (setting.getValue() instanceof Double) {

            }
            if (setting.getValue() instanceof Color) {

            }
        }
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
        Astro.FONT_RENDERER.drawText(setting.getName(), posX + 2, posY + 5, fontColor);
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
        Astro.FONT_RENDERER.drawText(setting.getName() + ": " + setting.getValue(), posX + 2, posY + 5, fontColor);
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
        int key = ClickGUI.keyCode;
        char typedChar = ClickGUI.typedChar;

        if (setting.isFocus()) {
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
        }

        ClickGUI.keyCode = Keyboard.KEY_NONE;

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, buttonColor);
        Astro.FONT_RENDERER.drawText(setting.getName() + ": " + setting.getValue(), posX + 2, posY + 5, fontColor);
    }
}
