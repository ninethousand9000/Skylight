package com.skylight.base.mixins.mixins;

import com.skylight.base.events.events.UpdateWalkingPlayerEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityPlayerSP.class}, priority=9998)
public abstract class MixinEntityPlayerSP {
    @Inject(method={"onUpdateWalkingPlayer"}, at={@At(value="HEAD")}, cancellable=true)
    private void preMotion(CallbackInfo info) {
        UpdateWalkingPlayerEvent.Pre event = new UpdateWalkingPlayerEvent.Pre();
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method={"onUpdateWalkingPlayer"}, at={@At(value="RETURN")})
    private void postMotion(CallbackInfo info) {
        UpdateWalkingPlayerEvent.Post event = new UpdateWalkingPlayerEvent.Post();
        MinecraftForge.EVENT_BUS.post(event);
    }
}
