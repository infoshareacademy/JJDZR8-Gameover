package com.isa.repository;

import com.isa.entity.ClosedTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosedTransactionRepository extends JpaRepository<ClosedTransactionEntity,Long> {
}
