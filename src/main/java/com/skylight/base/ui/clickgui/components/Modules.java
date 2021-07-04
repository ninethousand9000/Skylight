package com.skylight.base.ui.clickgui.components;

import com.skylight.Skylight;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.ui.clickgui.ClickGUI;
import com.skylight.base.utils.color.GradientCalculationUtil;
import com.skylight.base.utils.misc.MouseUtils;
import com.skylight.base.utils.render.RenderUtils2D;
import com.skylight.base.utils.render.font.FontUtils;
import com.skylight.base.utils.sound.SoundUtils;
import com.skylight.client.modules.client.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Modules {
    public int width;
    public int height;
    public ModuleCategory category;
    public Map<Integer, Integer> gradMap = new HashMap<Integer, Integer>();

    public Modules(int width, int height, ModuleCategory category) {
        this.width = width;
        this.height = height;
        this.category = category;
    }

    public void draw(int posX, int posY, Color accentColor, Color frameColor, Color fontColor, int mouseX, int mouseY) {
        if (category.isOpenInGui()) {
            drawBackground(posX, posY, frameColor);
            drawButtons(posX + 1, posY + 1, frameColor, accentColor, fontColor, mouseX, mouseY);
        }
    }

    public void drawBackground(int posX, int posY, Color frameColor) {
        int totalY = posY + 1;
        int steps = 0;

        for (Module module : Skylight.MODULE_MANAGER.getModulesByCategory(this.category)) {
            totalY += height;
            steps++;

            if (module.isOpened()) {
                Settings settings = new Settings(module, height - 1, width - 2, steps, gradMap);
                totalY += settings.totalHeight;
                steps += (settings.totalHeight - 1) / settings.height;
            }
        }

        if (GUI.gradientButtons.getValue()) gradMap = GradientCalculationUtil.getInterpolatedValues(steps, GUI.gradientTop.getValue(), GUI.gradientBottom.getValue());
        else gradMap = GradientCalculationUtil.fillMapWithColor(steps, GUI.normalColor.getValue());

        RenderUtils2D.drawRect(posX, posY, posX + width, totalY, frameColor);
//        RenderUtils2D.drawGradientRect(posX, posY, posX + width, totalY, new Color(0x12C2E9), new Color(0xBD45EA));
    }

    public void drawButtons(int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        int steps = 0;
        for (Module module : Skylight.MODULE_MANAGER.getModulesByCategory(category)) {
            try {onColor = new Color(gradMap.get(steps));} catch (NullPointerException ignored){}

            Color buttonColor = module.isEnabled() ? onColor : offColor;

            if (MouseUtils.mouseHovering(posX, posY, posX + width - 2, posY + height - 1, mouseX, mouseY)) {
                buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 220);
            }
            else {
                buttonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), module.isEnabled() ? 190 : 160);
            }
            if (MouseUtils.mouseHovering(posX, posY, posX + width - 2, posY + height - 1, mouseX, mouseY) && ClickGUI.leftClicked) {
                module.setEnabled(!module.isEnabled());
                SoundUtils.playGuiClick();
            }
            if (MouseUtils.mouseHovering(posX, posY, posX + width - 2, posY + height - 1, mouseX, mouseY) && ClickGUI.rightClicked && module.getSettings().size() > 0) {
                module.setOpened(!module.isOpened());
                SoundUtils.playGuiClick();
            }

            RenderUtils2D.drawRect(posX, posY, posX + width - 2, posY + height - 1, buttonColor);
            FontUtils.drawString(module.getName(), posX + 2, posY + 5, fontColor);

            if (module.getSettings().size() > 0) {
                if (module.isOpened()) {
                    FontUtils.drawString("v", posX + width - 2 - FontUtils.getStringWidth("v") - 2, posY + 5, fontColor);
                }
                else {
                    FontUtils.drawString(">", posX + width - 2 - FontUtils.getStringWidth(">") - 2, posY + 5, fontColor);
                }
            }

            posY += height;

            if (module.isOpened()) {
                Settings settings = new Settings(module, height - 1, width - 4, steps + 1, gradMap);
                settings.draw(posX + 1, posY, offColor, onColor, fontColor, mouseX, mouseY);
                posY += settings.totalHeight;
                steps += (settings.totalHeight - 1) / settings.height;
            }
            steps++;
        }
    }
}