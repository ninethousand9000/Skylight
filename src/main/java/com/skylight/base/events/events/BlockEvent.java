package com.skylight.base.events.events;

import com.skylight.base.events.CancellableEvent;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class BlockEvent extends CancellableEvent {
    public BlockPos pos;
    public EnumFacing facing;

    public BlockEvent(BlockPos pos, EnumFacing facing) {
        this.pos = pos;
        this.facing = facing;
    }
}
