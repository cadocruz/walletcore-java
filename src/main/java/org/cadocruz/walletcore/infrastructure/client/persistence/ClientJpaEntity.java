package org.cadocruz.walletcore.infrastructure.client.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.cadocruz.walletcore.domain.models.Client;

import java.time.Instant;

@Data
@Entity(name = "Client")
@Table(name = "clients")
@AllArgsConstructor
public class ClientJpaEntity {

    @Id
    @Column(name = "id", nullable = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public ClientJpaEntity() {}

    public static ClientJpaEntity from(Client client) {
        return new ClientJpaEntity(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }

    public Client toAggregate() {
        return Client.with(getId(), getName(), getEmail(), getCreatedAt(), getUpdatedAt());
    }
}
