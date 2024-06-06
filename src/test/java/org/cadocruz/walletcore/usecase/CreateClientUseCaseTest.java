package org.cadocruz.walletcore.usecase;

import org.cadocruz.walletcore.application.usecase.client.create.CreateClientInput;
import org.cadocruz.walletcore.application.usecase.client.create.CreateClientUseCase;
import org.cadocruz.walletcore.domain.gateway.ClientGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateClientUseCaseTest {

    @Mock
    private ClientGateway clientGateway;

    @InjectMocks
    private CreateClientUseCase useCase;

    @Test
    void givenAValidInput_whenCreateClientUseCase_thenReturnClientId() {
        final var expectedName = "John Doe";
        final var expectedEmail = "john.doe@example.com";
        final var input = CreateClientInput.with(expectedName, expectedEmail);
        when(clientGateway.create(Mockito.any())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        final var output = useCase.execute(input);
        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(clientGateway, Mockito.times(1)).create(Mockito.argThat(aClient ->
                Objects.equals(expectedName, aClient.getName())
                        && Objects.equals(expectedEmail, aClient.getEmail())));

    }

    @Test
    void givenAInvalidInputName_whenCreateClientUseCase_thenShouldThrowException() {
        final String expectedName = null;
        final var expectedEmail = "john.doe@example.com";
        final var expectedMessageError = "Client name cannot be null or empty";

        final var input = CreateClientInput.with(expectedName, expectedEmail);

        final var actualException = assertThrows(RuntimeException.class, () -> useCase.execute(input));

        Assertions.assertEquals(expectedMessageError, actualException.getMessage());
    }

    @Test
    void givenAInvalidInputEmptyName_whenCreateClientUseCase_thenShouldThrowException() {
        final var expectedName = "  ";
        final var expectedEmail = "john.doe@example.com";
        final var expectedMessageError = "Client name cannot be null or empty";

        final var input = CreateClientInput.with(expectedName, expectedEmail);

        final var actualException = assertThrows(RuntimeException.class, () -> useCase.execute(input));

        Assertions.assertEquals(expectedMessageError, actualException.getMessage());
    }

    @Test
    void givenAInvalidInputEmail_whenCreateClientUseCase_thenShouldThrowException() {
        final var expectedName = "John Doe";
        final String expectedEmail = null;
        final var expectedMessageError = "Client email cannot be null or empty";

        final var input = CreateClientInput.with(expectedName, expectedEmail);

        final var actualException = assertThrows(RuntimeException.class, () -> useCase.execute(input));

        Assertions.assertEquals(expectedMessageError, actualException.getMessage());
    }

    @Test
    void givenAInvalidInputEmptyEmail_whenCreateClientUseCase_thenShouldThrowException() {
        final var expectedName = "John Doe";
        final String expectedEmail = " ";
        final var expectedMessageError = "Client email cannot be null or empty";

        final var input = CreateClientInput.with(expectedName, expectedEmail);

        final var actualException = assertThrows(RuntimeException.class, () -> useCase.execute(input));

        Assertions.assertEquals(expectedMessageError, actualException.getMessage());
    }
}
