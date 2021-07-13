package com.skylight.base.events.events;

import com.skylight.base.events.CancellableEvent;
import com.skylight.base.events.EventType;
import net.minecraft.entity.MoverType;

public class MoveEvent extends CancellableEvent {
    private MoverType mover;
    private double x;
    private double y;
    private double z;

    public MoveEvent(MoverType mover, double x, double y, double z) {
        this.mover = mover;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MoverType getMover() {
        return mover;
    }

    public void setMover(MoverType mover) {
        this.mover = mover;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
