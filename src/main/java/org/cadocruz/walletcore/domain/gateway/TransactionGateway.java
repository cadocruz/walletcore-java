package org.cadocruz.walletcore.domain.gateway;

import org.cadocruz.walletcore.domain.entity.Transaction;

public interface TransactionGateway {
    void create(Transaction transaction);
}
