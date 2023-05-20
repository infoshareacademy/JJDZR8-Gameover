package com.isa.repository;

import com.isa.entity.ActiveTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveTransactionRepository extends JpaRepository<ActiveTransactionEntity,Long> {
}
