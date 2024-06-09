package org.cadocruz.walletcore.infrastructure.transaction.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionJpaEntity, String> {
}
