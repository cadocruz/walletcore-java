package org.cadocruz.walletcore.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {
    private String id;
    private String clientID;
    private BigDecimal balance;
    private Instant createdAt;
    private Instant updatedAt;

    public static Account newAccount(String clientID, BigDecimal balance) {
        var id = UUID.randomUUID().toString();
        var now = Instant.now();
        return new Account(id, clientID, balance, now, now);
    }

    public void validate() {
        if (clientID == null || clientID.isBlank()) {
            throw new IllegalArgumentException("Client id is null or empty");
        }
    }

    public void debit(BigDecimal amount) {
        this.balance = balance.subtract(amount);
        this.updatedAt = Instant.now();
    }

    public void credit(BigDecimal amount) {
        this.balance = balance.add(amount);
        this.updatedAt = Instant.now();
    }
}
