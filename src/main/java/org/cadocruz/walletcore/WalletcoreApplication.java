package org.cadocruz.walletcore;

import org.cadocruz.walletcore.infrastructure.events.EventDispatcher;
import org.cadocruz.walletcore.infrastructure.events.impl.BalanceUpdatedKafkaHandler;
import org.cadocruz.walletcore.infrastructure.events.impl.TransactionCreatedKafkaHandler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class WalletcoreApplication {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(WalletcoreApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, Object> template, EventDispatcher dispatcher)  {
        return args -> {
            dispatcher.register("TransactionCreated", new TransactionCreatedKafkaHandler(template));
            dispatcher.register("BalanceUpdated", new BalanceUpdatedKafkaHandler(template));
        };
    }

    //Bean
//    ApplicationRunner runner(ClientRepository repository) {
//        return args -> {
//            var clients = repository.findAll();
//
//            var client = Client.newClient("Teste", "teste@test.com");
//            repository.saveAndFlush(ClientJpaEntity.from(client));
//            repository.deleteAll();
//        };
//    }
}
