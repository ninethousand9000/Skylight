package com.skylight.base.ui.clickgui.components;

import com.skylight.base.features.modules.ModuleCategory;

import java.awt.*;

public class Panel {
    public int posX;
    public int posY;
    public int mouseX;
    public int mouseY;
    public int sectionWidth;
    public int sectionHeight;
    public ModuleCategory category;
    public Color accentColor;
    public Color frameColor;
    public Color enabledColor;
    public Color fontColor;
    public Color typingColor;

    public Panel(int posX, int posY, int sectionWidth, int sectionHeight, ModuleCategory category,
                 Color accentColor, Color frameColor, Color enabledColor, Color fontColor, Color typingColor) {
        this.posX = posX;
        this.posY = posY;
        this.sectionWidth = sectionWidth;
        this.sectionHeight = sectionHeight;
        this.category = category;
        this.accentColor = accentColor;
        this.frameColor = frameColor;
        this.enabledColor = enabledColor;
        this.fontColor = fontColor;
        this.typingColor = typingColor;
        this.mouseX = 0;
        this.mouseY = 0;
    }

    public void draw() {
        Header header = new Header(sectionWidth, sectionHeight, category);
        header.draw(posX, posY, accentColor, fontColor, mouseX, mouseY);
        posY += header.getHeight();

        Modules modules = new Modules(sectionWidth - 2, sectionHeight, category);
        modules.draw(posX + 1, posY, accentColor, frameColor, fontColor, mouseX, mouseY);

    }
}
