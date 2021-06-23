package club.astro.base.ui.clickgui.components;

import club.astro.Astro;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.utils.render.RenderUtils2D;
import club.astro.base.utils.render.font.FontRenderer;

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

    public void draw(int posX, int posY, Color frameColor, Color fontColor) {
        RenderUtils2D.drawRect(posX, posY, width, height, frameColor);
        Astro.FONT_RENDERER.drawText(category.name(), posX + (width / 2) - (Astro.FONT_RENDERER.getTextWidth(category.name()) / 2), posY, fontColor);
    }
}
