package com.skylight.base.utils.game;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class Hole {
    protected BlockPos blockPos;
    protected boolean bedrock;
    protected List<Entity> entities;

    public Hole(BlockPos blockPos, boolean bedrock, List<Entity> entities) {
        this.blockPos = blockPos;
        this.bedrock = bedrock;
        this.entities = entities;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public boolean isBedrock() {
        return bedrock;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
