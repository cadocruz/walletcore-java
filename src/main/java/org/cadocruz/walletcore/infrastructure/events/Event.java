package org.cadocruz.walletcore.infrastructure.events;

import java.time.Instant;

public interface Event {
    String getName();
    Object getPayload();
    void setPayload(Object payload);
    Instant getDateTime();
}
