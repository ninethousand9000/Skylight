package com.skylight.client.modules.visual;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class ItemPhysics extends Module {
    public static final NumberSetting<Float> itemScale = new NumberSetting<>("ItemScale", 0.1f, 0.5f, 10.0f, 1);

    public ItemPhysics() {
        registerParents(new ParentSetting("Settings", true, itemScale));
    }
}
