package com.skylight.client.modules.visual;

import com.skylight.base.events.events.RenderEvent3D;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.color.PresetColors;
import com.skylight.base.utils.render.RenderUtils3D;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class VoidESP extends Module {
    public static final NumberSetting<Integer> range = new NumberSetting<>("Range", 0, 6, 20, 1);
    public static final NumberSetting<Float> lineWidth = new NumberSetting<>("Width", 0.5f, 1.5f, 5.0f, 1);
    public static final Setting<Color> voidColor = new Setting<>("VoidColor", PresetColors.SkylightPink.color);

    public VoidESP() {
        registerParents(new ParentSetting("Settings", true, range, lineWidth, voidColor));
    }

    public final List<BlockPos> voidBlocks = new ArrayList<>();

    @Override
    public void onUpdate() {
        if (nullCheck()) return;

        voidBlocks.clear();

        final Vec3i player_pos = new Vec3i(mc.player.posX, mc.player.posY, mc.player.posZ);

        for (int x = player_pos.getX() - range.getValue(); x < player_pos.getX() + range.getValue(); x++) {
            for (int z = player_pos.getZ() - range.getValue(); z < player_pos.getZ() + range.getValue(); z++) {
                for (int y = player_pos.getY() + range.getValue(); y > player_pos.getY() - range.getValue(); y--) {
                    final BlockPos blockPos = new BlockPos(x, y, z);

                    if (isVoidHole(blockPos) == HoleType.Void || isVoidHole(blockPos) == HoleType.Block)
                        voidBlocks.add(blockPos);
                }
            }
        }
    }

    @Override
    public void render3D(RenderEvent3D event) {
        new ArrayList<>(voidBlocks).forEach(pos -> {
            if (isVoidHole(pos) == HoleType.Void) RenderUtils3D.drawBox(pos, voidColor.getValue());
            RenderUtils3D.drawBlockOutline(pos, voidColor.getValue(), lineWidth.getValue());
        });
    }

    private HoleType isVoidHole(BlockPos blockPos) {
        if (blockPos.getY() != 0) return HoleType.None;

        if (mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK) return HoleType.None;

        if (mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) return HoleType.Void;

        return HoleType.Block;
    }

    private enum HoleType {
        Void,
        Block,
        None
    }
}
