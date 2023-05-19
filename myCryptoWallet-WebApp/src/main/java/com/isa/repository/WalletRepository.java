package com.isa.repository;

import com.isa.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity,Long> {

    WalletEntity findWalletEntitiesByUserEmail(String email);
}
