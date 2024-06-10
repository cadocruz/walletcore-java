package org.cadocruz.walletcore.infrastructure.events;

import java.time.Instant;

public interface EventService {
    String getName();
    Object getPayload();
    void setPayload(Object payload);
    Instant getDateTime();
}
