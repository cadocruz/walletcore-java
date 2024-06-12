package org.cadocruz.walletcore.infrastructure.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cadocruz.walletcore.infrastructure.account.dto.CreateAccountRequest;
import org.cadocruz.walletcore.infrastructure.account.dto.CreateAccountResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("accounts")
@Tag(name = "Accounts")
public interface AccountAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateAccountResponse.class)) }),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown", content = @Content),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown", content = @Content)
    })
    ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest input);

}
