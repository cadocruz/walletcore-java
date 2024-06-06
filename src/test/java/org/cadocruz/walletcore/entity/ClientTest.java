package org.cadocruz.walletcore.entity;

import org.cadocruz.walletcore.domain.models.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void givenValidParams_whenCallNewClient_thenInstantiateClient() {
        final var expectedName = "John Doe";
        final var expectedEmail = "john@j.com";

        final var actualClient = Client.newClient(expectedName, expectedEmail);

        assertNotNull(actualClient);
        assertNotNull(actualClient.getId());
        assertEquals(expectedName, actualClient.getName());
        assertEquals(expectedEmail, actualClient.getEmail());
        assertNotNull(actualClient.getCreatedAt());
        assertNotNull(actualClient.getUpdatedAt());
    }

    @Test
    public void givenInvalidNullName_whenCallNewClientAndValidate_thenShouldThrowException() {
        final String expectedName = null;
        final var expectedEmail = "john@j.com";
        final var expectedErrorMessage = "Client name cannot be null or empty";
        final var actualCategory = Client.newClient(expectedName, expectedEmail);
        final var actualException = assertThrows(RuntimeException.class, actualCategory::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenInvalidNullEmail_whenCallNewClientAndValidate_thenShouldThrowException() {
        final String expectedName = "John Doe";
        final var expectedEmail = " ";
        final var expectedErrorMessage = "Client email cannot be null or empty";
        final var actualCategory = Client.newClient(expectedName, expectedEmail);
        final var actualException = assertThrows(RuntimeException.class, actualCategory::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

}
