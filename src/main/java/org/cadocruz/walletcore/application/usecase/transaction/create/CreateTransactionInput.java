package org.cadocruz.walletcore.application.usecase.transaction.create;

import java.math.BigDecimal;

public record CreateTransactionInput(
        String accountIdFrom,
        String accountIdTO,
        BigDecimal amount
) {
    public static CreateTransactionInput from(final String accountIdFrom, final String accountIdTO, final BigDecimal amount) {
        return new CreateTransactionInput(accountIdFrom, accountIdTO, amount);
    }
}
