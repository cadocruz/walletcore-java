package org.cadocruz.walletcore.infrastructure.account.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.cadocruz.walletcore.domain.models.Account;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity(name = "Account")
@Table(name = "accounts")
@AllArgsConstructor
public class AccountJpaEntity {
    @Id
    @Column(name = "id", nullable = true)
    private String id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public AccountJpaEntity() {}

    public static AccountJpaEntity from(Account account) {
        return new AccountJpaEntity(account.getId(), account.getClientID(), account.getBalance(), account.getCreatedAt(), account.getUpdatedAt());
    }

    public Account toAggregate() {
        return Account.with(getId(), getClientId(), getBalance(), getCreatedAt(), getUpdatedAt());
    }

}
