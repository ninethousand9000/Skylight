package com.skylight.client.modules.visual;

import com.skylight.base.events.events.RenderEvent3D;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.mixins.accessors.IRenderGlobal;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.color.PresetColors;
import com.skylight.base.utils.render.RenderUtils3D;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.awt.*;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class BreakESP extends Module {
    public static final Setting<BreakMode> mode = new Setting<>("Mode", BreakMode.Grow);
    public static final Setting<Boolean> nametag = new Setting<>("Nametag", false);
    public static final Setting<Color> renderColor = new Setting<>("BoxColor", PresetColors.SkylightBlueAlpha.color);
    public static final NumberSetting<Float> outlineWidth = new NumberSetting<>("OutlineWidth", 0.5f, 2.0f, 5.0f, 1);
    public static final NumberSetting<Integer> range = new NumberSetting<>("Range", 0, 6, 20, 1);

    public BreakESP() {
        registerParents(
                new ParentSetting("Settings", true, mode, nametag, renderColor, outlineWidth, range)
        );
    }

    @Override
    public void render3D(RenderEvent3D event) {
        if (nullCheck()) return;

        ((IRenderGlobal) mc.renderGlobal).getDamagedBlocks().forEach((integer, destroyBlockProgress) -> {
            BlockPos pos = destroyBlockProgress.getPosition();
            if (destroyBlockProgress.getPosition().getDistance((int) mc.player.posX,(int)  mc.player.posY,(int)  mc.player.posZ) <= range.getValue()) {
                AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - mc.getRenderManager().viewerPosX, pos.getY() - mc.getRenderManager().viewerPosY, pos.getZ() - mc.getRenderManager().viewerPosZ, pos.getX() + 1 - mc.getRenderManager().viewerPosX, pos.getY() + 1 - mc.getRenderManager().viewerPosY, pos.getZ() + 1 - mc.getRenderManager().viewerPosZ);

                double sizeChange = 0;
                if (mode.getValue() == BreakMode.Grow) sizeChange = (10 - destroyBlockProgress.getPartialBlockDamage()) * 0.05;
                else if (mode.getValue() == BreakMode.Shrink) sizeChange = destroyBlockProgress.getPartialBlockDamage() * 0.05;
                else if (mode.getValue() == BreakMode.Both) sizeChange = destroyBlockProgress.getPartialBlockDamage() * 0.1;

                RenderUtils3D.drawBox(bb.shrink(sizeChange), renderColor.getValue());
                RenderUtils3D.drawBlockOutline(bb.shrink(sizeChange), renderColor.getValue(), outlineWidth.getValue());

                if (nametag.getValue()) {
                    try {
                        RenderUtils3D.drawNametagFromBlockPos(destroyBlockProgress.getPosition(), 0.6f, mc.world.getEntityByID(integer).getName());
                        RenderUtils3D.drawNametagFromBlockPos(destroyBlockProgress.getPosition(), 0.2f, (destroyBlockProgress.getPartialBlockDamage() * 10) + "%");
                    } catch (Exception ignored) {

                    }
                }
            }
        });
    }

    private enum BreakMode {
        Grow,
        Shrink,
        Both,
        None
    }
}
