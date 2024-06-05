package org.cadocruz.walletcore.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaction {
    private String id;
    private Account sender;
    private Account recipient;
    private BigDecimal amount;
    private Instant createdAt;

    public static Transaction newTransaction(final Account sender, final Account recipient, final BigDecimal amount) {
        final var id = UUID.randomUUID().toString();
        Instant now = Instant.now();
        return new Transaction(id, sender, recipient, amount, now);
    }

    public void validate() {
        if (sender == null) {
            throw new IllegalArgumentException("Sender is required");
        }
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient is required");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (this.sender.getBalance().compareTo(this.amount) < 0) {
            throw new IllegalArgumentException("Sender balance is lower than amount");
        }
    }

    public void commit() {
        this.sender.debit(this.amount);
        this.recipient.credit(this.amount);
    }
}
