package org.cadocruz.walletcore.entity;

import org.cadocruz.walletcore.domain.entity.Account;
import org.cadocruz.walletcore.domain.entity.Client;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    @Test
    public void givenValidParams_whenCallNewAccount_thenInstantiateAccount() {
        final var expectedClientID = UUID.randomUUID().toString();
        final var expectedBalance = BigDecimal.valueOf(1000.0);

        final var actualAccount = Account.newAccount(expectedClientID, expectedBalance);

        assertNotNull(actualAccount);
        assertNotNull(actualAccount.getId());
        assertEquals(expectedClientID, actualAccount.getClientID());
        assertEquals(expectedBalance, actualAccount.getBalance());
        assertNotNull(actualAccount.getCreatedAt());
        assertNotNull(actualAccount.getUpdatedAt());
    }

    @Test
    public void givenInvalidNullClientID_whenCallNewAccountAndValidate_thenShouldThrowException() {
        final String expectedClientID = null;
        final var expectedBalance = BigDecimal.valueOf(1000.0);
        final var expectedErrorMessage = "Client id is null or empty";

        final var actualAccount = Account.newAccount(expectedClientID, expectedBalance);

        final var actualException = assertThrows(RuntimeException.class, actualAccount::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidParams_whenCallDebit_thenReturnDebitToBalance() {
        final var expectedClientID = UUID.randomUUID().toString();
        final var expectedBalance = BigDecimal.valueOf(1000.0);
        final var expectedBalanceSubtracted = BigDecimal.valueOf(100.0);

        final var actualAccount = Account.newAccount(expectedClientID, expectedBalance);

        actualAccount.debit(expectedBalanceSubtracted);
        assertNotNull(actualAccount);
        assertNotNull(actualAccount.getId());
        assertEquals(expectedClientID, actualAccount.getClientID());
        assertEquals(900.0, actualAccount.getBalance().doubleValue());
    }

    @Test
    public void givenAValidParams_whenCallCredit_thenReturnCreditToBalance() {
        final var expectedClientID = UUID.randomUUID().toString();
        final var expectedBalance = BigDecimal.valueOf(1000.0);
        final var expectedBalanceAdd = BigDecimal.valueOf(100.0);

        final var actualAccount = Account.newAccount(expectedClientID, expectedBalance);

        actualAccount.credit(expectedBalanceAdd);
        assertNotNull(actualAccount);
        assertNotNull(actualAccount.getId());
        assertEquals(expectedClientID, actualAccount.getClientID());
        assertEquals(1100.0, actualAccount.getBalance().doubleValue());
    }

}
