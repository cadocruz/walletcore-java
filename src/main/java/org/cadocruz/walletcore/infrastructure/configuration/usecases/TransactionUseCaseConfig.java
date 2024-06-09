package org.cadocruz.walletcore.infrastructure.configuration.usecases;

import org.cadocruz.walletcore.application.usecase.transaction.create.CreateTransactionUseCase;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.cadocruz.walletcore.domain.gateway.TransactionGateway;
import org.cadocruz.walletcore.infrastructure.transaction.persistence.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseConfig {

    private final AccountGateway accountGateway;
    private final TransactionGateway transactionGateway;

    public TransactionUseCaseConfig(final AccountGateway accountGateway, final TransactionGateway transactionGateway) {
        this.accountGateway = accountGateway;
        this.transactionGateway = transactionGateway;
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase() {
        return new CreateTransactionUseCase(accountGateway,transactionGateway);
    }
}
