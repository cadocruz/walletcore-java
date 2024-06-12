package org.cadocruz.walletcore.infrastructure.configuration;

import org.cadocruz.walletcore.infrastructure.events.EventDispatcher;
import org.cadocruz.walletcore.infrastructure.events.impl.BalanceUpdatedKafkaHandler;
import org.cadocruz.walletcore.infrastructure.events.impl.TransactionCreatedKafkaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration(proxyBeanMethods = false)
@ComponentScan("org.cadocruz.walletcore")
public class WebServerConfig {

    @Bean
    EventDispatcher eventDispatcher(KafkaTemplate<String, Object> template) {
        var dispatcher = new EventDispatcher();
        dispatcher.register("TransactionCreated", new TransactionCreatedKafkaHandler(template));
        dispatcher.register("BalanceUpdated", new BalanceUpdatedKafkaHandler(template));
        return dispatcher;
    }
}
