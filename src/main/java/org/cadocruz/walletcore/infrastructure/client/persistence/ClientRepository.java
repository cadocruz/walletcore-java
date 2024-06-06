package org.cadocruz.walletcore.infrastructure.client.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<ClientJpaEntity, String> {
}
