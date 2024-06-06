package org.cadocruz.walletcore.application.usecase.transaction.create;

import org.cadocruz.walletcore.application.UseCase;
import org.cadocruz.walletcore.domain.entity.Account;
import org.cadocruz.walletcore.domain.entity.Transaction;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.cadocruz.walletcore.domain.gateway.TransactionGateway;

import java.util.Objects;

public class CreateTransactionUseCase extends UseCase<CreateTransactionInput, CreateTransactionOutput> {

    private final AccountGateway accountGateway;
    private final TransactionGateway transactionGateway;

    public CreateTransactionUseCase(final AccountGateway accountGateway, final TransactionGateway transactionGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
        this.transactionGateway = Objects.requireNonNull(transactionGateway);
    }


    @Override
    public CreateTransactionOutput execute(CreateTransactionInput input) {
        final var accountIdFrom = input.accountIdFrom();
        final var accountIdTo = input.accountIdTO();
        final var amount = input.amount();

        final var accountFrom = accountGateway.findById(accountIdFrom).orElse(null);
        final var accountTo = accountGateway.findById(accountIdTo).orElse(null);

        final var transaction = Transaction.newTransaction(accountFrom, accountTo, amount);
        transaction.validate();
        transaction.commit();
        transactionGateway.create(transaction);
        return CreateTransactionOutput.from(transaction);
    }
}
