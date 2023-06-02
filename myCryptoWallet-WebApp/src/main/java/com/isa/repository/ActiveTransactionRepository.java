package com.isa.repository;

import com.isa.entity.ActiveTransactionEntity;
import com.isa.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveTransactionRepository extends JpaRepository<ActiveTransactionEntity,Long> {

    void deleteActiveTransactionEntitiesByWalletEntity(WalletEntity walletEntity);
}
