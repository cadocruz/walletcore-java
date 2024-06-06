package org.cadocruz.walletcore.gateway;

import org.cadocruz.walletcore.MySQLGatewayTest;
import org.cadocruz.walletcore.domain.models.Client;
import org.cadocruz.walletcore.infrastructure.client.ClientMySQLGateway;
import org.cadocruz.walletcore.infrastructure.client.persistence.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class MySQLClientGatewayTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMySQLGateway gateway;

    @BeforeEach
    public void cleanUp() {
        clientRepository.deleteAll();
    }

    @Test
    void givenAValidClient_whenCallClientGateway_thenSuccess() {
        final var expectedName = "John Doe";
        final var expectedEmail = "john.doe@example.com";

        final var client = Client.newClient(expectedName, expectedEmail);

        Assertions.assertEquals(0, clientRepository.count());

        final var actualClient = gateway.create(client);

        Assertions.assertEquals(1, clientRepository.count());
        Assertions.assertEquals(expectedName, actualClient.getName());
        Assertions.assertEquals(expectedEmail, actualClient.getEmail());
        Assertions.assertNotNull(actualClient.getCreatedAt());
        Assertions.assertNotNull(actualClient.getUpdatedAt());

        final var actualEntity = gateway.findById(actualClient.getId()).get();

        Assertions.assertNotNull(actualEntity);
        Assertions.assertEquals(expectedName, actualEntity.getName());
        Assertions.assertEquals(expectedEmail, actualEntity.getEmail());
        Assertions.assertNotNull(actualEntity.getCreatedAt());
        Assertions.assertNotNull(actualEntity.getUpdatedAt());
    }
}
