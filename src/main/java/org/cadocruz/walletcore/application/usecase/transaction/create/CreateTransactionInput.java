package org.cadocruz.walletcore.application.usecase;

import java.math.BigDecimal;

public record CreateTransactionInput(
        String accountIdFrom,
        String accountIdTO,
        BigDecimal amount
) { }
