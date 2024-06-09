package org.cadocruz.walletcore.infrastructure.api.controllers;

import org.cadocruz.walletcore.application.usecase.account.create.CreateAccountInput;
import org.cadocruz.walletcore.application.usecase.account.create.CreateAccountUseCase;
import org.cadocruz.walletcore.infrastructure.account.dto.CreateAccountRequest;
import org.cadocruz.walletcore.infrastructure.api.AccountAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class AccountController implements AccountAPI {

    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @Override
    public ResponseEntity<?> createAccount(CreateAccountRequest input) {
        final var clientId = input.clientId();
        final var balance = input.balance();
        final var output = createAccountUseCase.execute(CreateAccountInput.from(clientId, balance));

        return ResponseEntity.created(URI.create("/accounts/" + output.id())).body(output);
    }
}
