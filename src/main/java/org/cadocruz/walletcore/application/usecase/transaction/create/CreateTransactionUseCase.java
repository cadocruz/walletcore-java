package org.cadocruz.walletcore.application.usecase.transaction.create;

import org.cadocruz.walletcore.application.UseCase;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.cadocruz.walletcore.domain.gateway.TransactionGateway;
import org.cadocruz.walletcore.domain.models.Transaction;
import org.cadocruz.walletcore.infrastructure.events.EventDispatcher;
import org.cadocruz.walletcore.infrastructure.events.EventService;
import org.cadocruz.walletcore.infrastructure.transaction.dto.BalanceUpdatedOutputDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public class CreateTransactionUseCase extends UseCase<CreateTransactionInput, CreateTransactionOutput> {

    private final AccountGateway accountGateway;
    private final TransactionGateway transactionGateway;
    private final EventService transactionCreatedEvent;
    private final EventService balanceUpdatedEvent;
    private final EventDispatcher eventDispatcher;

    public CreateTransactionUseCase(
            final AccountGateway accountGateway,
            final TransactionGateway transactionGateway,
            @Qualifier("transactionCreated") final EventService transactionCreatedEvent,
            @Qualifier("balanceUpdated") final EventService balanceUpdatedEvent,
            final EventDispatcher eventDispatcher) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
        this.transactionGateway = Objects.requireNonNull(transactionGateway);
        this.transactionCreatedEvent = transactionCreatedEvent;
        this.balanceUpdatedEvent = balanceUpdatedEvent;
        this.eventDispatcher = eventDispatcher;
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public CreateTransactionOutput execute(CreateTransactionInput input) {
        final var accountIdFrom = input.accountIdFrom();
        final var accountIdTo = input.accountIdTO();
        final var amount = input.amount();

        final var accountFrom = accountGateway.findById(accountIdFrom).orElse(null);
        final var accountTo = accountGateway.findById(accountIdTo).orElse(null);

        assert accountFrom != null;
        assert accountTo != null;
        final var transaction = Transaction.newTransaction(accountFrom, accountTo, amount);
        accountGateway.update(accountFrom);
        accountGateway.update(accountTo);
        transactionGateway.create(transaction);
        CreateTransactionOutput output = CreateTransactionOutput.from(transaction);

        final var balanceUpdatedOutput =
                BalanceUpdatedOutputDTO.from(accountIdFrom, accountIdTo, accountFrom.getBalance(), accountTo.getBalance());

        transactionCreatedEvent.setPayload(output);
        balanceUpdatedEvent.setPayload(balanceUpdatedOutput);

        eventDispatcher.dispatch(transactionCreatedEvent);
        eventDispatcher.dispatch(balanceUpdatedEvent);
        return output;
    }
}
