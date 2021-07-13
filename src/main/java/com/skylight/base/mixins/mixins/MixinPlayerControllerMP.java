package com.skylight.base.mixins.mixins;

import com.skylight.base.events.EventType;
import com.skylight.base.events.events.BlockEvent;
import com.skylight.base.mixins.accessors.IRenderControllerMP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP implements IRenderControllerMP {

    @Inject(method = {"clickBlock"}, at = {@At(value = "HEAD")}, cancellable = true)
    private void clickBlockHook(BlockPos pos, EnumFacing face, CallbackInfoReturnable<Boolean> info) {
        BlockEvent event = new BlockEvent(pos, face);
        event.setType(EventType.Type3);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(method = {"onPlayerDamageBlock"}, at = {@At(value = "HEAD")}, cancellable = true)
    private void onPlayerDamageBlockHook(BlockPos pos, EnumFacing face, CallbackInfoReturnable<Boolean> info) {
        BlockEvent event = new BlockEvent(pos, face);
        event.setType(EventType.Type4);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.cancel();
        }
    }

    @Shadow
    private float curBlockDamageMP;

    @Shadow
    private boolean isHittingBlock;

    @Shadow
    private int blockHitDelay;

    @Override
    public float getCurBlockDamageMP() {
        return curBlockDamageMP;
    }

    @Override
    public boolean isHittingBlock() {
        return isHittingBlock;
    }

    @Override
    public void setHittingBlock(boolean hittingBlock) {
        isHittingBlock = hittingBlock;
    }

    @Override
    public void setBlockHitDelay(int blockHitDelay) {
        this.blockHitDelay = blockHitDelay;
    }
}
