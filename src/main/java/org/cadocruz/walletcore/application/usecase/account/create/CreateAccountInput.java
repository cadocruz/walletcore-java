package org.cadocruz.walletcore.application.usecase.account.create;

import java.math.BigDecimal;

public record CreateAccountInput(
        String clientId,
        BigDecimal balance
) {
    public static CreateAccountInput from(final String clientId, final BigDecimal balance) {
        return new CreateAccountInput(clientId, balance);
    }
}
