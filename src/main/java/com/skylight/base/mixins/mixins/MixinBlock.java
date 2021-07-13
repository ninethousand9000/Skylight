package com.skylight.base.mixins.mixins;

import com.skylight.base.mixins.accessors.IBlock;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public class MixinBlock implements IBlock {
    @Shadow
    protected float blockHardness;

    @Override
    public float getBlockHardness() {
        return blockHardness;
    }
}
