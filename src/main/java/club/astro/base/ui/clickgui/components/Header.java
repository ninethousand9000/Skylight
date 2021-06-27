package club.astro.base.ui.clickgui.components;

import club.astro.Astro;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.ui.clickgui.ClickGUI;
import club.astro.base.utils.misc.MouseUtils;
import club.astro.base.utils.render.RenderUtils2D;
import club.astro.base.utils.sound.SoundUtils;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

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
        else {
            frameColor = new Color(frameColor.getRed(), frameColor.getGreen(), frameColor.getBlue(), 200);
        }
        if (MouseUtils.mouseHovering(posX, posY, posX + width, posY + height, mouseX, mouseY) && ClickGUI.rightClicked) {
            category.setOpenInGui(!category.isOpenInGui());
            SoundUtils.playGuiClick();
        }

        RenderUtils2D.drawRect(posX, posY, posX + width, posY + height, frameColor);
        Astro.FONT_RENDERER.drawText(category.name(), posX + 4, posY + 5, fontColor);

        if (category.isOpenInGui()) {
            Astro.FONT_RENDERER.drawText("v", posX + width - Astro.FONT_RENDERER.getTextWidth("v") - 2, posY + 5, fontColor);
        }
        else {
            Astro.FONT_RENDERER.drawText(">", posX + width - Astro.FONT_RENDERER.getTextWidth(">") - 2, posY + 5, fontColor);
        }
    }

    public int getHeight() {
        return height;
    }
}
