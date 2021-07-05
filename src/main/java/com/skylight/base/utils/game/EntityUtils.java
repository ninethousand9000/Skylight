package com.skylight.base.utils.game;

import com.skylight.base.mixins.accessors.IRenderManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class EntityUtils implements Game {
    public static Vec3d getInterpolatedAmount(Entity entity, double x, double y, double z) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * x, 0 * y,
                (entity.posZ - entity.lastTickPosZ) * z);
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double ticks) {
        return getInterpolatedAmount(entity, ticks, ticks, ticks);
    }

    public static Vec3d getInterpolatedPos(Entity entity, float ticks) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ)
                .add(getInterpolatedAmount(entity, ticks));
    }

    public static Vec3d getInterpolatedRenderPos(Entity entity, float ticks) {
        RenderManager renderManager = mc.getRenderManager();
        return getInterpolatedPos(entity, ticks).subtract(
                ((IRenderManager)renderManager).getRenderPosX(),
                ((IRenderManager)renderManager).getRenderPosY(),
                ((IRenderManager)renderManager).getRenderPosZ());
    }
}
