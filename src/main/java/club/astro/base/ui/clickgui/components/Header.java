package club.astro.base.ui.clickgui.components;

import club.astro.Astro;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.utils.misc.MouseUtils;
import club.astro.base.utils.render.RenderUtils2D;

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
            frameColor = new Color(frameColor.getRed(), frameColor.getGreen(), frameColor.getBlue(), 220);
        }

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, frameColor);
        Astro.FONT_RENDERER.drawText(category.name(), posX + (width / 2 - Astro.FONT_RENDERER.getTextWidth(category.name()) / 2), posY - (Astro.FONT_RENDERER.getTextHeight() / 2), fontColor);
    }
}
