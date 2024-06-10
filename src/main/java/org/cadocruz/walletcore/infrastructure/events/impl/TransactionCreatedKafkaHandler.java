package org.cadocruz.walletcore.infrastructure.events.impl;

import org.cadocruz.walletcore.infrastructure.configuration.json.Json;
import org.cadocruz.walletcore.infrastructure.events.EventHandler;
import org.cadocruz.walletcore.infrastructure.events.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionCreatedKafkaHandler implements EventHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public TransactionCreatedKafkaHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void accept(Event event) {
        final var payload = Json.writeValueAsString(event.getPayload());
        kafkaTemplate.send("transactions", payload);
    }
}
