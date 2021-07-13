package com.skylight.base.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class CancellableEvent extends Event {

    boolean cancelled = false;

    EventType type;

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }
}
