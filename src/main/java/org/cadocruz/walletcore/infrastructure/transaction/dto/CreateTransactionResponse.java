package org.cadocruz.walletcore.infrastructure.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.cadocruz.walletcore.domain.models.Transaction;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateTransactionResponse(
        @JsonProperty("id") String id,
        @JsonProperty("account_id_from") String accountIdFrom,
        @JsonProperty("account_id_to") String accountIdTO,
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("created_at") Instant createdAt
        )
{
    public static CreateTransactionResponse from(final Transaction transaction) {
        return new CreateTransactionResponse(
                transaction.getId(),
                transaction.getSender().getId(),
                transaction.getRecipient().getId(),
                transaction.getAmount(),
                transaction.getCreatedAt()
        );
    }
}
