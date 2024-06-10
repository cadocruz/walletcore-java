package org.cadocruz.walletcore.infrastructure.events.impl;

import org.cadocruz.walletcore.infrastructure.configuration.json.Json;
import org.cadocruz.walletcore.infrastructure.events.EventHandler;
import org.cadocruz.walletcore.infrastructure.events.EventService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BalanceUpdatedKafkaHandler implements EventHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BalanceUpdatedKafkaHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void handle(EventService event) {
        final var payload = Json.writeValueAsString(event.getPayload());
        kafkaTemplate.send("balances", payload);
    }
}
