package com.isa.mapper;

import com.isa.control.transactions.ActiveTransaction;
import com.isa.entity.ActiveTransactionEntity;

public class ActiveTransactionEntityMapper {

    public static ActiveTransaction mapActiveTransactionEntityToActiveTransaction(ActiveTransactionEntity activeTransactionEntity){
        ActiveTransaction activeTransaction = new ActiveTransaction();
        activeTransaction.setIdTransaction(activeTransactionEntity.getIdTransaction());
        activeTransaction.setActive(activeTransactionEntity.isActive());
        activeTransaction.setVolume(activeTransactionEntity.getVolume());
        activeTransaction.setOpenTransactionDate(activeTransactionEntity.getOpenTransactionDate());
        activeTransaction.setOpenPrice(activeTransactionEntity.getOpenPrice());
        activeTransaction.setCurrentPrice(activeTransactionEntity.getCurrentPrice());
        activeTransaction.setStopLoss(activeTransactionEntity.getStopLoss());
        activeTransaction.setSLOn(activeTransactionEntity.isSLOn());
        activeTransaction.setTakeProfit(activeTransactionEntity.getTakeProfit());
        activeTransaction.setTPOn(activeTransactionEntity.isTPOn());
        activeTransaction.setCoin(CoinEntityMapper.mapCoinEntityToCoin(activeTransactionEntity.getCoinEntity()));
        return activeTransaction;
    }

    public static ActiveTransactionEntity mapActiveTransactionToEntity(ActiveTransaction activeTransaction){
        ActiveTransactionEntity activeTransactionEntity = new ActiveTransactionEntity();
        activeTransactionEntity.setIdTransaction(activeTransaction.getIdTransaction());
        activeTransactionEntity.setActive(activeTransaction.isActive());
        activeTransactionEntity.setVolume(activeTransaction.getVolume());
        activeTransactionEntity.setOpenTransactionDate(activeTransaction.getOpenTransactionDate());
        activeTransactionEntity.setOpenPrice(activeTransaction.getOpenPrice());
        activeTransactionEntity.setCurrentPrice(activeTransaction.getCurrentPrice());
        activeTransactionEntity.setSLOn(activeTransaction.isSLOn());
        activeTransactionEntity.setStopLoss(activeTransaction.getStopLoss());
        activeTransactionEntity.setTakeProfit(activeTransaction.getTakeProfit());
        activeTransactionEntity.setTPOn(activeTransaction.isTPOn());
        activeTransactionEntity.setCoinEntity(CoinEntityMapper.mapCoinToEntity(activeTransaction.getCoin()));
        return activeTransactionEntity;
    }
}
