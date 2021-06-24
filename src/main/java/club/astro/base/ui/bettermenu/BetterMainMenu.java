package club.astro.base.ui.bettermenu;

import club.astro.Astro;
import club.astro.base.utils.color.RainbowGradientUtil;
import club.astro.base.utils.render.RenderUtils2D;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.Map;

public class BetterMainMenu extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution res = new ScaledResolution(mc);
        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        RenderUtils2D.drawRect(0, 0, screenWidth, screenHeight, new Color(0x383838));
        RenderUtils2D.drawRect((screenWidth / 2) - 170, (screenHeight / 2) + 100, (screenWidth / 2) + 170, (screenHeight / 2) - 100, new Color(0x282828));

        Map<Integer, Integer> gradient = RainbowGradientUtil.getGradient(50, 255, 255);
        RenderUtils2D.gradient((screenWidth / 2) - 170, (screenHeight / 2) + 100, (screenWidth / 2) + 170, (screenHeight / 2) + 95, gradient.get(1), gradient.get(200), true);

        String title = "developed by 9k#8583";
        float scale = 1f;
        int x = 2;
        int y = screenHeight - 10;
        GlStateManager.scale(scale, scale, scale);
        Astro.FONT_RENDERER.drawTextRainbow(title, (int)(x / scale), (int)(y / scale), new Color(gradient.get(1)), 40);
        GlStateManager.scale(1, 1, 1);

        title = "Astro Client";
        scale = 2f;
        x = (int) ((screenWidth / 2) - ((scale * Astro.FONT_RENDERER.getTextWidth(title)) / 2)) + 2;
        y = (screenHeight / 2) - 90;
        GlStateManager.scale(scale, scale, scale);
        Astro.FONT_RENDERER.drawTextRainbow(title, (int) (x / scale), (int) (y / scale), new Color(gradient.get(1)), 40);
        GlStateManager.scale(1, 1, 1);
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void initGui() {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
}
