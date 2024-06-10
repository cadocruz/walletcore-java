package org.cadocruz.walletcore.infrastructure.events;

import org.cadocruz.walletcore.infrastructure.EventService;

import java.time.Instant;

public class TransactionCreated implements EventService {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getPayload() {
        return "";
    }

    @Override
    public void setPayload(String payload) {

    }

    @Override
    public Instant getDateTime() {
        return null;
    }
}
