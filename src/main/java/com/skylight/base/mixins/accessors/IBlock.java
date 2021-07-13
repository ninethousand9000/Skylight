package com.skylight.base.mixins.accessors;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Block.class)
public interface IBlock {
    @Accessor("blockHardness")
    float getBlockHardness();
}
