package org.cadocruz.walletcore.infrastructure.events;

public interface EventHandler {
    void accept(Event event);
}
