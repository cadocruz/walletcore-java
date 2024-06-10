package org.cadocruz.walletcore.infrastructure.events;

public interface EventHandler {
    void handle(EventService event);
}
