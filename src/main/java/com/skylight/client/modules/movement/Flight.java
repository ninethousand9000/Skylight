package com.skylight.client.modules.movement;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;

@ModuleAnnotation(category = ModuleCategory.Movement)
public class Flight extends Module {

    public Flight() {

    }

    @Override
    public void onEnable() {
        if (nullCheck()) return;

        mc.player.capabilities.isFlying = true;
        if (mc.player.capabilities.isCreativeMode) {
            return;
        }
        mc.player.capabilities.allowFlying = true;
    }

    @Override
    public void onDisable() {
        if (nullCheck()) return;

        mc.player.capabilities.isFlying = false;
        mc.player.capabilities.setFlySpeed(0.05f);
        if (mc.player.capabilities.isCreativeMode) {
            return;
        }
        mc.player.capabilities.allowFlying = false;
    }
}
