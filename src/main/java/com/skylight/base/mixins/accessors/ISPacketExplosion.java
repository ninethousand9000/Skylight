package com.skylight.base.mixins.accessors;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SPacketExplosion.class)
public interface ISPacketExplosion {
    @Accessor("motionX")
    float getMotionX();

    @Accessor("motionY")
    float getMotionY();

    @Accessor("motionZ")
    float getMotionZ();

    @Accessor("motionX")
    void setMotionX(float motionX);

    @Accessor("motionY")
    void setMotionY(float motionY);

    @Accessor("motionZ")
    void setMotionZ(float motionZ);
}
