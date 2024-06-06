package org.cadocruz.walletcore.infrastructure.client;

import org.cadocruz.walletcore.domain.gateway.ClientGateway;
import org.cadocruz.walletcore.domain.models.Client;
import org.cadocruz.walletcore.infrastructure.client.persistence.ClientJpaEntity;
import org.cadocruz.walletcore.infrastructure.client.persistence.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientMySQLGateway implements ClientGateway {
    private final ClientRepository clientRepository;

    public ClientMySQLGateway(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client create(Client client) {
        return clientRepository.saveAndFlush(ClientJpaEntity.from(client)).toAggregate();
    }

    @Override
    public void deleteById(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<Client> findById(String id) {
        return clientRepository.findById(id)
                .map(ClientJpaEntity::toAggregate);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.saveAndFlush(ClientJpaEntity.from(client)).toAggregate();
    }
}
