package club.astro.client.modules.client;

import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.ui.clickgui.ClickGUI;
import org.lwjgl.input.Keyboard;

@ModuleAnnotation(category = ModuleCategory.Client, bind = Keyboard.KEY_O)
public class GUI extends Module {
    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
    }
}
