package org.cadocruz.walletcore.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Client {
    private String id;
    private String name;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;

    public static Client newClient(String name, String email) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Client(id, name, email, now, now);
    }

    public static Client with(String id, String name, String email, Instant createdAt, Instant updatedAt) {
        return new Client(id, name, email, createdAt, updatedAt);
    }

    public void validate() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Client email cannot be null or empty");
        }
    }
}



