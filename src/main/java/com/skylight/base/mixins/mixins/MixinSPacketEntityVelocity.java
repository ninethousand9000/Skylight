package com.skylight.base.mixins.mixins;

import com.skylight.base.mixins.accessors.ISPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SPacketEntityVelocity.class)
public class MixinSPacketEntityVelocity implements ISPacketEntityVelocity {
    @Shadow
    private int motionX;

    @Shadow
    private int motionY;

    @Shadow
    private int motionZ;

    @Override
    public int getMotionX() {
        return motionX;
    }

    @Override
    public int getMotionY() {
        return motionY;
    }

    @Override
    public int getMotionZ() {
        return motionZ;
    }

    @Override
    public void setMotionX(int motionX) {
        this.motionX = motionX;
    }

    @Override
    public void setMotionY(int motionY) {
        this.motionY = motionY;
    }

    @Override
    public void setMotionZ(int motionZ) {
        this.motionZ = motionZ;
    }
}
