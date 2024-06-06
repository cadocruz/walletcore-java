package org.cadocruz.walletcore.infrastructure.client.dto;

public record CreateClientRequest(
        String name,
        String email
) {
    public static CreateClientRequest from(final String name, final String email) {
        return new CreateClientRequest(name, email);
    }
}
