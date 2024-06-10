package org.cadocruz.walletcore.gateway;

import org.cadocruz.walletcore.MySQLGatewayTest;
import org.cadocruz.walletcore.domain.models.Account;
import org.cadocruz.walletcore.domain.models.Client;
import org.cadocruz.walletcore.domain.models.Transaction;
import org.cadocruz.walletcore.infrastructure.account.AccountMySQLGateway;
import org.cadocruz.walletcore.infrastructure.account.persistence.AccountRepository;
import org.cadocruz.walletcore.infrastructure.transaction.TransactionMySQLGateway;
import org.cadocruz.walletcore.infrastructure.transaction.persistence.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

@MySQLGatewayTest
public class TransactionMySQLGatewayTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMySQLGateway gateway;

    @Autowired
    AccountMySQLGateway accountGateway;

    @BeforeEach
    public void cleanUp() {
        transactionRepository.deleteAll();
    }

    @Test
    void givenAValidAccount_whenCallAccountGateway_thenSuccess() {
        final var expectedSender = Client.newClient("John Doe", "john@doe.com");
        final var expectedBalanceSender = BigDecimal.valueOf(100.0);
        final var expectedAccountSender = Account.newAccount(expectedSender.getId(), expectedBalanceSender);
        final var expectedRecipient = Client.newClient("Jane Doe", "jane@doe.com");
        final var expectedBalanceRecipient = BigDecimal.valueOf(50.0);
        final var expectedAccountRecipient = Account.newAccount(expectedRecipient.getId(), expectedBalanceRecipient);

        final var expectedAmount = BigDecimal.valueOf(10.0);
        accountGateway.create(expectedAccountSender);
        accountGateway.create(expectedAccountRecipient);

        final var actualTransaction = Transaction.newTransaction(expectedAccountSender, expectedAccountRecipient, expectedAmount);

        Assertions.assertEquals(0, transactionRepository.count());


        gateway.create(actualTransaction);

        Assertions.assertEquals(1, transactionRepository.count());
        Assertions.assertEquals(expectedAccountSender, actualTransaction.getSender());
        Assertions.assertEquals(expectedAccountRecipient, actualTransaction.getRecipient());
        Assertions.assertNotNull(actualTransaction.getCreatedAt());

     }
}
