package org.cadocruz.walletcore.infrastructure.api.controllers;

import org.cadocruz.walletcore.application.usecase.client.create.CreateClientInput;
import org.cadocruz.walletcore.application.usecase.client.create.CreateClientUseCase;
import org.cadocruz.walletcore.infrastructure.api.ClientAPI;
import org.cadocruz.walletcore.infrastructure.client.dto.CreateClientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ClientController implements ClientAPI {

    private final CreateClientUseCase createClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase) {
        this.createClientUseCase = createClientUseCase;
    }


    public ResponseEntity<?> createClient(CreateClientRequest request) {
        final var clientInput = CreateClientInput.with(request.name(), request.email());
        final var output = createClientUseCase.execute(clientInput);

        return ResponseEntity.created(URI.create("/clients/" + output.id())).body(output);
    }
}
