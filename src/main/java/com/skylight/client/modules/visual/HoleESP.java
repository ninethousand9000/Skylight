package com.skylight.client.modules.visual;

import com.skylight.base.events.events.RenderEvent3D;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.color.PresetColors;
import com.skylight.base.utils.game.BlockUtils;
import com.skylight.base.utils.game.Game;
import com.skylight.base.utils.game.Hole;
import com.skylight.base.utils.game.PlayerUtils;
import com.skylight.base.utils.render.RenderUtils3D;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.util.ArrayList;

@ModuleAnnotation(category = ModuleCategory.Visual)
public class HoleESP extends Module {
    public static final NumberSetting<Integer> range = new NumberSetting<>("Range", 0, 6, 20, 1);
    public static final NumberSetting<Float> lineWidth = new NumberSetting<>("Width", 0.5f, 1.5f, 5.0f, 1);
    public static final NumberSetting<Float> boxHeight = new NumberSetting<>("BoxHeight", 0.1f, 1.0f, 3.0f, 1);
    public static final Setting<Color> bedrockColor = new Setting<>("BedrockColor", PresetColors.SkylightBlueAlpha.color);
    public static final Setting<Color> obsidianColor = new Setting<>("ObbyColor", PresetColors.SkylightPinkAlpha.color);
    public static final Setting<RenderMode> renderMode = new Setting<>("RenderMode", RenderMode.Pretty);

    public static final MultiThread holeThread = new MultiThread();

    public HoleESP() {
        registerParents(
                new ParentSetting("Settings", true, range),
                new ParentSetting("Render", false, lineWidth, boxHeight, bedrockColor, obsidianColor, renderMode)
                );
    }

    @Override
    public void onEnable() {
        holeThread.start();
    }

    @Override
    public void onDisable() {
        holeThread.interrupt();
    }

    @Override
    public void render3D(RenderEvent3D event) {
        new ArrayList<>(MultiThread.holes).forEach(hole -> {
            Color color = hole.isBedrock() ? bedrockColor.getValue() : obsidianColor.getValue();
            if (renderMode.getValue() == RenderMode.Outline || renderMode.getValue() == RenderMode.Pretty) {
                RenderUtils3D.drawBlockOutline(hole.getBlockPos(), color, lineWidth.getValue(), boxHeight.getValue());
            }
            if (renderMode.getValue() == RenderMode.Solid || renderMode.getValue() == RenderMode.Pretty) {
                RenderUtils3D.drawBox(hole.getBlockPos(), color, boxHeight.getValue());
            }
        });
    }

    private enum RenderMode {
        Outline,
        Solid,
        Pretty,
        Gradient
    }

    protected static final class MultiThread extends Thread implements Game {
        public static final ArrayList<Hole> holes = new ArrayList<>();

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(50);
                    holes.clear();
                    if (!(mc.player == null || mc.world == null)) {
                        for (BlockPos blockPos : BlockUtils.getSphere(PlayerUtils.getPlayerBlockPos(), range.getValue(), range.getValue(), false, true, 0)) {
                            boolean hole = true;
                            boolean bedrock = true;

                            if (BlockUtils.isAir(blockPos) && BlockUtils.isAir(blockPos.up()) && BlockUtils.isAir(blockPos.up().up())) {

                                for (EnumFacing facingDirection : EnumFacing.values()) {
                                    if (facingDirection != EnumFacing.UP) {
                                        if (mc.world.getBlockState(blockPos.offset(facingDirection)).getBlock() != Blocks.BEDROCK) {
                                            bedrock = false;

                                            if (mc.world.getBlockState(blockPos.offset(facingDirection)).getBlock() != Blocks.OBSIDIAN) {
                                                hole = false;
                                            }
                                        }
                                    }
                                }
                            }

                            else {
                                hole = false;
                            }

                            if (hole) holes.add(new Hole(blockPos, bedrock, mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))));
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
