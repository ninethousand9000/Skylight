package com.skylight.client.modules.movement;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;

@ModuleAnnotation(category = ModuleCategory.Movement)
public class ReverseStep extends Module {
    public static final Setting<Float> height = new NumberSetting<>("Height", 0.0f, 2.0f, 10.0f, 1);

    public ReverseStep() {
        registerParents(new ParentSetting("Settings", true, height));
    }

    @Override
    public void onUpdate() {
        if (nullCheck()) return;
        try {
            if (mc.player.isInLava() || mc.player.isInWater() || mc.player.isOnLadder()) return;
        } catch (Exception ignored) {
            return;
        }

        if (mc.player.onGround) { // idk if this makes the other checks invalid, probably does
            for (double y = 0.0; y < this.height.getValue() + 0.5; y += 0.01) {
                if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY = -10.0;
                    break;
                }
            }
        }
    }
}
