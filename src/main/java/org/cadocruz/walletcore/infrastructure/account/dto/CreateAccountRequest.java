package org.cadocruz.walletcore.infrastructure.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @JsonProperty("client_id") String clientId,
        @JsonProperty("balance") BigDecimal balance
) {
}
