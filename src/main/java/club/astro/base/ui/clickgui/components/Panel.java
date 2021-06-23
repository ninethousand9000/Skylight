package club.astro.base.ui.clickgui.components;

import club.astro.base.features.modules.ModuleCategory;

import java.awt.*;

public class Panel {
    public int posX;
    public int posY;
    public int sectionWidth;
    public int sectionHeight;
    public ModuleCategory category;
    public Color frameColor;
    public Color enabledColor;
    public Color fontColor;
    public Color typingColor;

    public Panel(int posX, int posY, int sectionWidth, int sectionHeight, ModuleCategory category,
                 Color frameColor, Color enabledColor, Color fontColor, Color typingColor) {
        this.posX = posX;
        this.posY = posY;
        this.sectionWidth = sectionWidth;
        this.sectionHeight = sectionHeight;
        this.category = category;
        this.frameColor = frameColor;
        this.enabledColor = enabledColor;
        this.fontColor = fontColor;
        this.typingColor = typingColor;

        Header header = new Header(sectionWidth, sectionHeight, category);
        header.draw(posX, posY, frameColor, fontColor);
    }
}
