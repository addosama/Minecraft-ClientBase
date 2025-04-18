package test.client.event;

public class CancellableEvent extends Event {
    private boolean cancelled;

    public CancellableEvent() {
        this.cancelled = false;
    }

    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public final boolean isCancelled() {
        return cancelled;
    }
}
