package com.skylight.base.events.events;

import com.skylight.base.events.CancellableEvent;
import net.minecraft.client.gui.ScaledResolution;

public class RenderEvent2D extends CancellableEvent {
    public float partialTicks;
    public ScaledResolution scaledResolution;

    public RenderEvent2D(float partialTicks, ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public void setScaledResolution(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }

    public double getScreenWidth() {
        return this.scaledResolution.getScaledWidth_double();
    }

    public double getScreenHeight() {
        return this.scaledResolution.getScaledHeight_double();
    }
}
