package com.skylight.client.modules.client;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;

@ModuleAnnotation(category = ModuleCategory.Client, alwaysEnabled = true)
public class Client extends Module {
    public static final Setting<String> name = new Setting<>("Name", "Skylight");
    public static final Setting<Boolean> limitValues = new Setting<>("Limit Values", true);

    public Client() {
        registerParents(
                new ParentSetting("Settings", true, name, limitValues)
        );
    }
}
