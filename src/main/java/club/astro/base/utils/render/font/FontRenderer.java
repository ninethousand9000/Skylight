package club.astro.base.utils.render.font;

import club.astro.base.utils.game.Game;

import java.awt.*;

public class FontRenderer implements Game {
    public void drawText(String text, int posX, int posY, Color color) {
        mc.fontRenderer.drawString(text, posX, posY, color.getRGB());
    }

    public int getTextWidth(String text) {
        return mc.fontRenderer.getStringWidth(text);
    }
}
