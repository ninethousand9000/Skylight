package com.skylight.base.mixins.mixins;

import com.skylight.base.mixins.accessors.IRenderGlobal;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal implements IRenderGlobal {
    @Shadow
    @Final
    private Map<Integer, DestroyBlockProgress> damagedBlocks;

    @Override
    public Map<Integer, DestroyBlockProgress> getDamagedBlocks() {
        return damagedBlocks;
    }
}
