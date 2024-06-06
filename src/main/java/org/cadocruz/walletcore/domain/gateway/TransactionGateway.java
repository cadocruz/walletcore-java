package org.cadocruz.walletcore.domain.gateway;

import org.cadocruz.walletcore.domain.models.Transaction;

public interface TransactionGateway {
    void create(Transaction transaction);
}
