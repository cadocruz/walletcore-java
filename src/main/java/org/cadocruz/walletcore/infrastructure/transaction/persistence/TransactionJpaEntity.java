package org.cadocruz.walletcore.infrastructure.transaction.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.cadocruz.walletcore.domain.models.Account;
import org.cadocruz.walletcore.domain.models.Transaction;
import org.cadocruz.walletcore.infrastructure.account.persistence.AccountJpaEntity;

import java.math.BigDecimal;
import java.time.Instant;

@Entity (name = "Transaction")
@Table (name = "transactions")
@Data
@AllArgsConstructor
public class TransactionJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id_from")
    private AccountJpaEntity accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_id_to")
    private AccountJpaEntity accountTo;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public TransactionJpaEntity() {}

    public static TransactionJpaEntity from(final Transaction transaction) {
        return new TransactionJpaEntity(
                transaction.getId(),
                AccountJpaEntity.from(transaction.getSender()),
                AccountJpaEntity.from(transaction.getRecipient()),
                transaction.getAmount(),
                transaction.getCreatedAt()
        );
    }

    public Transaction toAggregate() {
        return Transaction.with(
                this.id,
                Account.with(
                        this.accountFrom.getId(),
                        this.accountFrom.getClientId(),
                        this.accountFrom.getBalance(),
                        this.accountFrom.getCreatedAt(), this.accountFrom.getUpdatedAt()
                ),
                Account.with(
                        this.accountTo.getId(),
                        this.accountTo.getClientId(),
                        this.accountTo.getBalance(),
                        this.accountTo.getCreatedAt(), this.accountFrom.getUpdatedAt()
                ),
                this.amount,
                this.createdAt
                );
    }
}
