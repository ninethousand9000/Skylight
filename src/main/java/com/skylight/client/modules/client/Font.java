package com.skylight.client.modules.client;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.render.font.FontMode;

@ModuleAnnotation(category = ModuleCategory.Client, enabledByDefault = true)
public class Font extends Module {
    public static final Setting<FontMode> customFont = new Setting<>("Font", FontMode.Verdana);
    public static final Setting<Boolean> shadow = new Setting<>("Shadow", true);
    public static final NumberSetting<Integer> size = new NumberSetting<>("Size", 10, 18, 30, 1);

    public Font() {
        registerParents(new ParentSetting("Setting", true, customFont, shadow, size));
    }

}
