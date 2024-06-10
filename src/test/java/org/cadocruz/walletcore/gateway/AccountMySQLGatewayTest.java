package org.cadocruz.walletcore.gateway;

import org.cadocruz.walletcore.MySQLGatewayTest;
import org.cadocruz.walletcore.domain.models.Account;
import org.cadocruz.walletcore.infrastructure.account.AccountMySQLGateway;
import org.cadocruz.walletcore.infrastructure.account.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

@MySQLGatewayTest
public class AccountMySQLGatewayTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMySQLGateway gateway;

    @BeforeEach
    public void cleanUp() {
        accountRepository.deleteAll();
    }

    @Test
    void givenAValidAccount_whenCallAccountGateway_thenSuccess() {
        final var expectedClientID = UUID.randomUUID().toString();
        final var expectedBalance = BigDecimal.valueOf(1000.0);

        final var account = Account.newAccount(expectedClientID, expectedBalance);

        Assertions.assertEquals(0, accountRepository.count());

        final var actualAccount= gateway.create(account);

        Assertions.assertEquals(1, accountRepository.count());
        Assertions.assertEquals(expectedClientID, actualAccount.getClientID());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertNotNull(actualAccount.getUpdatedAt());

        final var actualEntity = gateway.findById(actualAccount.getId()).get();

        Assertions.assertNotNull(actualEntity);
        Assertions.assertEquals(expectedClientID, actualEntity.getClientID());
        Assertions.assertEquals(expectedBalance, actualEntity.getBalance());
        Assertions.assertNotNull(actualEntity.getCreatedAt());
        Assertions.assertNotNull(actualEntity.getUpdatedAt());
    }
}
