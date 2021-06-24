package club.astro.base.ui.clickgui;

import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.ui.clickgui.components.Panel;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class ClickGUI extends GuiScreen {
    public static boolean leftClicked = false, leftDown = false, rightClicked = false, rightDown = false;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int x = 10;
        for (ModuleCategory category : ModuleCategory.values()) {
            Panel panel = new Panel(x, 10, 100, 20, category,
                    new Color(0xB5575757, true), new Color(0xBCD417D4, true), new Color(0xFFFFFF), new Color(0x841A84),
                    mouseX, mouseY);
            x += 110;
        }

        leftClicked = false;
        rightClicked = false;
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
}
