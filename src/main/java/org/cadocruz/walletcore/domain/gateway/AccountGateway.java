package org.cadocruz.walletcore.domain.gateway;

import org.cadocruz.walletcore.domain.entity.Account;

import java.util.Optional;

public interface AccountGateway {
    Account create(Account Account);

    void deleteById(String id);

    Optional<Account> findById(String id);

    Account update(Account Account);
}
