package org.cadocruz.walletcore.domain.gateway;

import org.cadocruz.walletcore.domain.models.Account;

import java.util.Optional;

public interface AccountGateway {
    Account create(Account account);

    void deleteById(String id);

    Optional<Account> findById(String id);

    Account update(Account account);
}
