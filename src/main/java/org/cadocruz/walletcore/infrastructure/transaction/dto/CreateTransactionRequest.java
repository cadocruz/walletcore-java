package org.cadocruz.walletcore.infrastructure.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CreateTransactionRequest(
        @JsonProperty("account_id_from") String accountIdFrom,
        @JsonProperty("account_id_to") String accountIdTo,
        @JsonProperty("amount") BigDecimal amount
        ) {
}
