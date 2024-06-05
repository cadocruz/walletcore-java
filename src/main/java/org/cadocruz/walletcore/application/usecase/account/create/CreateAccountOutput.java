package org.cadocruz.walletcore.application.usecase.client.create;

public record CreateAccountOutput(
        String id
) {
    public static CreateAccountOutput from(String id) {
        return new CreateAccountOutput(id);
    }
}
