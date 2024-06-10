package org.cadocruz.walletcore.infrastructure.configuration.usecases;

import org.cadocruz.walletcore.application.usecase.transaction.create.CreateTransactionUseCase;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.cadocruz.walletcore.domain.gateway.TransactionGateway;
import org.cadocruz.walletcore.infrastructure.events.EventDispatcher;
import org.cadocruz.walletcore.infrastructure.events.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseConfig {

    private final AccountGateway accountGateway;
    private final TransactionGateway transactionGateway;
    private final Event transactionCreatedEvent;
    private final Event balanceUpdatedEvent;
    private final EventDispatcher eventDispatcher;

    public TransactionUseCaseConfig(
            final AccountGateway accountGateway,
            final TransactionGateway transactionGateway,
            @Qualifier("transactionCreatedEvent") final Event transactionCreatedEvent,
            @Qualifier("balanceUpdatedEvent") final Event balanceUpdatedEvent,
            final EventDispatcher eventDispatcher) {
        this.accountGateway = accountGateway;
        this.transactionGateway = transactionGateway;
        this.transactionCreatedEvent = transactionCreatedEvent;
        this.balanceUpdatedEvent = balanceUpdatedEvent;
        this.eventDispatcher = eventDispatcher;
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase() {
        return new CreateTransactionUseCase(accountGateway,transactionGateway, transactionCreatedEvent, balanceUpdatedEvent, eventDispatcher);
    }
}
