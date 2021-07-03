package com.skylight.client.modules.client;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.ui.clickgui.ClickGUI;
import org.lwjgl.input.Keyboard;

@ModuleAnnotation(category = ModuleCategory.Client, bind = Keyboard.KEY_O)
public class GUI extends Module {
    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
    }
}
