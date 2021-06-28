package club.astro.base.ui.clickgui;

import club.astro.Astro;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.ui.clickgui.components.Panel;
import club.astro.client.modules.client.GUI;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClickGUI extends GuiScreen {
    public static boolean leftClicked = false, leftDown = false, rightClicked = false, rightDown = false;
    public static final ArrayList<Panel> panels = new ArrayList<>();
    public static int startY = 10;

    public static char typedChar;
    public static int keyCode;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        update(mouseX, mouseY);

        leftClicked = false;
        rightClicked = false;
    }

    @Override
    public void initGui() {
        int x = 10;
        panels.clear();
        for (ModuleCategory category : ModuleCategory.values()) {
            panels.add(new Panel(x, 10, 110, 18, category,
                    new Color(0xBB2020), new Color(0xB5575757, true), new Color(0xBCD417D4, true), new Color(0xFFFFFF), new Color(0x841A84)));
            x += 111;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        ClickGUI.typedChar = typedChar;
        ClickGUI.keyCode = keyCode;
    }

    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0) {
            leftClicked = true;
            leftDown = true;
        }

        if (mouseButton == 1) {
            rightClicked = true;
            rightDown = true;
        }
    }

    @Override
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        if (state == 0) {
            leftClicked = false;
            leftDown = false;
        }

        if (state == 1) {
            rightClicked = false;
            rightDown = false;
        }
    }

    public void update(int mouseX, int mouseY) {
        scroll();
        for (Panel panel : panels) {
            panel.mouseX = mouseX;
            panel.mouseY = mouseY;
            panel.posY = startY;
            panel.draw();
        }
    }

    public void scroll() {
        int scrollWheel = Mouse.getDWheel();

        if (scrollWheel < 0) {
            startY += 4;
        }
        else if (scrollWheel > 0) {
            startY -= 4;
        }
    }

    @Override
    public void onGuiClosed() {
        Astro.MODULE_MANAGER.getModule(GUI.class).setEnabled(false);
    }
}
