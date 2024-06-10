package org.cadocruz.walletcore.infrastructure;

import java.time.Instant;

public interface EventService {
    String getName();
    String getPayload();
    void setPayload(String payload);
    Instant getDateTime();
}
