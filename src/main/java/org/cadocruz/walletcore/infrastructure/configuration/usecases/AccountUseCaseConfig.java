package org.cadocruz.walletcore.infrastructure.configuration.usecases;

import org.cadocruz.walletcore.application.usecase.account.create.CreateAccountUseCase;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountUseCaseConfig {

    private final AccountGateway accountGateway;

    public AccountUseCaseConfig(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }


    @Bean
    public CreateAccountUseCase createAccountUseCase() {
        return new CreateAccountUseCase(accountGateway);
    }

}
