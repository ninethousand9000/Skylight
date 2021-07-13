package com.skylight.base.mixins.accessors;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SPacketEntityVelocity.class)
public interface ISPacketEntityVelocity {
    @Accessor("motionX")
    int getMotionX();

    @Accessor("motionY")
    int getMotionY();

    @Accessor("motionZ")
    int getMotionZ();

    @Accessor("motionX")
    void setMotionX(int motionX);

    @Accessor("motionY")
    void setMotionY(int motionY);

    @Accessor("motionZ")
    void setMotionZ(int motionZ);
}
