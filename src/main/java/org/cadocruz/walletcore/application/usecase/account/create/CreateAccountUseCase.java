package org.cadocruz.walletcore.application.usecase.account.create;

import org.cadocruz.walletcore.application.UseCase;
import org.cadocruz.walletcore.domain.entity.Account;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;

import java.util.Objects;

public class CreateAccountUseCase extends UseCase<CreateAccountInput, CreateAccountOutput> {

    private final AccountGateway accountGateway;

    public CreateAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
    }

    @Override
    public CreateAccountOutput execute(CreateAccountInput input) {
        final var clientId = input.clientId();
        final var balance = input.balance();

        final var account = Account.newAccount(clientId, balance);
        account.validate();
        accountGateway.create(account);
        return new CreateAccountOutput(account.getId());
    }
}
