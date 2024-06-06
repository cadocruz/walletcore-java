package org.cadocruz.walletcore.infrastructure.configuration.usecases;

import org.cadocruz.walletcore.application.usecase.client.create.CreateClientUseCase;
import org.cadocruz.walletcore.domain.gateway.ClientGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientUseCaseConfig {

    private final ClientGateway clientGateway;


    public ClientUseCaseConfig(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    @Bean
    public CreateClientUseCase createClientUseCase() {
        return new CreateClientUseCase(clientGateway);
    }
}
