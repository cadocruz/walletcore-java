package org.cadocruz.walletcore.application.usecase.client.create;

public record CreateClientOutput(
        String id
) {
    public static CreateClientOutput from(String id) {
        return new CreateClientOutput(id);
    }
}
