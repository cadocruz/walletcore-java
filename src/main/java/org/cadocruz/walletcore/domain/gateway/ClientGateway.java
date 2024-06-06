package org.cadocruz.walletcore.domain.gateway;

import org.cadocruz.walletcore.domain.entity.Client;

import java.util.Optional;

public interface ClientGateway {
    Client create(Client Client);

    void deleteById(String id);

    Optional<Client> findById(String id);

    Client update(Client Client);
}
