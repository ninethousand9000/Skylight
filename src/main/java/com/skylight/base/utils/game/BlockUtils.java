package com.skylight.base.utils.game;

import com.skylight.base.mixins.accessors.IBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class BlockUtils implements Game {
    public static List<BlockPos> getSphere(BlockPos pos, float radius, int height, boolean hollow, boolean sphere, int yOffset) {
        List<BlockPos> circleBlocks = new ArrayList<>();

        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();

        int currentX = centerX - (int) radius;

        while ((float) currentX <= (float) centerX + radius) {
            int currentZ = centerZ - (int) radius;

            while ((float) currentZ <= (float) centerZ + radius) {
                int currentY = sphere ? centerY - (int) radius : centerY;

                while (true) {
                    float f2 = sphere ? (float) centerY + radius : (float) (centerY + height);

                    if (!(currentY < f2)) break;

                    double distance = (centerX - currentX) * (centerX - currentX) + (centerZ - currentZ) * (centerZ - currentZ) + (sphere ? (centerY - currentY) * (centerY - currentY) : 0);

                    if (!(!(distance < (double) (radius * radius)) || hollow && distance < (double) ((radius - 1.0f) * (radius - 1.0f))))
                        circleBlocks.add(new BlockPos(currentX, currentY + yOffset, currentZ));

                    ++currentY;
                }
                ++currentZ;
            }
            ++currentX;
        }

        return circleBlocks;
    }

    public static boolean isAir(BlockPos pos) {
        return mc.world.getBlockState(pos).getBlock() == Blocks.AIR;
    }

    public static double getMineTime(Block block, ItemStack stack){
        float speedMultiplier = stack.getDestroySpeed(block.getDefaultState());
        float damage;

        if (!mc.player.onGround) {
            speedMultiplier /= 5;
        }

        if (stack.canHarvestBlock(block.getDefaultState())) {
            damage = speedMultiplier / ((IBlock)block).getBlockHardness() / 30;
        } else {
            damage = speedMultiplier / ((IBlock)block).getBlockHardness()  / 100;
        }

        float ticks = (float) Math.ceil(1 / damage);

        try {
            return ticks / ServerUtils.getTPS();
        } catch (Exception ignored) {
            return ticks / 20;
        }
    }
}
