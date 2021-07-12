package com.skylight.base.utils.game;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public final class PlayerUtils implements Game {
    public static float getHealth() {
        return mc.player.getHealth() + mc.player.getAbsorptionAmount();
    }

    public static BlockPos getPlayerBlockPos() {
        return getPlayerBlockPos(mc.player);
    }

    public static BlockPos getPlayerBlockPos(EntityPlayer player) {
        return new BlockPos(Math.floor(player.posX), Math.floor(player.posY), Math.floor(player.posZ));
    }

    public static Vec3d getCenteredPos() {
        return new Vec3d(Math.floor(mc.player.posX) + 0.5, Math.floor(mc.player.posY) + 0.5, Math.floor(mc.player.posZ) + 0.5);
    }

    public static Vec3d getPlayerEyeVec() {
        return new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);
    }

    public static BlockPos[] getSurroundingBlocks() {
        Vec3d centeredPos = getCenteredPos();
        BlockPos[] returnValue = new BlockPos[4];

        int position = 0;
        for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
            returnValue[position++] = new BlockPos(centeredPos).offset(enumFacing);
        }

        return returnValue;
    }

    public static boolean canSeeEntity(Entity entity) {
        return mc.world.rayTraceBlocks(
                new Vec3d(
                        mc.player.posX,
                        mc.player.posY + mc.player.getEyeHeight(),
                        mc.player.posZ
                ),
                new Vec3d(
                        entity.posX,
                        entity.posY + (entity.height / 2),
                        entity.posZ)
        ) == null;
    }

    private PlayerUtils() {
        throw new UnsupportedOperationException();
    }
}
