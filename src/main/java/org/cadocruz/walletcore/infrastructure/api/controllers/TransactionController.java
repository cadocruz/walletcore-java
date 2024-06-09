package org.cadocruz.walletcore.infrastructure.api.controllers;

import org.cadocruz.walletcore.application.usecase.transaction.create.CreateTransactionInput;
import org.cadocruz.walletcore.application.usecase.transaction.create.CreateTransactionUseCase;
import org.cadocruz.walletcore.infrastructure.api.TransactionAPI;
import org.cadocruz.walletcore.infrastructure.transaction.dto.CreateTransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TransactionController implements TransactionAPI {

    private final CreateTransactionUseCase createTransactionUseCase;

    public TransactionController(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @Override
    public ResponseEntity<?> createTransaction(CreateTransactionRequest input) {
        final var accountIdFrom = input.accountIdFrom();
        final var accountIdTo = input.accountIdTo();
        final var amount = input.amount();
        final var output = createTransactionUseCase.execute(CreateTransactionInput.from(accountIdFrom, accountIdTo, amount));

        return ResponseEntity.created(URI.create("/transactions/" + output.id())).body(output);
    }
}
