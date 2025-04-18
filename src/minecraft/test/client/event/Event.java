package test.client.event;

import test.client.Client;

public class Event {
    public final void call() {
        Client.INSTANCE.eventBus.post(this);
    }
}
