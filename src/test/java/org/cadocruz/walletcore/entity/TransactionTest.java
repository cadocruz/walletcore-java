package org.cadocruz.walletcore.entity;

import org.cadocruz.walletcore.domain.models.Account;
import org.cadocruz.walletcore.domain.models.Client;
import org.cadocruz.walletcore.domain.models.Transaction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void givenValidParams_whenCallNewTransaction_thenInstantiateAccount() {
        final var expectedSender = Client.newClient("John Doe", "john@doe.com");
        final var expectedBalanceSender = BigDecimal.valueOf(100.0);
        final var expectedAccountSender = Account.newAccount(expectedSender.getId(), expectedBalanceSender);
        final var expectedRecipient = Client.newClient("Jane Doe", "jane@doe.com");
        final var expectedBalanceRecipient = BigDecimal.valueOf(50.0);
        final var expectedAccountRecipient = Account.newAccount(expectedRecipient.getId(), expectedBalanceRecipient);

        final var expectedAmount = BigDecimal.valueOf(10.0);

        final var actualTransaction = Transaction.newTransaction(expectedAccountSender, expectedAccountRecipient, expectedAmount);

        assertNotNull(actualTransaction);
        assertNotNull(actualTransaction.getId());
        assertEquals(90.0, expectedAccountSender.getBalance().doubleValue());
        assertEquals(60.0, expectedAccountRecipient.getBalance().doubleValue());

    }

    @Test
    public void givenInvalidNullRecipient_whenCallNewTransactionAndValidate_thenShouldThrowException() {
        final var expectedSender = Client.newClient("John Doe", "john@doe.com");
        final var expectedBalanceSender = BigDecimal.valueOf(100.0);
        final var expectedAccountSender = Account.newAccount(expectedSender.getId(), expectedBalanceSender);

        final Account expectedAccountRecipient = null;
        final var expectedErrorMessage = "Recipient is required";

        final var expectedAmount = BigDecimal.valueOf(10.0);

        final var actualException = assertThrows(RuntimeException.class, () -> Transaction.newTransaction(expectedAccountSender, expectedAccountRecipient, expectedAmount));

        assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    @Test
    public void givenInvalidNullSender_whenCallNewTransactionAndValidate_thenShouldThrowException() {
        final Account expectedAccountSender = null;
        final var expectedRecipient = Client.newClient("Jane Doe", "jane@doe.com");
        final var expectedBalanceRecipient = BigDecimal.valueOf(50.0);
        final var expectedAccountRecipient = Account.newAccount(expectedRecipient.getId(), expectedBalanceRecipient);
        final var expectedErrorMessage = "Sender is required";

        final var expectedAmount = BigDecimal.valueOf(10.0);

        final var actualException = assertThrows(RuntimeException.class, () ->  Transaction.newTransaction(expectedAccountSender, expectedAccountRecipient, expectedAmount));

        assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    @Test
    public void givenInvalidBalanceSender_whenCallNewTransactionAndValidate_thenShouldThrowException() {
        final var expectedSender = Client.newClient("John Doe", "john@doe.com");
        final var expectedBalanceSender = BigDecimal.valueOf(10.0);
        final var expectedAccountSender = Account.newAccount(expectedSender.getId(), expectedBalanceSender);
        final var expectedRecipient = Client.newClient("Jane Doe", "jane@doe.com");
        final var expectedBalanceRecipient = BigDecimal.valueOf(50.0);
        final var expectedAccountRecipient = Account.newAccount(expectedRecipient.getId(), expectedBalanceRecipient);

        final var expectedErrorMessage = "Sender balance is lower than amount";

        final var expectedAmount = BigDecimal.valueOf(100.0);

        final var actualException = assertThrows(RuntimeException.class, () ->  Transaction.newTransaction(expectedAccountSender, expectedAccountRecipient, expectedAmount));

        assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    @Test
    public void givenInvalidAmountSender_whenCallNewTransactionAndValidate_thenShouldThrowException() {
        final var expectedSender = Client.newClient("John Doe", "john@doe.com");
        final var expectedBalanceSender = BigDecimal.valueOf(100.0);
        final var expectedAccountSender = Account.newAccount(expectedSender.getId(), expectedBalanceSender);
        final var expectedRecipient = Client.newClient("Jane Doe", "jane@doe.com");
        final var expectedBalanceRecipient = BigDecimal.valueOf(50.0);
        final var expectedAccountRecipient = Account.newAccount(expectedRecipient.getId(), expectedBalanceRecipient);

        final var expectedErrorMessage = "Amount must be positive";

        final var expectedAmount = BigDecimal.valueOf(0.0);

        final var actualException = assertThrows(RuntimeException.class, () ->  Transaction.newTransaction(expectedAccountSender, expectedAccountRecipient, expectedAmount));

        assertEquals(expectedErrorMessage, actualException.getMessage());

    }

}
