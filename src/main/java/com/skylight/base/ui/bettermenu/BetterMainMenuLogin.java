package com.skylight.base.ui.bettermenu;

import com.skylight.Skylight;
import com.skylight.base.utils.color.RainbowGradientUtil;
import com.skylight.base.utils.login.LoginUtil;
import com.skylight.base.utils.render.RenderUtils2D;
import com.skylight.base.utils.render.font.FontUtils;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Map;

public class BetterMainMenuLogin extends GuiScreen {
    public static boolean loggedIn = false;
    public GuiTextField usernameTypeBox;
    public GuiTextField passwordTypeBox;
    private final ResourceLocation background = new ResourceLocation("textures/skylight/titlebg.png");

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
        int x = 2;
        int y = screenHeight - 10;
        FontUtils.drawRainbowString(title, x, y, new Color(gradient.get(1)), 40);
        GlStateManager.scale(1, 1, 1);

        title = "Skylight";
        x = (int) ((screenWidth / 2) - ((FontUtils.getStringWidth(title)) / 2)) + 2;
        y = (screenHeight / 2) - 90;
        FontUtils.drawRainbowString(title, (int) (x), (int) (y), new Color(gradient.get(1)), 40);
        GlStateManager.scale(1, 1, 1);
        GlStateManager.disableRescaleNormal();

        if (mc.player != null) title = "Welcome, " + mc.player.getName();
        else title = "Welcome to Skylight " + Skylight.MOD_VERSION;

        x = (int) ((screenWidth / 2) - ((FontUtils.getStringWidth(title)) / 2)) + 2;
        y = (screenHeight / 2) - 65;
        FontUtils.drawString(title, (int) (x), (int) (y), Color.white);

        title = "Please sign-in to continue";
        x = (int) ((screenWidth / 2) - ((FontUtils.getStringWidth(title)) / 2)) + 2;
        y = (screenHeight / 2) - 56;
        FontUtils.drawString(title, (int) (x), (int) (y), Color.white);


        title = "Username";
        x = width / 2 - 100;
        y = (screenHeight / 2) - 40;
        FontUtils.drawString(title, (int) (x), (int) (y), Color.white);

        usernameTypeBox.drawTextBox();

        title = "Password";
        x = width / 2 - 100;
        y = (screenHeight / 2);
        FontUtils.drawString(title, (int) (x), (int) (y), Color.white);

        passwordTypeBox.drawTextBox();

        buttonList.get(0).drawButton(mc, mouseX, mouseY, partialTicks);



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
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(0, this.width / 2 - 100, height/2 + 50, "Sign-in"));
        buttonList.add(new GuiButton(1, 2, 2, "Request new login"));
        usernameTypeBox =  new GuiTextField(0, this.fontRenderer, width / 2 - 100, (height/2) - 29, 200, 20);
        usernameTypeBox.setMaxStringLength(128);
        usernameTypeBox.setText("");
        usernameTypeBox.setFocused(true);
        passwordTypeBox =  new GuiTextField(1, this.fontRenderer, width / 2 - 100, (height/2) + 11, 200, 20);
        passwordTypeBox.setMaxStringLength(128);
        passwordTypeBox.setText("");
        passwordTypeBox.setFocused(false);
        buttonList.get(0).enabled = false;
        buttonList.get(1).enabled = false;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (this.usernameTypeBox.textboxKeyTyped(typedChar, keyCode) || this.passwordTypeBox.textboxKeyTyped(typedChar, keyCode))
        {
            (this.buttonList.get(0)).enabled = (!this.usernameTypeBox.getText().isEmpty() && this.usernameTypeBox.getText().split(":").length > 0) && (!this.passwordTypeBox.getText().isEmpty() && this.passwordTypeBox.getText().split(":").length > 0);
            (this.buttonList.get(1)).enabled = (!this.usernameTypeBox.getText().isEmpty() && this.usernameTypeBox.getText().split(":").length > 0) && (!this.passwordTypeBox.getText().isEmpty() && this.passwordTypeBox.getText().split(":").length > 0);
        }
        else if (keyCode == 28 || keyCode == 156)
        {
            this.actionPerformed(this.buttonList.get(0));
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        usernameTypeBox.mouseClicked(mouseX, mouseY, mouseButton);
        passwordTypeBox.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 0) {
                boolean validLogin = LoginUtil.login(usernameTypeBox.getText(), passwordTypeBox.getText());

                Skylight.log(usernameTypeBox.getText() + "," +  passwordTypeBox.getText());

                if (validLogin) {
                    Skylight.log("Login success");
                    loggedIn = true;
                    mc.displayGuiScreen(new GuiMainMenu());
                }
                else {
                    Skylight.log("Login invalid");
                }
            }

            if (button.id == 1) {
                String genLogin = LoginUtil.getNewLogin(usernameTypeBox.getText(), passwordTypeBox.getText());
                StringSelection stringSelection = new StringSelection(genLogin);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        }
    }

    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
}
