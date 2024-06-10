package org.cadocruz.walletcore.infrastructure.transaction.dto;

import java.math.BigDecimal;

public record BalanceUpdatedOutputDTO(
        String accountIdFrom,
        String accountIdTo,
        BigDecimal balanceAccountIdFrom,
        BigDecimal balanceAccountIdTo
) {
    public static BalanceUpdatedOutputDTO from(
            final String accountIdFrom,
            final String accountIdTo,
            final BigDecimal balanceAccountIdFrom,
            final BigDecimal balanceAccountIdTo)
    {
        return new BalanceUpdatedOutputDTO(accountIdFrom, accountIdTo, balanceAccountIdFrom, balanceAccountIdTo);
    }
}
