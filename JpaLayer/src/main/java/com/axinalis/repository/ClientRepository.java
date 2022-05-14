package com.axinalis.repository;

import com.axinalis.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByEmail(String email);
    Optional<ClientEntity> findByClientId(Long clientId);

}
