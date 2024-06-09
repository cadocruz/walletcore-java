package org.cadocruz.walletcore.domain.gateway;

import org.cadocruz.walletcore.domain.models.Transaction;

public interface TransactionGateway {
    Transaction create(Transaction transaction);
}
