package org.cadocruz.walletcore.infrastructure.events;

public interface EventDispatcher {
    void register(String eventName, EventHandler handler);
    void dispatch(EventService event);
    void unregister(String eventName, EventHandler handler);
    void unregisterAll();
    boolean has(String eventName, EventHandler handler);
}
