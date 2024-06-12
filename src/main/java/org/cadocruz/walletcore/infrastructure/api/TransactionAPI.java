package org.cadocruz.walletcore.infrastructure.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cadocruz.walletcore.infrastructure.transaction.dto.CreateTransactionRequest;
import org.cadocruz.walletcore.infrastructure.transaction.dto.CreateTransactionResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;

@RequestMapping("transactions")
@Tag(name = "Transactions")
public interface TransactionAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateTransactionResponse.class)) }),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown", content = @Content),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown", content = @Content)
    })
    ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRequest input);

}
