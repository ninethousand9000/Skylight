package com.skylight.client.modules.visual;

import com.skylight.base.events.events.RenderEvent3D;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.color.PresetColors;
import com.skylight.base.utils.render.RenderUtils3D;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import java.awt.*;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class BlockHighlight extends Module {
    public static final Setting<RenderMode> mode = new Setting<>("Mode", RenderMode.Outline);
    public static final Setting<Color> boxColor = new Setting<>("BoxColor", PresetColors.SkylightBlue.color);
    public static final NumberSetting<Float> outlineWidth = new NumberSetting<>("OutlineWidth", 0.5f, 2.0f, 5.0f, 1);

    public BlockHighlight() {
        registerSettings(
                mode,
                boxColor,
                outlineWidth
        );
    }

    @Override
    public void render3D(RenderEvent3D event) {
        if (nullCheck()) return;

        RayTraceResult result = mc.objectMouseOver;

        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos pos = result.getBlockPos();

            if (mode.getValue() == RenderMode.Solid || mode.getValue() == RenderMode.Both) {
                RenderUtils3D.drawBox(pos, boxColor.getValue());
            }
            if (mode.getValue() == RenderMode.Outline || mode.getValue() == RenderMode.Both) {
                RenderUtils3D.drawBlockOutline(pos, boxColor.getValue(), outlineWidth.getValue());
            }
        }
    }

    private enum RenderMode {
        Solid,
        Outline,
        Both
    }
}
