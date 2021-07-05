package com.skylight.base.utils.rotation;

import com.skylight.Skylight;
import com.skylight.base.utils.game.Game;
import com.skylight.base.utils.math.MathUtils;
import com.skylight.base.utils.misc.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RotateManager implements Game {

    private static float yaw;
    private static float pitch;

    public static void updateRotations() {
        yaw = mc.player.rotationYaw;
        pitch = mc.player.rotationPitch;
    }

    public static void restoreRotations() {
        mc.player.rotationYaw = yaw;
        mc.player.rotationYawHead = yaw;
        mc.player.rotationPitch = pitch;
    }

    public static void setPlayerRotations(float yaw, float pitch) {
        mc.player.rotationYaw = yaw;
        mc.player.rotationYawHead = yaw;
        mc.player.rotationPitch = pitch;
    }

    public static void setPlayerYaw(float yaw) {
        mc.player.rotationYaw = yaw;
        mc.player.rotationYawHead = yaw;
    }

    public static void lookAtPos(BlockPos pos) {
        float[] angle = MathUtils.calcAngle(mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((float) pos.getX() + 0.5f, (float) pos.getY() + 0.5f, (float) pos.getZ() + 0.5f));
        setPlayerRotations(angle[0], angle[1]);
    }

    public static void lookAtVec3d(Vec3d vec3d) {
        float[] angle = MathUtils.calcAngle(mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        setPlayerRotations(angle[0], angle[1]);
    }

    public static void lookAtVec3d(double x, double y, double z) {
        Vec3d vec3d = new Vec3d(x, y, z);
        lookAtVec3d(vec3d);
    }

    public static void lookAtEntity(Entity entity) {
        float[] angle = MathUtils.calcAngle(mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionEyes(mc.getRenderPartialTicks()));
        setPlayerRotations(angle[0], angle[1]);
    }

    public static void setPlayerPitch(float pitch) {
        mc.player.rotationPitch = pitch;
    }

    public static float getYaw() {
        return RotateManager.yaw;
    }

    public static void setYaw(float yaw) {
        RotateManager.yaw = yaw;
    }

    public static float getPitch() {
        return RotateManager.pitch;
    }

    public static void setPitch(float pitch) {
        RotateManager.pitch = pitch;
    }

    public static int getDirection4D() {
        return RotationUtil.getDirection4D();
    }

    public static String getDirection4D(boolean northRed) {
        return RotationUtil.getDirection4D(northRed);
    }
}
