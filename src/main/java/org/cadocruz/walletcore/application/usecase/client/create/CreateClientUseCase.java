package org.cadocruz.walletcore.application.usecase.client.create;

import org.cadocruz.walletcore.application.UseCase;
import org.cadocruz.walletcore.domain.entity.Client;
import org.cadocruz.walletcore.domain.gateway.ClientGateway;

import java.util.Objects;

public class CreateClientUseCase extends UseCase<CreateClientInput, CreateClientOutput> {

    private final ClientGateway clientGateway;

    public CreateClientUseCase(final ClientGateway clientGateway) {
        this.clientGateway = Objects.requireNonNull(clientGateway);
    }

    @Override
    public CreateClientOutput execute(final CreateClientInput input) {
        final var name = input.name();
        final var email = input.email();

        final var client = Client.newClient(name, email);
        client.validate();
        final var aResult = clientGateway.create(client);
        return CreateClientOutput.from(aResult.getId());
    }
}
