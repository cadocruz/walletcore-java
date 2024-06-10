package org.cadocruz.walletcore.infrastructure.events.impl;

import org.cadocruz.walletcore.infrastructure.events.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Qualifier("balanceUpdated")
public class BalanceUpdatedEvent implements Event {

    private final String name;
    private Object payload;

    public BalanceUpdatedEvent() {
        super();
        this.name = "BalanceUpdated";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getPayload() {
        return this.payload;
    }

    @Override
    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public Instant getDateTime() {
        return Instant.now();
    }
}
