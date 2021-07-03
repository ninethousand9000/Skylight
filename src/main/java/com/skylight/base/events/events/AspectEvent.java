package com.skylight.base.events.events;

import com.skylight.base.events.CancellableEvent;

public class AspectEvent extends CancellableEvent {
    private double aspectRatio;

    public AspectEvent(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
