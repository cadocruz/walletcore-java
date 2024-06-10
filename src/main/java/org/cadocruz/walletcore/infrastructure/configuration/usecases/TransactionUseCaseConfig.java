package org.cadocruz.walletcore.infrastructure.configuration.usecases;

import org.cadocruz.walletcore.application.usecase.transaction.create.CreateTransactionUseCase;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.cadocruz.walletcore.domain.gateway.TransactionGateway;
import org.cadocruz.walletcore.infrastructure.events.EventDispatcher;
import org.cadocruz.walletcore.infrastructure.events.EventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseConfig {

    private final AccountGateway accountGateway;
    private final TransactionGateway transactionGateway;
    private final EventService transactionCreatedEvent;
    private final EventService balanceUpdatedEvent;
    private final EventDispatcher eventDispatcher;

    public TransactionUseCaseConfig(
            final AccountGateway accountGateway,
            final TransactionGateway transactionGateway,
            @Qualifier("transactionCreated") final EventService transactionCreatedEvent,
            @Qualifier("balanceUpdated") final EventService balanceUpdatedEvent,
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
