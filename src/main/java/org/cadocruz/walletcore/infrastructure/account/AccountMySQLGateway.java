package org.cadocruz.walletcore.infrastructure.account;

import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.cadocruz.walletcore.domain.models.Account;
import org.cadocruz.walletcore.infrastructure.account.persistence.AccountJpaEntity;
import org.cadocruz.walletcore.infrastructure.account.persistence.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountMySQLGateway implements AccountGateway {

    private final AccountRepository accountRepository;

    public AccountMySQLGateway(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.saveAndFlush(AccountJpaEntity.from(account)).toAggregate();
    }

    @Override
    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id)
                .map(AccountJpaEntity::toAggregate);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.saveAndFlush(AccountJpaEntity.from(account)).toAggregate();
    }
}
