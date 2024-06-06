package org.cadocruz.walletcore.application.usecase.transaction.create;

import org.cadocruz.walletcore.domain.entity.Transaction;

import java.math.BigDecimal;

public record CreateTransactionOutput(
        String id,
        String accountIdFrom,
        String accountIdTo,
        BigDecimal amount
) {
    public static CreateTransactionOutput from(final Transaction transaction) {
        return new CreateTransactionOutput(transaction.getId(), transaction.getSender().getId(), transaction.getRecipient().getId(), transaction.getAmount());
    }
}
