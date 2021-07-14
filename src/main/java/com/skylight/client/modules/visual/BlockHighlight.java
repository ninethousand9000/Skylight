package com.skylight.client.modules.visual;

import com.skylight.base.events.events.RenderEvent3D;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.color.PresetColors;
import com.skylight.base.utils.render.NewRender3DUtils;
import com.skylight.base.utils.render.RenderUtils3D;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import java.awt.*;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class BlockHighlight extends Module {
    public static final Setting<NewRender3DUtils.RenderBoxMode> mode = new Setting<>("RenderMode", NewRender3DUtils.RenderBoxMode.Fancy);
    public static final Setting<Color> boxColor = new Setting<>("BoxColor", PresetColors.SkylightBlueAlpha.color);
    public static final NumberSetting<Float> outlineWidth = new NumberSetting<>("OutlineWidth", 0.5f, 2.0f, 5.0f, 1);

    public BlockHighlight() {
        registerParents(
                new ParentSetting("Settings", true, mode, boxColor, outlineWidth)
        );
    }

    @Override
    public void onRender3d(RenderEvent3D event) {
        if (nullCheck()) return;

        RayTraceResult result = mc.objectMouseOver;

        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos pos = result.getBlockPos();

            NewRender3DUtils.renderStandardBox(pos, boxColor.getValue(), mode.getValue(), 0.0f, outlineWidth.getValue());
        }
    }
}
