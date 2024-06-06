package org.cadocruz.walletcore.domain.gateway;

import org.cadocruz.walletcore.domain.models.Client;

import java.util.Optional;

public interface ClientGateway {
    Client create(Client client);

    void deleteById(String id);

    Optional<Client> findById(String id);

    Client update(Client client);
}
