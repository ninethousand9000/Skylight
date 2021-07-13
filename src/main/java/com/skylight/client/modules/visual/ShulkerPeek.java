package com.skylight.client.modules.visual;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class ShulkerPeek extends Module {
    public ShulkerPeek() {
        registerParents();
    }
}
