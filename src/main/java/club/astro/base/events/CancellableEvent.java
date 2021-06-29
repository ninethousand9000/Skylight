package club.astro.base.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class CancellableEvent extends Event {

    boolean cancelled = false;

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
