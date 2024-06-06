package org.cadocruz.walletcore.application.usecase.client.create;

public record CreateClientInput(
        String name,
        String email
) {
    public static CreateClientInput with(
            String aName,
            String aEmail
    ) {
        return new CreateClientInput(aName, aEmail);
    }
}
