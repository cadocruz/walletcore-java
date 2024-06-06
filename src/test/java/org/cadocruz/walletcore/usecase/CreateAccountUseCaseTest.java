package org.cadocruz.walletcore.usecase;

import org.cadocruz.walletcore.application.usecase.account.create.CreateAccountInput;
import org.cadocruz.walletcore.application.usecase.account.create.CreateAccountUseCase;
import org.cadocruz.walletcore.domain.gateway.AccountGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateAccountUseCaseTest {

    @Mock
    private AccountGateway accountGateway;

    @InjectMocks
    private CreateAccountUseCase useCase;

    @Test
    void givenAValidInput_whenCreateAccountUseCase_thenReturnAccountId() {
        final var expectedClientID = UUID.randomUUID().toString();
        final var expectedBalance = BigDecimal.valueOf(1000.0);
        final var input = CreateAccountInput.from(expectedClientID, expectedBalance);

        when(accountGateway.create(Mockito.any())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        final var output = useCase.execute(input);
        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(accountGateway, Mockito.times(1)).create(Mockito.argThat(aAccount ->
                Objects.equals(expectedClientID, aAccount.getClientID())
                        && Objects.equals(expectedBalance, aAccount.getBalance())));

    }

    @Test
    void givenAInvalidClientId_whenCreateAccountUseCase_thenShouldThrowException() {
        final String expectedClientID = null;
        final var expectedBalance = BigDecimal.valueOf(1000.0);

        final var expectedMessageError = "Client id is null or empty";

        final var input = CreateAccountInput.from(expectedClientID, expectedBalance);

        final var actualException = assertThrows(RuntimeException.class, () -> useCase.execute(input));

        Assertions.assertEquals(expectedMessageError, actualException.getMessage());

    }
}
