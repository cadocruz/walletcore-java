package org.cadocruz.walletcore.infrastructure.events.impl;

import org.cadocruz.walletcore.infrastructure.events.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Qualifier("transactionCreated")
public class TransactionCreatedEvent implements Event {

    private final String name;
    private Object payload;

    public TransactionCreatedEvent() {
        super();
        this.name = "TransactionCreated";
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
