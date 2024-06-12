package org.cadocruz.walletcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class WalletcoreApplication {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(WalletcoreApplication.class, args);
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
