package club.astro.base.ui.clickgui.components;

import club.astro.Astro;
import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.ui.clickgui.ClickGUI;
import club.astro.base.utils.color.GradientCalculationUtil;
import club.astro.base.utils.misc.MouseUtils;
import club.astro.base.utils.render.RenderUtils2D;
import club.astro.base.utils.render.font.FontUtils;
import club.astro.base.utils.sound.SoundUtils;

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

        for (Module module : Astro.MODULE_MANAGER.getModulesByCategory(this.category)) {
            totalY += height;
            steps++;

            if (module.isOpened()) {
                Settings settings = new Settings(module, height - 1, width - 2, steps, gradMap);
                totalY += settings.totalHeight;
                steps += (settings.totalHeight - 1) / settings.height;
            }
        }

        gradMap = GradientCalculationUtil.getInterpolatedValues(steps, new Color(0x12C2E9), new Color(0xCD05FA));

        RenderUtils2D.drawRect(posX, posY, posX + width, totalY, frameColor);
//        RenderUtils2D.drawGradientRect(posX, posY, posX + width, totalY, new Color(0x12C2E9), new Color(0xBD45EA));
    }

    public void drawButtons(int posX, int posY, Color offColor, Color onColor, Color fontColor, int mouseX, int mouseY) {
        int steps = 0;
        for (Module module : Astro.MODULE_MANAGER.getModulesByCategory(category)) {
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
