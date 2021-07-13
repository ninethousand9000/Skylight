package com.skylight.base.mixins.accessors;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerControllerMP.class)
public interface IRenderControllerMP {
    @Accessor("curBlockDamageMP")
    float getCurBlockDamageMP();

    @Accessor("isHittingBlock")
    boolean isHittingBlock();

    @Accessor("isHittingBlock")
    void setHittingBlock(boolean hittingBlock);

    @Accessor("blockHitDelay")
    void setBlockHitDelay(int blockHitDelay);
}
