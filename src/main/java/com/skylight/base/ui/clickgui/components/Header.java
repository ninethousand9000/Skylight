package com.skylight.base.ui.clickgui.components;

import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.ui.clickgui.ClickGUI;
import com.skylight.base.utils.misc.MouseUtils;
import com.skylight.base.utils.render.RenderUtils2D;
import com.skylight.base.utils.render.font.FontUtils;
import com.skylight.base.utils.sound.SoundUtils;
import com.skylight.client.modules.client.GUI;

import java.awt.*;

public class Header {
    public int width;
    public int height;
    public ModuleCategory category;

    public Header(int width, int height, ModuleCategory category) {
        this.width = width;
        this.height = height;
        this.category = category;
    }

    public void draw(int posX, int posY, Color frameColor, Color fontColor, int mouseX, int mouseY) {
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY)) {
            frameColor = new Color(frameColor.getRed(), frameColor.getGreen(), frameColor.getBlue(), 255);
        }
        else {
            frameColor = new Color(frameColor.getRed(), frameColor.getGreen(), frameColor.getBlue(), 220);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.rightClicked) {
            category.setOpenInGui(!category.isOpenInGui());
            SoundUtils.playGuiClick();
        }

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, GUI.gradientButtons.getValue() ? GUI.gradientTop.getValue() : GUI.normalColor.getValue());
        //RenderUtils2D.drawLeftGradientRect(posX, posY, posX + width, posY + height, new Color(0x12C2E9), new Color(0xCD05FA));
        FontUtils.drawString(category.name(), posX + 4, posY + 5, fontColor);

        if (category.isOpenInGui()) {
            FontUtils.drawString("v", posX + width - FontUtils.getStringWidth("v") - 2, posY + 5, fontColor);
        }
        else {
            FontUtils.drawString(">", posX + width - FontUtils.getStringWidth(">") - 2, posY + 5, fontColor);
        }
    }

    public int getHeight() {
        return height;
    }
}
