package com.skylight.client.modules.client;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.render.font.FontMode;

@ModuleAnnotation(category = ModuleCategory.Client, enabledByDefault = true)
public class Font extends Module {
    public static final Setting<FontMode> customFont = new Setting<>("Font", FontMode.VERDANA);

    public Font() {
        registerSettings(customFont);
    }
}
