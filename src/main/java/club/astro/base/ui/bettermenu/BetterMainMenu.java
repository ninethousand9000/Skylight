/*
package club.astro.base.ui.bettermenu;

import club.astro.Astro;
import club.astro.base.utils.color.RainbowGradientUtil;
import club.astro.base.utils.login.LoginUtil;
import club.astro.base.utils.render.RenderUtils2D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class BetterMainMenu extends GuiScreen {
    public GuiTextField usernameTypeBox;
    public GuiTextField passwordTypeBox;
    private final ResourceLocation background = new ResourceLocation("textures/astro/titlebg.png");

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution res = new ScaledResolution(mc);
        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

//        RenderUtils2D.drawRect(0, 0, screenWidth, screenHeight, new Color(0x383838));
        mc.getTextureManager().bindTexture(background);
        drawCompleteImage(0, 0, width, height);
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
        GlStateManager.disableRescaleNormal();

        if (mc.player != null) title = "Welcome, " + mc.player.getName();
        else title = "Logged in as: " + LoginUtil.username;

        scale = 0.5f;
        GlStateManager.scale(scale, scale, scale);
        x = (int) ((screenWidth / 2) - ((Astro.FONT_RENDERER.getTextWidth(title)) / 2)) + 2;
        y = (screenHeight / 2) - 65;
        Astro.FONT_RENDERER.drawText(title, (int) (x), (int) (y), Color.white);



        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public static void drawCompleteImage(float posX, float posY, float width, float height) {
        GL11.glPushMatrix();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(width, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public void initGui() {
        buttonList.add(new GuiButton(0, this.width / 2 - 100, height/2 + 50, "Sign-in"));
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

*/
