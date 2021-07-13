package com.skylight.base.mixins.mixins;

import com.skylight.base.events.EventType;
import com.skylight.base.events.events.PushEvent;
import com.skylight.base.mixins.accessors.IEntity;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class MixinEntity implements IEntity {
    @Redirect(method={"applyEntityCollision"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void addVelocityHook(Entity entity, double x, double y, double z) {
        PushEvent event = new PushEvent(entity, x, y, z, true);
        event.setType(EventType.Type1);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (!event.isCanceled()) {
            entity.motionX += event.x;
            entity.motionY += event.y;
            entity.motionZ += event.z;
            entity.isAirBorne = event.airborne;
        }
    }

    @Shadow
    protected boolean isInWeb;

    @Override
    public boolean getIsInWeb() {
        return isInWeb;
    }

    @Override
    public void setIsInWeb(boolean isInWeb) {
        this.isInWeb = isInWeb;
    }
}
