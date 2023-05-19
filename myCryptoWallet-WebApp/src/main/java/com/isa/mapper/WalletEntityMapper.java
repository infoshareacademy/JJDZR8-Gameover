package com.isa.mapper;

import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;
import com.isa.entity.ActiveTransactionEntity;
import com.isa.entity.ClosedTransactionEntity;
import com.isa.entity.WalletEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WalletEntityMapper {

    public static Wallet mapWalletEntityToWallet(WalletEntity walletEntity){
        Set<ActiveTransaction> activeTransactions = new TreeSet<>();
        Set<ClosedTransaction> closedTransactions = new TreeSet<>();
        if (!walletEntity.getActiveTransactionEntityList().isEmpty()){
            activeTransactions = walletEntity.getActiveTransactionEntityList()
                    .stream().map(ActiveTransactionEntityMapper::mapActiveTransactionEntityToActiveTransaction)
                    .collect(Collectors.toSet());
        }
        if (!walletEntity.getClosedTransactionEntities().isEmpty()){
            closedTransactions = walletEntity.getClosedTransactionEntities()
                    .stream().map(ClosedTransactionEntityMapper::mapClosedTransactionEntityToClosedTransaction)
                    .collect(Collectors.toSet());
        }
        Wallet wallet = new Wallet();
        wallet.setWalletId(walletEntity.getWalletId());
        wallet.setHistoricalProfitLoss(walletEntity.getHistoricalProfitLoss());
        wallet.setPaymentCalc(walletEntity.getPaymentCalc());
        wallet.setActiveTransactions(activeTransactions);
        wallet.setTransactionsHistory(closedTransactions);
        return wallet;
    }

    public static WalletEntity mapWalletToEntity(Wallet wallet){
        List<ActiveTransactionEntity> activeTransactionEntityList = new ArrayList<>();
        List<ClosedTransactionEntity> closedTransactionEntityList = new ArrayList<>();
        if (!wallet.getActiveTransactions().isEmpty()){
            activeTransactionEntityList = wallet.getActiveTransactions().stream()
                    .map(ActiveTransactionEntityMapper::mapActiveTransactionToEntity).collect(Collectors.toList());
        }
        if (!wallet.getTransactionsHistory().isEmpty()){
            closedTransactionEntityList = wallet.getTransactionsHistory().stream()
                    .map(ClosedTransactionEntityMapper::mapClosedTransactionToEntity).collect(Collectors.toList());
        }
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setWalletId(wallet.getWalletId());
        walletEntity.setHistoricalProfitLoss(wallet.getHistoricalProfitLoss());
        walletEntity.setPaymentCalc(wallet.getPaymentCalc());
        walletEntity.setActiveTransactionEntityList(activeTransactionEntityList);
        walletEntity.setClosedTransactionEntities(closedTransactionEntityList);
        return walletEntity;
    }
}
