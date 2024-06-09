package org.cadocruz.walletcore.infrastructure.transaction;

import org.cadocruz.walletcore.domain.gateway.TransactionGateway;
import org.cadocruz.walletcore.domain.models.Transaction;
import org.cadocruz.walletcore.infrastructure.transaction.persistence.TransactionJpaEntity;
import org.cadocruz.walletcore.infrastructure.transaction.persistence.TransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionMySQLGateway implements TransactionGateway {

    private final TransactionRepository transactionRepository;

    public TransactionMySQLGateway(TransactionRepository transactionGateway) {
        this.transactionRepository = transactionGateway;
    }

    @Override
    public Transaction create(final Transaction transaction) {
        return transactionRepository.saveAndFlush(TransactionJpaEntity.from(transaction)).toAggregate();
    }
}
