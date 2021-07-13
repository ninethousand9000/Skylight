package com.skylight.client.modules.movement;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;

@ModuleAnnotation(category = ModuleCategory.Movement)
public class Sprint extends Module {
    public static final Setting<Mode> mode = new Setting<>("Mode", Mode.Rage);

    public Sprint() {
        registerParents(new ParentSetting("Settings", true, mode));
    }

    @Override
    public void onUpdate() {
        if (mode.getValue() == Mode.Legit) {
            if (mc.gameSettings.keyBindForward.isKeyDown()) {
                mc.player.setSprinting(true);
            }
        }
        else if (mode.getValue() == Mode.Rage) {
            mc.player.setSprinting(true);
        }
    }

    private enum Mode {
        Rage,
        Legit
    }
}
