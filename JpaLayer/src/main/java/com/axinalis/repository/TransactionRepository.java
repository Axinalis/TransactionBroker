package com.axinalis.repository;

import com.axinalis.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByClientIdOrderByCreatedAtDesc(Long clientId);

}
