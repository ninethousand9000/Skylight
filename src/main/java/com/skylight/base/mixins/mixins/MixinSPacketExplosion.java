package com.skylight.base.mixins.mixins;

import com.skylight.base.mixins.accessors.ISPacketEntityVelocity;
import com.skylight.base.mixins.accessors.ISPacketExplosion;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SPacketExplosion.class)
public class MixinSPacketExplosion implements ISPacketExplosion {
    @Shadow
    private float motionX;

    @Shadow
    private float motionY;

    @Shadow
    private float motionZ;

    @Override
    public float getMotionX() {
        return motionX;
    }

    @Override
    public float getMotionY() {
        return motionY;
    }

    @Override
    public float getMotionZ() {
        return motionZ;
    }

    @Override
    public void setMotionX(float motionX) {
        this.motionX = motionX;
    }

    @Override
    public void setMotionY(float motionY) {
        this.motionY = motionY;
    }

    @Override
    public void setMotionZ(float motionZ) {
        this.motionZ = motionZ;
    }
}

