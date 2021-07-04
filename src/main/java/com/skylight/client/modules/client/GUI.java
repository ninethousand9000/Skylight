package com.skylight.client.modules.client;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.Setting;
import com.skylight.base.ui.clickgui.ClickGUI;
import com.skylight.base.utils.color.PresetColors;
import org.lwjgl.input.Keyboard;

import java.awt.*;

@ModuleAnnotation(category = ModuleCategory.Client, bind = Keyboard.KEY_O)
public class GUI extends Module {
    public static final Setting<Color> normalColor = new Setting<>("NormalColor", PresetColors.SkylightBlue.color);
    public static final Setting<Boolean> gradientButtons = new Setting<>("GradientButtons", true);
    public static final Setting<Color> gradientTop = new Setting<>("GradientTop", PresetColors.SkylightBlue.color);
    public static final Setting<Color> gradientBottom = new Setting<>("GradientBottom", PresetColors.SkylightPink.color);

    public GUI() {
        registerSettings(normalColor, gradientButtons, gradientTop, gradientBottom);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
    }
}
