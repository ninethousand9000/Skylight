package com.skylight.base.events.events;

import com.skylight.base.events.CancellableEvent;
import net.minecraft.entity.Entity;

public class PushEvent extends CancellableEvent {
    public Entity entity;
    public double x;
    public double y;
    public double z;
    public boolean airborne;

    public PushEvent() {}

    public PushEvent(Entity entity, double x, double y, double z, boolean airborne) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.airborne = airborne;
    }

    public PushEvent(Entity entity) {
        this.entity = entity;
    }
}
