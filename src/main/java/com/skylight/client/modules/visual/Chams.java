package com.skylight.client.modules.visual;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.Setting;

import java.awt.*;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class Chams extends Module {
    public static final Setting<Color> c = new Setting<>("Color", new Color(0x2525CB));

    public Chams() {
        registerSettings(c);
    }
}
