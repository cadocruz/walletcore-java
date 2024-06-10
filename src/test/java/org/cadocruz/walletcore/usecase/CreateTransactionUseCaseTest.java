package org.cadocruz.walletcore.usecase;

import org.cadocruz.walletcore.application.usecase.transaction.create.CreateTransactionInput;
import org.cadocruz.walletcore.application.usecase.transaction.create.CreateTransactionUseCase;
import org.cadocruz.walletcore.domain.models.Account;
import org.cadocruz.walletcore.domain.models.Client;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.cadocruz.walletcore.domain.gateway.TransactionGateway;
import org.cadocruz.walletcore.infrastructure.events.EventDispatcher;
import org.cadocruz.walletcore.infrastructure.events.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTransactionUseCaseTest {

    @Mock
    private AccountGateway accountGateway;
    @Mock
    private TransactionGateway transactionGateway;
    @Mock
    private Event transactionCreatedEvent;
    @Mock
    private EventDispatcher eventDispatcher;
    @InjectMocks
    private CreateTransactionUseCase useCase;

    @Test
    void givenAValidInput_whenCreateTransactionUseCase_thenReturnATransactionCreated() {
        final var expectedSender = Client.newClient("John Doe", "john@doe.com");
        final var expectedBalanceSender = BigDecimal.valueOf(100.0);
        final var expectedAccountSender = Account.newAccount(expectedSender.getId(), expectedBalanceSender);
        final var expectedRecipient = Client.newClient("Jane Doe", "jane@doe.com");
        final var expectedBalanceRecipient = BigDecimal.valueOf(50.0);
        final var expectedAccountRecipient = Account.newAccount(expectedRecipient.getId(), expectedBalanceRecipient);
        final var expectedAmount = BigDecimal.valueOf(100.0);

        when(accountGateway.findById(expectedAccountSender.getId())).thenReturn(Optional.of(expectedAccountSender));
        when(accountGateway.findById(expectedAccountRecipient.getId())).thenReturn(Optional.of(expectedAccountRecipient));
        when(accountGateway.update(expectedAccountSender)).thenReturn(expectedAccountSender);
        when(accountGateway.update(expectedAccountRecipient)).thenReturn(expectedAccountRecipient);

        doNothing()
                .when(transactionGateway).create(Mockito.any());

        doNothing()
                .when(eventDispatcher).dispatch(Mockito.any());

        final var input = CreateTransactionInput.from(expectedAccountSender.getId(), expectedAccountRecipient.getId(),expectedAmount);
        Assertions.assertDoesNotThrow(() -> this.useCase.execute(input));
    }
}
