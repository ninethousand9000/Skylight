package com.skylight.base.events.events;

import com.skylight.base.events.CancellableEvent;

public class RenderEvent3D extends CancellableEvent {
    private float partialTicks;

    public RenderEvent3D(final float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}